package com.instrument.triface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;

/**
 * Triface-specific version of the JSObjectFactory. Used to build
 * triface action objects only.
 * 
 * Reasoning: Once we create an instance of a java class in JS,
 * we can't further extend it. 
 * 
 * Therefore, if we want to define some default JS methods that don't
 * need to be copy and pasted all over the place, we define them 
 * in a generic object, then override them downstream, finally using
 * the Rhino JavaAdapter to force it into the appropriate java type.
 * 
 * Gross hacky fun.
 * 
 * @author feigner
 *
 */
public class TrifaceJSObjectFactory extends JSObjectFactory {
	
	/**
	 * Primary constructor
	 * 
	 * @param interfaceType the interface that the underlying object implements
	 * @param scriptName the script to be executed
	 */
	public TrifaceJSObjectFactory(Class interfaceType, String scriptName) {
		super(interfaceType, scriptName);
	}

	/**
	 * Create a JS -> Java object. Handles running some triface-action specific scripts
	 * prior to object creation. Workaround since extending / inheriting Java classes
	 * within Rhino Javascript is problematic.
	 */
	@Override
	public Object createObject() {

		Object generator = null;
		try {
			BufferedReader reader;
			Script myScript;
			
			reader = new BufferedReader(new InputStreamReader(
				     getClass().getClassLoader().getResourceAsStream("TrifaceJSActionPre.js")));
			
			myScript = context.compileReader(reader, "source", 0, null);
			myScript.exec(context, scriptable);

			reader = new BufferedReader( new FileReader(resolveScriptPath(scriptName)));
			myScript = context.compileReader(reader, "source", 0, null);
			myScript.exec(context, scriptable);
			
			reader = new BufferedReader(new InputStreamReader(
				    getClass().getClassLoader().getResourceAsStream("TrifaceJSActionPost.js")));
			myScript = context.compileReader(reader, "source", 0, null);
			Object object = myScript.exec(context, scriptable);
			
			generator = Context.jsToJava(object, this.interfaceType);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Unable to create " + interfaceType + " object from: " + scriptName, e);
		}
		return generator;
	}
}
