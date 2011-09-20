package com.instrument.triface;

import java.util.List;

import org.jruby.embed.EvalFailedException;
import org.jruby.embed.ScriptingContainer;

/**
 * JRuby Object Factory for coercing JRuby to Java objects.
 * 
 * @author feigner
 *
 */
public class JRubyObjectFactory implements IObjectFactory {

	private final ScriptingContainer scriptingContainer;
	private final Class interfaceType;
	private final String scriptName;

	public JRubyObjectFactory(ScriptingContainer scriptingContainer, Class interfaceType, String scriptName)
	{
		this.scriptingContainer = scriptingContainer;
		this.interfaceType = interfaceType;
		this.scriptName = scriptName;
	}

	public JRubyObjectFactory(Class interfaceType, String scriptName)
	{
		this(new ScriptingContainer(),interfaceType,scriptName);
	}

	/**
	 * Invoke the specified script, creating and returning a JRuby object.
	 * Make sure to set load paths before invoking.
	 * 
	 * @return NULL if object could not be coerced.
	 */
	public Object createObject()
	{
		Object ret = null;
		try
		{
			// TODO: this is ridiculously slow
			scriptingContainer.runScriptlet("load '" + this.scriptName + ".rb'");
			ret = scriptingContainer.runScriptlet(this.scriptName + ".new");
		}
		catch(EvalFailedException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ret;
	}

	/**
	 * Add a single lookup path for the factory to use when
	 * resolving a script.
	 * 
	 * @param path the path to search
	 */
	public void addLoadPath(String path)
	{
		this.scriptingContainer.getLoadPaths().add(path);
	}

	/**
	 * Add a list of lookup paths for the factory to use when
	 * resolving a script.
	 * 
	 * @param paths the paths to search
	 */
	public void addLoadPaths(List<String> paths)
	{
		this.scriptingContainer.setLoadPaths(paths);
	}

}
