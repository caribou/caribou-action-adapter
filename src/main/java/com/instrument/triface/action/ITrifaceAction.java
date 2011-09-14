package com.instrument.triface.action;

import java.util.Map;

/**
 * Simple interface for triface actions.
 * 
 * @author feigner
 *
 */
public interface ITrifaceAction {
	public Map<Object, Object> execute();
	public void setMap(Map s);
	public Map<Object, Object> getMap();
	public Map getConvertedMap();
}
