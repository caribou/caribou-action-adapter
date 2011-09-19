package com.instrument.triface;

import org.junit.Before;

import com.instrument.triface.action.ITrifaceAction;

/**
 * Simple tests for basic JRubyObjectFactory functionality
 * 
 * @author feigner
 *
 */
public class JRubyObjectFactoryTest extends AObjectFactoryTest {
	
	@Before
	public void setupFactory()
	{
		objectFactory = new JRubyObjectFactory(ITrifaceAction.class, "DummyAction");
		objectFactory.addLoadPath("src/test/ruby");
		action = (ITrifaceAction) objectFactory.createObject();
	}
	
}
