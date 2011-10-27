package com.instrument.triface.action;

import java.io.File;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.interop.JRubyObjectFactory;
import com.instrument.triface.interop.JythonObjectFactory;
import com.instrument.triface.interop.TrifaceJSObjectFactory;
import com.instrument.triface.util.FileUtils;

public class TrifaceActionEngine {

	public static ITrifaceAction getAction(File script)
	{
		IObjectFactory factory = null;
		String extension = FileUtils.getFileExtension(script);
		
		// TODO: use enum instead
		if(extension.equalsIgnoreCase(".rb"))
		{
			factory = new JRubyObjectFactory(ITrifaceAction.class, script);
		}
		else if(extension.equalsIgnoreCase(".py"))
		{
			factory = new JythonObjectFactory(ITrifaceAction.class, script);
		}
		else if(extension.equalsIgnoreCase(".js"))
		{
			factory = new TrifaceJSObjectFactory(ITrifaceAction.class, script);
		}
		
		if(factory != null)
		{
			return (ITrifaceAction) factory.createObject();
		}
		else
		{
			// something went wrong
			// TODO: handle this
			return null;
		}

	}
	
}
