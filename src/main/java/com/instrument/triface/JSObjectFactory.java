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
	protected String scriptName;
	protected List<String> searchPaths;

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
		
		this.searchPaths = new ArrayList<String>();
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
			
			BufferedReader reader = new BufferedReader( new FileReader(resolveScriptPath(scriptName)));
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
	 * Since rhino doesn't have any sort of classloading mechanism,
	 * we have this. 
	 * 
	 * TODO: this is crazy inefficient. maybe force that the 
	 * scripts be on the classpath?
	 * 
	 * @param scriptName the name of the script to resolve
	 * @return String the path to the file. 
	 */
	protected String resolveScriptPath(String scriptName)
	{
		String filePath;
		for(String path : this.searchPaths)
		{
			filePath = path + "/" + scriptName + ".js";
			if(new File(filePath).isFile())
			{
				return filePath;
			}
		}
		return "";
	}

	/**
	 * Add a single lookup path for the factory to use when
	 * resolving a script.
	 * 
	 * @param path the path to search
	 */	
	public void addLoadPath(String path) {
		this.searchPaths.add(path);
	}

	/**
	 * Add a list of lookup paths for the factory to use when
	 * resolving a script.
	 * 
	 * @param paths the paths to search
	 */
	public void addLoadPaths(List<String> paths) {
		this.searchPaths.addAll(paths);
	}

}
