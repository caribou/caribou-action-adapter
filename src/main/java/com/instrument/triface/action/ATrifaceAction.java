package com.instrument.triface.action;

import java.util.Map;

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
	protected abstract void setMapInternal(Map s);
	protected abstract Map<Object, Object> getMapInternal();

	public void setMap(Map s)
	{
		this.setMapInternal(s);
	}
	
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
	public Map<Object, Object> getConvertedMap(ITrifaceAction.MapType type)
	{
		switch (type) {
			case CLOJURE:
				return getClojureMap();
			case RUBY:
				throw new UnsupportedOperationException("Not yet");
			case PYTHON:
				throw new UnsupportedOperationException("Not yet");
			default:
				return null;
		}
	}
	
	/*
	 * Handle converting JRuby-specific types within the model map
	 * to clojure-compatable types. Pretty gross. And incomplete.
	 * 
	 * Returns a deep-copy of the underlying map.
	 * 
	 * TODO: this needs to recursively traverse the map looking for
	 * convertable types...
	 * 
	 */
	public PersistentHashMap getClojureMap()
	{
		PersistentHashMap p = null;
		boolean isEmptyMap = false;
		
		Map<Object, Object> m = this.getMap();
		if(m instanceof PersistentHashMap)
		{
			// exploit the fact that assoc returns a deep copy cheaply
			p = (PersistentHashMap)((PersistentHashMap)m).assoc(null, null);
		}
		else
		{
			// deep copy, starting with an empty map
			p = PersistentHashMap.EMPTY;
			isEmptyMap = true;
		}
		
		Object mapObjVal;
		for(Map.Entry<Object, Object> entry : m.entrySet())
		{
			mapObjVal = entry.getValue();
			if(ClojureTypeUtils.hasConversion(mapObjVal) || isEmptyMap)
			{
				p = (PersistentHashMap) p.assoc(entry.getKey(), ClojureTypeUtils.convert(mapObjVal));
			}
		}
		return p;

	}
}
