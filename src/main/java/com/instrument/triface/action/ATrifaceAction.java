package com.instrument.triface.action;

import java.util.Map;

import clojure.lang.IPersistentMap;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentHashMap;

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
				return (Map<Object, Object>) getClojureMap();
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
	 * TODO: this needs to recursively traverse the map looking for
	 * convertable types...
	 * 
	 * @Return PersistentHashMap a deep-copy of the underlying map, converted.
	 * 
	 */
	public IPersistentMap getClojureMap()
	{
		IPersistentMap p = null;
		boolean isEmptyMap = false;
		
		Map<Object, Object> m = this.getMap();
		if(m instanceof PersistentHashMap || m instanceof PersistentArrayMap)
		{
			// exploit the fact that assoc returns a deep copy cheaply
			p = ((IPersistentMap)m).assoc(null, null);
		}
		else
		{
			// deep copy, starting with an empty map
			p = PersistentArrayMap.EMPTY;
			isEmptyMap = true;
		}
		
		Object mapObjVal;
		for(Map.Entry<Object, Object> entry : m.entrySet())
		{
			mapObjVal = entry.getValue();
			
			// only if we are starting with an empty map do we need to populate the entire map.
			if(ClojureTypeUtils.hasConversion(mapObjVal) || isEmptyMap)
			{
				p = (IPersistentMap) p.assoc(entry.getKey(), ClojureTypeUtils.convert(mapObjVal));
			}
		}
		return p;
	}
}
