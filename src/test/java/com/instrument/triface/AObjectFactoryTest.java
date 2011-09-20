package com.instrument.triface;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;

/**
 * Integration tests that should apply to all implementations
 * of IObjectFactory. 
 * 
 * @author feigner
 *
 */
public abstract class AObjectFactoryTest {
	
	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;
	
	public abstract void setupFactory();
	
	@Test
	public void basicFactoryTest()
	{
		assertNotNull(action);
	}
	
	@Test
	public void factoryEqualityTest()
	{
		// check the objects are different
		ITrifaceAction action2 = (ITrifaceAction) objectFactory.createObject();
		assertFalse(action.equals(action2));
	}
}
