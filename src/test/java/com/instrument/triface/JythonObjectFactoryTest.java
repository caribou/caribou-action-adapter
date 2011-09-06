package com.instrument.triface;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.python.core.Py;
import org.python.core.PyComplex;
import org.python.core.PyFloat;
import org.python.core.PyString;
import org.python.core.PyTuple;

import com.instrument.triface.interfaces.IMapMangler;

/**
 * Simple tests for basic JythonObjectFactory funcitonality
 * 
 * @author eff
 *
 */
public class JythonObjectFactoryTest {

	private JythonObjectFactory objectFactory;
	private IMapMangler mangler;
	
	@Before
	public void setupFactory()
	{
    	// set the system path so that our python class can be found...
		Py.getSystemState().path.append(new PyString("src/main/python/"));
		
		objectFactory = new JythonObjectFactory(IMapMangler.class, "MapMangler");
    	mangler = (IMapMangler) objectFactory.createObject();
	}

	@Test
	public void simpleTest()
	{
		assertNotNull(mangler.getMap());
		assertTrue(mangler.getMap().isEmpty());
		
		Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put("foo","bar");
		
		mangler.setMap(testMap);
		assertFalse(mangler.getMap().isEmpty());
		assertTrue(mangler.getMap().containsKey("foo"));
		assertEquals("bar", mangler.getMap().get("foo"));
	}
	
	@Test
	public void typeConverstionTests()
	{
		assertNotNull(mangler.getMap());
		assertTrue(mangler.getMap().isEmpty());
		Map<String, Object> mangleMap = mangler.mangle();
		
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
    	assertTrue(mangleMap.get("dict") instanceof Map);
    	assertEquals("val1", ((Map)mangleMap.get("dict")).get("key1"));
    	assertEquals(2, ((Map)mangleMap.get("dict")).get("key2"));
	}
	
}
