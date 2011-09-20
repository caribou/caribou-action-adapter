package com.instrument.triface.util;

import com.instrument.triface.IObjectFactory;
import com.instrument.triface.JRubyObjectFactory;
import com.instrument.triface.JSObjectFactory;
import com.instrument.triface.JythonObjectFactory;
import com.instrument.triface.TrifaceJSObjectFactory;
import com.instrument.triface.action.ITrifaceAction;

/**
 * Superclass for helping with action testing.
 * @author feigner
 *
 */
public class FactoryUtils {

	public static IObjectFactory getJRubyObjectFactory(String script)
	{
		IObjectFactory objectFactory = new JRubyObjectFactory(ITrifaceAction.class, script);
		objectFactory.addLoadPath("src/test/ruby");
		return objectFactory;
	}
	
	public static IObjectFactory getJythonObjectFactory(String script)
	{
		IObjectFactory objectFactory = new JythonObjectFactory(ITrifaceAction.class, script);
		objectFactory.addLoadPath("src/test/python/");
    	return objectFactory;
	}
	
	public static IObjectFactory getJSObjectFactory(String script)
	{
		IObjectFactory objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, script);
		objectFactory.addLoadPath("src/test/js/");
		return objectFactory;
	}
	
}
