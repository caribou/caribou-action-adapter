package com.instrument.triface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
	protected File script;

	/**
	 * Primary constructor
	 * 
	 * @param interfaceType the interface that the underlying object implements
	 * @param scriptName the script to be executed
	 */
	public JSObjectFactory(Class interfaceType, File script)
	{
		this.interfaceType = interfaceType;     
		this.script = script;
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
			
			BufferedReader reader = new BufferedReader( new FileReader(this.script));
			script = context.compileReader(reader, "source", 0, null);
			Object object = script.exec(context, scriptable);
			generator = Context.jsToJava(object, this.interfaceType);
			
		} catch (Exception e)
		{
			throw new RuntimeException("Unable to create " + interfaceType + " object from: " + this.script.getPath(), e);
		}
		return generator;
	}
}
