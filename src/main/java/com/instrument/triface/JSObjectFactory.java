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

public class JSObjectFactory implements IObjectFactory {

	private final Context context = Context.enter();
	private final Scriptable scriptable = new ImporterTopLevel(context);
	private Class interfaceType;
	private String scriptName;

	public JSObjectFactory(Class interfaceType, String scriptName)
	{
		this.interfaceType = interfaceType;     
		this.scriptName = scriptName;
	}

	@Override
	public Object createObject() {
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader( new FileReader("src/test/js/" + scriptName + ".js") );
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Script myScript = null;
		try {
			myScript = context.compileReader(reader, "source", 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object = myScript.exec(context, scriptable);
		Object generator = Context.jsToJava(object, this.interfaceType);
		return generator;
	}

	@Override
	public void addLoadPath(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addLoadPaths(List<String> paths) {
		// TODO Auto-generated method stub

	}

}
