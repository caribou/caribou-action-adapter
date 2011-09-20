package com.instrument.triface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

/**
 * JavaScript Object Factory for coercing JS to Java objects.
 * 
 * @author feigner
 *
 */
public class JSObjectFactory implements IObjectFactory {

	protected final Context context = Context.enter();
	protected final Scriptable scriptable = new ImporterTopLevel(context);
	protected Class interfaceType;
	protected String scriptName;

	/**
	 * Primary constructor
	 * 
	 * @param interfaceType the interface that the underlying object implements
	 * @param scriptName the script to be executed
	 */
	public JSObjectFactory(Class interfaceType, String scriptName)
	{
		this.interfaceType = interfaceType;     
		this.scriptName = scriptName;
	}

	/**
	 * Invoke the specified script, creating and returning a JS object.
	 * Make sure to set load paths before invoking.
	 * 
	 * @Return Object an Object, or NULL of object could not be coerced
	 */
	public Object createObject() {
		Object generator = null;
		try {
			Script script;
			
			// TODO: don't hardcode this path -- figure a way to resolve the scriptname automagically.
			BufferedReader reader = new BufferedReader( new FileReader("src/test/js/" + scriptName + ".js") );
			script = context.compileReader(reader, "source", 0, null);
			Object object = script.exec(context, scriptable);
			generator = Context.jsToJava(object, this.interfaceType);
			
		} catch (Exception e)
		{
			throw new RuntimeException("Unable to create " + interfaceType + " object from: " + scriptName, e);
		}
		return generator;
	}

	/**
	 * Add a single lookup path for the factory to use when
	 * resolving a script.
	 * 
	 * @param path the path to search
	 */	
	public void addLoadPath(String path) {
		// TODO: make this work

	}

	/**
	 * Add a list of lookup paths for the factory to use when
	 * resolving a script.
	 * 
	 * @param paths the paths to search
	 */
	public void addLoadPaths(List<String> paths) {
		// TODO: make this work

	}

}
