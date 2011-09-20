package com.instrument.triface.action;

import java.util.Map;

/**
 * Triface action interface: Set an object map. Execute an action. 
 * Get a map back. Convert the map, if you need to.
 * 
 * @author feigner
 *
 */
public interface ITrifaceAction {
	
	/**
	 * Execute the action. 
	 * 
	 * @return
	 */
	public Map<Object, Object> execute();
	
	/**
	 * Retrieve the action object map.
	 * @return Map the action map
	 */
	public Map<Object, Object> getMap();
	
	/**
	 * Set the action object map.
	 * @param map the map the action will utilize.
	 */
	public void setMap(Map<Object, Object> map);
	
	/**
	 * Helpers for converting underlying map.
	 */
	public enum MapType {JAVA, CLOJURE, RUBY, PYTHON}
	public Map<Object, Object> getConvertedMap(MapType type);
}
