package com.instrument.triface;

import java.util.List;

/**
 * Interface defining an object factory.
 * 
 * @author feigner
 *
 */
public interface IObjectFactory {
	
	/**
	 * Get an object
	 * 
	 * @return
	 */
	public Object createObject();
}