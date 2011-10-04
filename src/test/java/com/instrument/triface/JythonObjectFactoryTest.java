package com.instrument.triface;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;
import com.instrument.triface.util.FactoryUtils;

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
		objectFactory = FactoryUtils.getJythonObjectFactory("DummyAction");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void badActionFactoryTest() 
	{
		objectFactory = new JythonObjectFactory(ITrifaceAction.class, new File("NON_EXISTANT_ACTION"));
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
