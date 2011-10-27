package com.instrument.triface.interop;

import java.io.File;
import java.util.List;

import org.python.core.Py;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;

import com.instrument.triface.util.FileUtils;

/**
 * Jython Object Factory for coercing Jython to Java objects.
 * 
 * @author feigner
 *
 */
public class JythonObjectFactory implements IObjectFactory{

	protected final PyObject importer;
	protected final Class interfaceType;
	protected final File script;

	/**
	 * Default constructor.
	 * 
	 * @param state 			The Python system state to use when importing 
	 * @param interfaceType	The interface that the Python class implements
	 * @param moduleName		The Python module name
	 * @param className		The Python class name
	 */
	public JythonObjectFactory(PySystemState state, Class interfaceType, File script)
	{
		this.interfaceType = interfaceType;
		this.importer = state.getBuiltins().__getitem__(Py.newString("__import__"));
		this.script = script;
	}

	/**
	 * Convenience constructor for when the module and classname are the same.
	 * 
	 * @param interfaceType the interface that the python class implements
	 * @param className the class name to load
	 */
	public JythonObjectFactory(Class interfaceType, File script)
	{
		this(Py.getSystemState(), interfaceType, script);
	}

	/**
	 * Create an object based on the defined interface.
	 * 
	 * @return an object!
	 * @throws Exception 
	 */
	public Object createObject() {
		Object ret = null;
		try
		{
			Py.getSystemState().path.append(new PyString(FileUtils.getPath(this.script)));
			String moduleName = FileUtils.getFileNameSansExtension(this.script);
			PyObject module = importer.__call__(Py.newString(moduleName));
			ret = module.__getattr__(moduleName).__call__().__tojava__(interfaceType);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Unable to create " + interfaceType + " object from: " + this.script.getPath(), e);
		}
		return ret;
	}
}