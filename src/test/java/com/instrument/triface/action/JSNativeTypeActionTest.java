package com.instrument.triface.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mozilla.javascript.NativeArray;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.util.FactoryUtils;

public class JSNativeTypeActionTest {
	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;

	@Before
	public void setupFactory()
	{
		objectFactory = FactoryUtils.getTrifaceJSObjectFactory("NativeTypesAction");
		action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void getJSMapTest()
	{
		// JS has no concept of a native map, we use HashMap instead.
		assertNotNull(action.getMap());
		assertTrue(action.getMap().isEmpty());
		assertTrue(action.getMap() instanceof HashMap);
	}	
	
	@Test
	public void JSJavaTypeConversionTest()
	{
		Map<Object, Object> map = action.execute();
		
		// simple string test
    	assertTrue(map.get("string") instanceof String);
    	assertEquals("hello world!", map.get("string"));
    	
    	// boolean
    	assertTrue(map.get("boolean") instanceof Boolean);
    	assertEquals(true, map.get("boolean"));
    	
    	// int
    	assertTrue(map.get("int") instanceof Double);
    	assertEquals(1.0, map.get("int"));

    	// float -> Double...
    	assertTrue(map.get("float") instanceof Double);
    	assertEquals(1.0, map.get("float"));    	          	
    	
    	// list
    	assertTrue(map.get("list") instanceof NativeArray);
    	// TODO: inspect this for equality
    	
    	// map test
    	assertTrue(map.get("map") instanceof Map);
    	assertEquals("val1", ((Map)map.get("map")).get("key1"));
    	assertEquals(2.0, ((Map)map.get("map")).get("key2"));
	}	
}