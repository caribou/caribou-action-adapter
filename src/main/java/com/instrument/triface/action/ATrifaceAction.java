package com.instrument.triface.action;

import java.util.List;
import java.util.Map;

import clojure.lang.IPersistentList;
import clojure.lang.IPersistentMap;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentList;

import com.instrument.triface.util.ClojureTypeUtils;

/**
 * Abstract Triface action class. Defines default map conversion behavior.
 * 
 * @author feigner
 *
 */
public abstract class ATrifaceAction implements ITrifaceAction {
	
	public abstract Map<Object, Object> execute();
	protected abstract void setMapInternal(Map<Object, Object> map);
	protected abstract Map<Object, Object> getMapInternal();

	/**
	 * Hook for modifying the incoming map.
	 */
	public void setMap(Map<Object, Object> map)
	{
		this.setMapInternal(map);
	}
	
	/**
	 * Hook for modifying the outgoing map.
	 */
	public Map<Object, Object> getMap()
	{
		return this.getMapInternal();
	}
	
	/**
	 * Convert JRuby-specific map element types to other types,
	 * consumable by different JVM languages
	 * 
	 * @param type the type to convert the map to.
	 * 
	 */
	public Map<Object, Object> getConvertedMap(MapType type)
	{
		switch (type) {
			case CLOJURE:
				// gross-ass cast...
				return (Map<Object, Object>) getClojureMap(this.getMap());
			case RUBY:
				throw new UnsupportedOperationException("Not yet");
			case PYTHON:
				throw new UnsupportedOperationException("Not yet");
			default:
				return null;
		}
	}
	
	/**
	 * Handle converting specific types within the model map
	 * to clojure-compatable types. Pretty gross. And incomplete.
	 * 
	 * If we encounter a map or a list, we rip through that, attempting to
	 * find convertable types.
	 * 
	 * TODO: this is really slow. Fix.
	 * 
	 * @Return IPersistentMap a deep-copy of the underlying map, converted.
	 * 
	 */
	public IPersistentMap getClojureMap(Map<Object, Object> sourceMap)
	{
		IPersistentMap p = null;
		p = PersistentArrayMap.EMPTY;
		
		Object mapObjVal;
		for(Map.Entry<Object, Object> entry : sourceMap.entrySet())
		{
			mapObjVal = entry.getValue();
			
			// convert the object, if we have an applicable conversion.
			if(ClojureTypeUtils.hasConversion(mapObjVal))
			{
				mapObjVal = ClojureTypeUtils.convert(mapObjVal);
			}
			
			// convert the contents of maps and lists, otherwise, add it to the map
			if(mapObjVal instanceof Map)
			{
				p = p.assoc(entry.getKey(), getClojureMap((Map<Object, Object>) mapObjVal));
			}
			else if(mapObjVal instanceof List)
			{
				p = p.assoc(entry.getKey(), getClojureList((List<Object>)mapObjVal));
			}
			
			else
			{
				p = (IPersistentMap) p.assoc(entry.getKey(), mapObjVal);
			}
		}
		return p;
	}
	
	/**
	 * Convert list elements to the appropriate types.
	 * 
	 * TODO: check to see if list contains any maps...
	 * 
	 * @param sourceList
	 * @return IPersistentList a deep-copy of the underlying list
	 */
	public IPersistentList getClojureList(List<Object> sourceList)
	{
		IPersistentList l = null;
		l = PersistentList.EMPTY;
		
		// iterate backwards, since cons appends to the front -- maintain order
		for(int i = sourceList.size()-1; i >=0; i--)
		{
			l = (IPersistentList) l.cons(ClojureTypeUtils.convert(sourceList.get(i)));
		}
		
		return l;
	}
}
