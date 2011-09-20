package com.instrument.triface;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void badActionFactoryTest() 
	{
		objectFactory = new JythonObjectFactory(ITrifaceAction.class, "NON_EXISTANT_ACTION");
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
