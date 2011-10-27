package com.instrument.triface.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jruby.RubyArray;
import org.jruby.RubyHash;
import org.mozilla.javascript.NativeArray;
import org.python.core.PyDictionary;
import org.python.core.PyList;

import clojure.lang.IPersistentList;
import clojure.lang.IPersistentMap;
import clojure.lang.PersistentHashMap;
import clojure.lang.PersistentList;

/**
 * Utility class to convert various types to their clojure-specific implementations.
 * This could get ugly.
 * 
 * @author feigner
 *
 */
public class ClojureTypeUtils implements ITypeUtils {
	
	/**
	 * Attempt to convert the specified object into a clojure-compatable object.
	 * 
	 * @param o the object to convert
	 * @return Object The clojure-specific corollary to the input object.
	 *         Returns the original object if no conversion could be made
	 */
	public static Object convert(Object o)
	{
		if(CLOJURE_CONVERSION_MAP.containsKey(o.getClass()))
		{
			return transform(o, CLOJURE_CONVERSION_MAP.get(o.getClass()));
		}
		else
		{
			return o;
		} 
	}
	
	/**
	 * Apply the transformation function
	 * 
	 * @param from the object to transform
	 * @param func the transformation function
	 */
	private static <F, T> Object transform(F from, Function<? super F, ? extends T> func)
	{
		return func.apply(from);
	}
	
	/**
	 * Determine if there exists a conversion for a specified type.
	 * 
	 * @param o the type to check
	 * @return boolean true if a conversion exists
	 */
	public static boolean hasConversion(Object o)
	{
		return CLOJURE_CONVERSION_MAP.containsKey(o.getClass());
	}
	
	
	/**
	 * Generic conversion functions
	 */
	private static final Function<Object, Object> MAP_TO_PERSISTENTHASHMAP = new Function<Object, Object>() {
		public IPersistentMap apply(Object in) {
    		return PersistentHashMap.create((Map<Object, Object>)in);
    	}
	};
	
	private static final Function<Object, Object> LIST_TO_PERSISTENTLIST = new Function<Object, Object>() {
		public IPersistentList apply(Object in) {
			return PersistentList.create((List<Object>) in);
    	}
	};
	
	/**
	 * Ruby specific conversions
	 */
	private static final Function<Object, Object> RUBYARRAY_TO_PERSISTENTLIST = new Function<Object, Object>() {
		public IPersistentList apply(Object in) {
			// when creating a persistentList from a RubyArray, a nil object gets created
			// in position 0. Hack workaround.
			// TODO: fix
			PersistentList pl = (PersistentList)PersistentList.create((List<Object>) in);
			return pl.pop();
    	}
	};		
	
	/**
	 * JS specific conversions
	 */
	private static final Function<Object, Object> NATIVEARRAY_TO_PERSISTENTLIST = new Function<Object, Object>() {
		public IPersistentList apply(Object in) {
			
			NativeArray arr = (NativeArray) in;
			Object [] array = new Object[(int) arr.getLength()];
			for (Object o : arr.getIds()) {
			    int index = (Integer) o;
			    Object val = arr.get(index, null);
			    
			    // TODO: fix this
			    // there is a weird issue where some numbers within a NativeArray will be integers, the others,
			    // doubles
			    if(val instanceof Integer)
			    {
			    	val = (double)((Integer)val);
			    }
			    array[index] = val;
			}
			
			PersistentList pl = (PersistentList) PersistentList.create(Arrays.asList(array));
			return pl;
    	}
	};
	
	/**
	 * Build the conversion map based on class type
	 */
	private static final Map<Class<?>, Function<Object, Object>> CLOJURE_CONVERSION_MAP = buildClojureConversionMap();
	private static Map<Class<?>,Function<Object, Object>> buildClojureConversionMap()
	{
	    Map<Class<?>, Function<Object, Object>> map = new HashMap<Class<?>, Function<Object, Object>>();
	    
	    // py
	    map.put(PyDictionary.class, MAP_TO_PERSISTENTHASHMAP);
	    map.put(PyList.class, LIST_TO_PERSISTENTLIST);
	    
	    // ruby
	    map.put(RubyHash.class, MAP_TO_PERSISTENTHASHMAP);
	    map.put(RubyArray.class, RUBYARRAY_TO_PERSISTENTLIST);
	    
	    // js
	    map.put(NativeArray.class, NATIVEARRAY_TO_PERSISTENTLIST);
	    
	    //java
	    map.put(HashMap.class, MAP_TO_PERSISTENTHASHMAP);
	    
	    return map;
	}
}
