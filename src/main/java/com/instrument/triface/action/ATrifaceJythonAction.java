package com.instrument.triface.action;

import java.util.List;
import java.util.Map;

import org.python.core.PyDictionary;
import org.python.core.PyList;


import clojure.lang.APersistentMap;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentVector;

/**
 * Abstract class for use with python / jython actions. Handles
 * converting python types to clojure types. 
 * 
 * @author feigner
 *
 */
public abstract class ATrifaceJythonAction implements ITrifaceAction {
	public abstract Map<Object, Object> mangle();
	public abstract void setMap(Map s);
	public abstract Map<Object, Object> getMap();
	
	/*
	 * Method to handle converting jython-specific types
	 * to clojure-compatable types. Pretty gross. And incomplete.
	 */
	public Map getConvertedMap()
	{
		Object mapObjVal;
		for(Map.Entry<Object, Object> entry : this.getMap().entrySet())
		{
			mapObjVal = entry.getValue();
			if(mapObjVal instanceof PyDictionary)
			{
				this.setMap((Map)((APersistentMap)this.getMap()).assoc(entry.getKey(), PersistentArrayMap.create((Map)mapObjVal)));
			}
			else if(mapObjVal instanceof PyList)
			{
				this.setMap((Map)((APersistentMap)this.getMap()).assoc(entry.getKey(), PersistentVector.create((List)mapObjVal)));
			}
		}
		return this.getMap();
	}
}
