package com.instrument.triface.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.python.core.PyComplex;
import org.python.core.PyDictionary;
import org.python.core.PyFloat;
import org.python.core.PyTuple;

import clojure.lang.PersistentHashMap;

import com.instrument.triface.IObjectFactory;
import com.instrument.triface.JythonObjectFactory;
import com.instrument.triface.util.FactoryUtils;

public class JythonNativeTypeActionTest {

	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;
	
	@Before
	public void setupFactory()
	{
		objectFactory = FactoryUtils.getJythonObjectFactory("NativeTypesAction");
    	action = (ITrifaceAction) objectFactory.createObject();
	}
	
	@Test
	public void getPyDictMapTest()
	{
		// actions should return a native empty 'map' implementation by default
		assertNotNull(action.getMap());
		assertTrue(action.getMap().isEmpty());
		assertTrue(action.getMap() instanceof PyDictionary);
	}		
	
	@Test
	public void JythonJavaTypeConversionTest()
	{
		action.setMap(PersistentHashMap.EMPTY);
		Map<Object, Object> mangleMap = action.execute();
		
		// simple string test
    	assertTrue(mangleMap.get("string") instanceof String);
    	assertEquals("hello world!", mangleMap.get("string"));
    	
    	// boolean
    	assertTrue(mangleMap.get("boolean") instanceof Boolean);
    	assertEquals(true, mangleMap.get("boolean"));
    	
    	// int
    	assertTrue(mangleMap.get("int") instanceof Integer);
    	assertEquals(1, mangleMap.get("int"));
    	
    	// python long -> BigInteger...
    	assertTrue(mangleMap.get("long") instanceof BigInteger);
    	assertEquals(1l, ((BigInteger)mangleMap.get("long")).longValue());    	

    	// float -> Double...
    	assertTrue(mangleMap.get("float") instanceof Double);
    	assertEquals(1.0, mangleMap.get("float"));    	
    	
    	// complex -> PyComplex
    	assertTrue(mangleMap.get("complex") instanceof PyComplex);
    	assertEquals(new PyFloat(0.0), ((PyComplex)mangleMap.get("complex")).getReal());      	
    	assertEquals(new PyFloat(1.0), ((PyComplex)mangleMap.get("complex")).getImag());
    	
    	// tuple -> pyTuple
    	assertTrue(mangleMap.get("tuple") instanceof PyTuple);
    	assertEquals(1, ((PyTuple)mangleMap.get("tuple")).get(0));          	
    	assertEquals(2, ((PyTuple)mangleMap.get("tuple")).get(1));          	
    	
    	// list
    	assertTrue(mangleMap.get("list") instanceof List);
    	assertEquals(Arrays.asList(1,1,2,3,5), mangleMap.get("list"));          	
    	
    	// map -> dict test
    	assertTrue(mangleMap.get("map") instanceof Map);
    	assertEquals("val1", ((Map)mangleMap.get("map")).get("key1"));
    	assertEquals(2, ((Map)mangleMap.get("map")).get("key2"));
	}		

}
