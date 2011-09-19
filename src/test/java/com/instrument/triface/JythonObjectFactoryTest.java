package com.instrument.triface;

import org.junit.Before;

import com.instrument.triface.action.ITrifaceAction;

/**
 * Simple tests for basic JythonObjectFactory functionality
 * 
 * @author eff
 *
 */
public class JythonObjectFactoryTest extends AObjectFactoryTest {
	
	@Before
	public void setupFactory()
	{	
		objectFactory = new JythonObjectFactory(ITrifaceAction.class, "DummyAction");
		objectFactory.addLoadPath("src/test/python/");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
}
