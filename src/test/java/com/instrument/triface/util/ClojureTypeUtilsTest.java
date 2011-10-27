package com.instrument.triface.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import org.jruby.RubyArray;
import org.jruby.RubyHash;
import org.junit.Test;
import org.python.core.PyDictionary;
import org.python.core.PyList;

import clojure.lang.IPersistentList;
import clojure.lang.IPersistentMap;
import clojure.lang.PersistentHashMap;
import clojure.lang.PersistentList;

import com.instrument.triface.action.ITrifaceAction;
import com.instrument.triface.interop.IObjectFactory;

public class ClojureTypeUtilsTest {
	
	@Test
	public void JythonDictTest()
	{
		PyDictionary pyDict = new PyDictionary();
		pyDict.put("foo", "bar");
		Object o = ClojureTypeUtils.convert(pyDict);
		assertTrue(o instanceof IPersistentMap);
		assertEquals(((Map)o).get("foo"), "bar");
	}
	
	@Test
	public void JythonListTest()
	{
		PyList pyList = new PyList();
		pyList.add("foo");
		pyList.add("bar");
		Object o = ClojureTypeUtils.convert(pyList);
		assertTrue(o instanceof IPersistentList);
		PersistentList pl = (PersistentList) o;
		assertTrue(pl.contains("foo"));
		assertTrue(pl.contains("bar"));
		assertEquals(2, pl.size());
	}
	
	@Test
	public void JRubyHashAndListTest()
	{
		/**
		 * spin up an action here, since instantiating Ruby types without a runtime is rather difficult.
		 */
		IObjectFactory objectFactory = FactoryUtils.getJRubyObjectFactory("NativeTypesAction");
		ITrifaceAction action = (ITrifaceAction) objectFactory.createObject();
    	Map<Object, Object> m = action.execute();
    	
    	assertTrue(m.get("map") instanceof RubyHash);
    	Object o = ClojureTypeUtils.convert(m.get("map"));
    	assertTrue(o instanceof PersistentHashMap);
    	PersistentHashMap phm = (PersistentHashMap) o;
    	assertEquals("val1",phm.get("key1"));
    	assertEquals(2l,phm.get("key2"));
    	
    	assertTrue(m.get("list") instanceof RubyArray);
    	
    	Object p = ClojureTypeUtils.convert(m.get("list"));
    	assertTrue(p instanceof PersistentList);
    	PersistentList pl = (PersistentList) p;
    	assertEquals(Arrays.asList(1l,1l,2l,3l,5l), pl);
	}
}
