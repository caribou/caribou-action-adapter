package com.instrument.triface.interop;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;
import com.instrument.triface.interop.JSObjectFactory;
import com.instrument.triface.util.FactoryUtils;

public class JSObjectFactoryTest extends AObjectFactoryTest {

	@Before
	public void setupFactory() {	
		objectFactory = FactoryUtils.getJSObjectFactory("DummyAction");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void badActionFactoryTest() {
		objectFactory = new JSObjectFactory(ITrifaceAction.class, new File("NON_EXISTANT_ACTION"));
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
