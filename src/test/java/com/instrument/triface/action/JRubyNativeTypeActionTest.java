package com.instrument.triface.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jruby.RubyHash;
import org.junit.Before;
import org.junit.Test;

import com.instrument.triface.IObjectFactory;
import com.instrument.triface.util.FactoryUtils;

/**
 * Integration test to ensure default action behavior, doing
 * round trip testing 
 * 
 * @author feigner
 *
 */
public class JRubyNativeTypeActionTest{
	
	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;

	@Before
	public void setupFactory()
	{
		objectFactory = FactoryUtils.getJRubyObjectFactory("NativeTypesAction");
		action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void getJRubyMapTest()
	{
		// actions should return a native empty 'map' implementation by default
		assertNotNull(action.getMap());
		assertTrue(action.getMap().isEmpty());
		assertTrue(action.getMap() instanceof RubyHash);
	}	
	
	@Test
	public void JRubyJavaTypeConversionTest()
	{
		Map<Object, Object> mangleMap = action.execute();
		
		// simple string test
    	assertTrue(mangleMap.get("string") instanceof String);
    	assertEquals("hello world!", mangleMap.get("string"));
    	
    	// boolean
    	assertTrue(mangleMap.get("boolean") instanceof Boolean);
    	assertEquals(true, mangleMap.get("boolean"));
    	
    	// int
    	assertTrue(mangleMap.get("int") instanceof Long);
    	assertEquals(1l, mangleMap.get("int"));

    	// float -> Double...
    	assertTrue(mangleMap.get("float") instanceof Double);
    	assertEquals(1.0, mangleMap.get("float"));    	          	
    	
    	// list
    	assertTrue(mangleMap.get("list") instanceof List);
    	assertEquals(Arrays.asList(1l,1l,2l,3l,5l), mangleMap.get("list"));          	
    	
    	// map test
    	assertTrue(mangleMap.get("map") instanceof Map);
    	assertEquals("val1", ((Map)mangleMap.get("map")).get("key1"));
    	assertEquals(2l, ((Map)mangleMap.get("map")).get("key2"));
	}
}
