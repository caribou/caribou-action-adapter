package com.instrument.triface;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;

/**
 * Simple tests for basic JRubyObjectFactory functionality
 * 
 * @author feigner
 *
 */
public class ScriptTest{
	
	IObjectFactory objectFactory;
	ITrifaceAction action;

	@Test
	public void JRubyActionFactorySmokeTest() throws IOException {
		File file = new File("src/test/ruby/NativeTypesAction.rb");
		
		objectFactory = new JRubyObjectFactory(ITrifaceAction.class, file);
		ITrifaceAction action = (ITrifaceAction) objectFactory.createObject();
		System.out.println(action.execute());
	}
	
	@Test
	public void JythonActionFactorySmokeTest() throws IOException {
		File file = new File("src/test/python/NativeTypesAction.py");
		
		objectFactory = new JythonObjectFactory(ITrifaceAction.class, file);
		ITrifaceAction action = (ITrifaceAction) objectFactory.createObject();
		System.out.println(action.execute());
	}
	
	@Test
	public void JSActionFactorySmokeTest() throws IOException {
		File file = new File("src/test/js/NativeTypesAction.js");
		
		objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, file);
		ITrifaceAction action = (ITrifaceAction) objectFactory.createObject();
		System.out.println(action.execute());
	}	
	
}

