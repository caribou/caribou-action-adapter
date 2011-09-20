package com.instrument.triface;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;

public class JSObjectFactoryTest extends AObjectFactoryTest {

	@Before
	public void setupFactory() {	
		objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, "DummyAction");
		objectFactory.addLoadPath("src/test/js/");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void badActionFactoryTest() {
		objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, "NON_EXISTANT_ACTION");
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
