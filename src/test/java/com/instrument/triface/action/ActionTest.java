package com.instrument.triface.action;

import com.instrument.triface.IObjectFactory;
import com.instrument.triface.JRubyObjectFactory;
import com.instrument.triface.JSObjectFactory;
import com.instrument.triface.JythonObjectFactory;

/**
 * Superclass for helping with action testing.
 * @author feigner
 *
 */
public class ActionTest {

	public IObjectFactory getJRubyObjectFactory(String script)
	{
		IObjectFactory objectFactory = new JRubyObjectFactory(ITrifaceAction.class, script);
		objectFactory.addLoadPath("src/test/ruby");
		return objectFactory;
	}
	
	public IObjectFactory getJythonObjectFactory(String script)
	{
		IObjectFactory objectFactory = new JythonObjectFactory(ITrifaceAction.class, script);
		objectFactory.addLoadPath("src/test/python/");
    	return objectFactory;
	}
	
	public IObjectFactory getJSObjectFactory(String script)
	{
		IObjectFactory objectFactory = new JSObjectFactory(ITrifaceAction.class, script);
		objectFactory.addLoadPath("src/test/js/");
		return objectFactory;
	}
	
}
