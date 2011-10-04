package com.instrument.triface.util;

import java.io.File;

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
		File f = new File("src/test/ruby/" + script + ".rb");
		IObjectFactory objectFactory = new JRubyObjectFactory(ITrifaceAction.class, f);
		return objectFactory;
	}
	
	public static IObjectFactory getJythonObjectFactory(String script)
	{
		File f = new File("src/test/python/" + script + ".py");
		IObjectFactory objectFactory = new JythonObjectFactory(ITrifaceAction.class, f);
    	return objectFactory;
	}
	
	public static IObjectFactory getJSObjectFactory(String script)
	{
		File f = new File("src/test/js/" + script + ".js");
		IObjectFactory objectFactory = new JSObjectFactory(ITrifaceAction.class, f);
		return objectFactory;
	}
	
	public static IObjectFactory getTrifaceJSObjectFactory(String script)
	{
		File f = new File("src/test/js/" + script + ".js");
		IObjectFactory objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, f);
		return objectFactory;
	}	
	
}
