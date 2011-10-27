package com.instrument.triface.interop;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;
import com.instrument.triface.interop.TrifaceJSObjectFactory;
import com.instrument.triface.util.FactoryUtils;

public class TrifaceJSObjectFactoryTest extends AObjectFactoryTest {

	@Before
	public void setupFactory() {	
		objectFactory = FactoryUtils.getTrifaceJSObjectFactory("DummyAction");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void badActionFactoryTest() {
		objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, new File("NON_EXISTANT_ACTION"));
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
