package com.instrument.triface.interop;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.instrument.triface.action.ITrifaceAction;
import com.instrument.triface.interop.JRubyObjectFactory;
import com.instrument.triface.util.FactoryUtils;

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
		objectFactory = FactoryUtils.getJRubyObjectFactory("DummyAction");
		action = (ITrifaceAction) objectFactory.createObject();
	}

	@Test
	public void badActionFactoryTest() {
		objectFactory = new JRubyObjectFactory(ITrifaceAction.class, new File("NON_EXISTANT_ACTION"));
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
