package com.instrument.triface.interfaces;

import java.util.Map;

/**
 * Dummy interface to test java -> jython integration
 * @author eff
 *
 */
public interface IMapMangler {	
	public Map mangle();
	public void setMap(Map s);
	public Map getMap();
}
