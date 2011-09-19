package com.instrument.triface;

import java.util.List;

/**
 * Interface defining a triface object factory.
 * 
 * @author feigner
 *
 */
public interface IObjectFactory {
	public Object createObject();
	public void addLoadPath(String path);
	public void addLoadPaths(List<String> paths);	
}