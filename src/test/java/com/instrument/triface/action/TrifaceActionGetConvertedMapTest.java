package com.instrument.triface.action;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import clojure.lang.IPersistentList;
import clojure.lang.IPersistentMap;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentHashMap;

import com.instrument.triface.action.ITrifaceAction.MapType;
import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.util.FactoryUtils;

/**
 * Action converted map tests
 * 
 * @author feigner
 *
 */
public class TrifaceActionGetConvertedMapTest {
	
	protected IObjectFactory objectFactory;
	protected ITrifaceAction action;
	
	@Test
	public void testJRubyToClojureConvertedMap()
	{
		objectFactory = FactoryUtils.getJRubyObjectFactory("NativeTypesAction");
		action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMap();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentArrayMap.EMPTY);
		action.execute();
		testToClojureConvertedMap();
	}
	
	@Test
	public void testJRubyToClojureConvertedMapNested()
	{
		objectFactory = FactoryUtils.getJRubyObjectFactory("NestedTypesAction");
		action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMapNested();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentArrayMap.EMPTY);
		action.execute();
		testToClojureConvertedMapNested();
	}	
	
	@Test
	public void testJythonToClojureConvertedMap()
	{
		objectFactory = FactoryUtils.getJythonObjectFactory("NativeTypesAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMap();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentArrayMap.EMPTY);
		action.execute();
		testToClojureConvertedMap();
	}	
	
	@Test
	public void testJythonToClojureConvertedMapNested()
	{
		objectFactory = FactoryUtils.getJythonObjectFactory("NestedTypesAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMapNested();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentArrayMap.EMPTY);
		action.execute();
		testToClojureConvertedMapNested();
	}		
	
	@Test
	public void testJSToClojureConvertedMap()
	{
		objectFactory = FactoryUtils.getTrifaceJSObjectFactory("NativeTypesAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMap();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentArrayMap.EMPTY);
		action.execute();
		testToClojureConvertedMap();
	}	
	
	@Test
	public void testJSToClojureConvertedMapNested()
	{
		objectFactory = FactoryUtils.getTrifaceJSObjectFactory("NestedTypesAction");
    	action = (ITrifaceAction) objectFactory.createObject();
		action.execute();
		testToClojureConvertedMapNested();
		
		// reinit action
		action = (ITrifaceAction) objectFactory.createObject();
		action.setMap(PersistentArrayMap.EMPTY);
		action.execute();
		testToClojureConvertedMapNested();
	}		
	
	/**
	 * Ensure that basic types are converted appropriately.
	 */
	public void testToClojureConvertedMap()
	{	
		Map<Object, Object> m = action.getConvertedMap(MapType.CLOJURE);
		assertTrue(m instanceof IPersistentMap);
		
		// check converted types
		assertTrue(m.get("map") instanceof IPersistentMap);
		assertTrue(m.get("list") instanceof IPersistentList);
		
		// check non-converted types
		assertTrue(m.get("string") instanceof String);
	}
	
	/**
	 * Ensure that nested types (maps, lists) get converted (and ordered) appropriately
	 */
	public void testToClojureConvertedMapNested()
	{	
		Map<Object, Object> m = action.getConvertedMap(MapType.CLOJURE);
		assertTrue(m instanceof IPersistentMap);
		
		// check converted types
		assertTrue(m.get("map") instanceof IPersistentMap);
		Map outerMap = (Map) m.get("map");	
		assertEquals(2,outerMap.size());
		assertTrue(outerMap.get("key1") instanceof IPersistentMap);
		assertTrue(outerMap.get("key2") instanceof IPersistentMap);
		
		assertTrue(m.get("list") instanceof IPersistentList);
		List outerList = (List) m.get("list");
		assertEquals(2, outerList.size());
		assertTrue(outerList.get(0) instanceof IPersistentList);
		List innerList = (List) outerList.get(0);
		assertEquals("a",innerList.get(0));
		assertEquals("b",innerList.get(1));
		assertTrue(outerList.get(1) instanceof IPersistentList);
		innerList = (List) outerList.get(1);
		assertEquals("c",innerList.get(0));
		assertEquals("d",innerList.get(1));
		
		
	}
}