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
	
	/**
	 * Add a lookup path to use when resolving factory assets
	 * @param path the lookup path
	 */
	public void addLoadPath(String path);
	
	/**
	 * Add a list of paths to use when resolving factory assets
	 * @param paths a list of paths
	 */
	public void addLoadPaths(List<String> paths);	
}