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

import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentHashMap;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.interop.JythonObjectFactory;
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
		action.setMap(PersistentArrayMap.EMPTY);
		Map<Object, Object> map = action.execute();
		
		// simple string test
    	assertTrue(map.get("string") instanceof String);
    	assertEquals("hello world!", map.get("string"));
    	
    	// boolean
    	assertTrue(map.get("boolean") instanceof Boolean);
    	assertEquals(true, map.get("boolean"));
    	
    	// int
    	assertTrue(map.get("int") instanceof Integer);
    	assertEquals(1, map.get("int"));
    	
    	// python long -> BigInteger...
    	assertTrue(map.get("long") instanceof BigInteger);
    	assertEquals(1l, ((BigInteger)map.get("long")).longValue());    	

    	// float -> Double...
    	assertTrue(map.get("float") instanceof Double);
    	assertEquals(1.0, map.get("float"));    	
    	
    	// complex -> PyComplex
    	assertTrue(map.get("complex") instanceof PyComplex);
    	assertEquals(new PyFloat(0.0), ((PyComplex)map.get("complex")).getReal());      	
    	assertEquals(new PyFloat(1.0), ((PyComplex)map.get("complex")).getImag());
    	
    	// tuple -> pyTuple
    	assertTrue(map.get("tuple") instanceof PyTuple);
    	assertEquals(1, ((PyTuple)map.get("tuple")).get(0));          	
    	assertEquals(2, ((PyTuple)map.get("tuple")).get(1));          	
    	
    	// list
    	assertTrue(map.get("list") instanceof List);
    	assertEquals(Arrays.asList(1,1,2,3,5), map.get("list"));          	
    	
    	// map -> dict test
    	assertTrue(map.get("map") instanceof Map);
    	assertEquals("val1", ((Map)map.get("map")).get("key1"));
    	assertEquals(2, ((Map)map.get("map")).get("key2"));
	}		

}
