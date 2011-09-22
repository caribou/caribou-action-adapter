package com.instrument.triface.action;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import clojure.lang.IPersistentList;
import clojure.lang.IPersistentMap;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentHashMap;

import com.instrument.triface.IObjectFactory;
import com.instrument.triface.action.ITrifaceAction.MapType;
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

}
