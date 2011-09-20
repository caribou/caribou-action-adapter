package com.instrument.triface;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void badActionFactoryTest() {
		objectFactory = new JRubyObjectFactory(ITrifaceAction.class, "NON_EXISTANT_ACTION");
		assertNotNull(objectFactory);
		try
		{
			objectFactory.createObject();
		}
		catch(RuntimeException e)
		{
			return;
		}
	}
	
}
