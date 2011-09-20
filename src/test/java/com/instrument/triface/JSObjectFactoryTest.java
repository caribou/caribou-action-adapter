package com.instrument.triface;

import org.junit.Before;

import com.instrument.triface.action.ITrifaceAction;

public class JSObjectFactoryTest extends AObjectFactoryTest {

	@Before
	public void setupFactory() {	
		objectFactory = new TrifaceJSObjectFactory(ITrifaceAction.class, "DummyAction");
		objectFactory.addLoadPath("src/test/js/");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
}
