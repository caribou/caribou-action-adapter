package com.instrument.triface;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * Jython Object Factory for coercing Jython to Java objects.
 * 
 */
public class JythonObjectFactory {

 private final Class interfaceType;
 private final PyObject clasz;
 
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
     PyObject importer = state.getBuiltins().__getitem__(Py.newString("__import__"));
     PyObject module = importer.__call__(Py.newString(moduleName));
     clasz = module.__getattr__(className);
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
     return clasz.__call__().__tojava__(interfaceType);
 }

/**
 * Create an object with a variable number of arguments / keywords
 * 
 * @param args argument array
 * @param keywords keyword array
 * @return an object!
 */
 public Object createObject(Object args[], String keywords[]) {
     PyObject convertedArgs[] = new PyObject[args.length];
     for (int i = 0; i < args.length; i++) {
         convertedArgs[i] = Py.java2py(args[i]);
     }
     return clasz.__call__(convertedArgs, keywords).__tojava__(interfaceType);
 }

 /**
  * Convenience method for passing n-number of arguments to the 
  * object constructor
  * 
  * @param args arguments, son.
  * @return an object!
  */
 public Object createObject(Object... args) {
     return createObject(args, Py.NoKeywords);
 }

}