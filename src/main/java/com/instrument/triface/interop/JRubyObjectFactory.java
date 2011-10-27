package com.instrument.triface.interop;

import java.io.File;
import java.util.List;

import org.jruby.embed.ScriptingContainer;

import com.instrument.triface.util.FileUtils;

/**
 * JRuby Object Factory for coercing JRuby to Java objects.
 * 
 * @author feigner
 *
 */
public class JRubyObjectFactory implements IObjectFactory {

	protected final ScriptingContainer scriptingContainer;
	protected final Class interfaceType;
	protected final File script;

	/**
	 * Primary constructor.
	 * 
	 * @param scriptingContainer the scripting container to run against
	 * @param interfaceType the interface that the underlying object implements
	 * @param scriptName the name of the script to be executed
	 */
	public JRubyObjectFactory(ScriptingContainer scriptingContainer, Class interfaceType, File script)
	{
		this.scriptingContainer = scriptingContainer;
		this.interfaceType = interfaceType;
		this.script = script;
	}

	/**
	 * Convenience constructor, creates a new scripting container.
	 * 
	 * @param interfaceType the interface that the underlying object implements
	 * @param scriptName the name of the script to be executed
	 */
	public JRubyObjectFactory(Class interfaceType, File script)
	{
		this(new ScriptingContainer(),interfaceType,script);
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
			scriptingContainer.getLoadPaths().add(FileUtils.getPath(this.script));
			scriptingContainer.runScriptlet("load '" + this.script.getName()  + "'");
			ret = scriptingContainer.runScriptlet(FileUtils.getFileNameSansExtension(this.script) + ".new");
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to create " + interfaceType + " object from: " + this.script, e);
		}
		return ret;
	}
}
