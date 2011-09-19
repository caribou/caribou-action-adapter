package com.instrument.triface;

import java.util.List;

import org.python.core.Py;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;

/**
 * Jython Object Factory for coercing Jython to Java objects.
 * 
 * @author feigner
 *
 */
public class JythonObjectFactory implements IObjectFactory{

	private final PyObject importer;
	private final Class interfaceType;
	private final String moduleName;
	private final String className;

	/**
	 * Default constructor.
	 * 
	 * @param state 			The Python system state to use when importing 
	 * @param interfaceType	The interface that the Python class implements
	 * @param moduleName		The Python module name
	 * @param className		The Python class name
	 */
	public JythonObjectFactory(PySystemState state, Class interfaceType, String moduleName, String className)
	{
		this.interfaceType = interfaceType;     
		this.importer = state.getBuiltins().__getitem__(Py.newString("__import__"));
		this.moduleName = moduleName;
		this.className = className;
	}

	/**
	 * Convenience constructor. Uses the default system state.
	 * 
	 * @param interfaceType	The interface that the Python class implements.
	 * @param moduleName		The Python module name.
	 * @param className		The Python class name.
	 */
	public JythonObjectFactory(Class interfaceType, String moduleName, String className) 
	{    
		this(Py.getSystemState(),interfaceType,moduleName, className);
	}

	/**
	 * Convenience constructor for when the module and classname are the same.
	 * 
	 * @param interfaceType the interface that the python class implements
	 * @param className the class name to load
	 */
	public JythonObjectFactory(Class interfaceType, String className)
	{
		this(interfaceType, className, className);
	}

	/**
	 * Convenience constructor for when the module and classname are the same,
	 * with a custom system state.
	 * 
	 * @param interfaceType the interface that the python class implements
	 * @param className the class name to load
	 */
	public JythonObjectFactory(PySystemState state, Class interfaceType, String className)
	{
		this(state, interfaceType, className, className);
	}

	/**
	 * Create an object based on the defined interface.
	 * 
	 * @return an object!
	 */
	public Object createObject() {
		PyObject module = importer.__call__(Py.newString(moduleName));
		return module.__getattr__(className).__call__().__tojava__(interfaceType);
	}

	/**
	 * Add a single lookup path for the factory to use when
	 * resolving a script.
	 * 
	 * @param path the path to search
	 */
	public void addLoadPath(String path) {
		Py.getSystemState().path.append(new PyString(path));
	}

	/**
	 * Add a list of lookup paths for the factory to use when
	 * resolving a script.
	 * 
	 * @param paths the paths to search
	 */
	public void addLoadPaths(List<String> paths) {
		Py.getSystemState().path.append(new PyList(paths));
	}

}