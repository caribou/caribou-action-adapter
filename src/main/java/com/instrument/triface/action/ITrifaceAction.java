package com.instrument.triface.action;

import java.util.Map;

/**
 * A simple interface Triface actions:
 * Set an object map. 
 * Execute an action. 
 * Get a(n optionally converted) map back.
 * 
 * @author feigner
 *
 */
public interface ITrifaceAction {
	
	public enum MapType {JAVA, CLOJURE, RUBY, PYTHON}
	public Map<Object, Object> execute();
	
	public Map<Object, Object> getMap();
	public void setMap(Map<Object, Object> s);
	
	public Map<Object, Object> getConvertedMap(MapType type);
}
