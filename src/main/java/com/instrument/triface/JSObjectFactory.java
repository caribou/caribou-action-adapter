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

	protected final Context context = Context.enter();
	protected final Scriptable scriptable = new ImporterTopLevel(context);
	protected Class interfaceType;
	protected String scriptName;

	public JSObjectFactory(Class interfaceType, String scriptName)
	{
		this.interfaceType = interfaceType;     
		this.scriptName = scriptName;
	}

	@Override
	public Object createObject() {
		Object generator = null;
		try {
			BufferedReader reader;
			Script myScript;
			
			// TODO: don't hardcode this path -- figure a way to resolve the scriptname automagically.
			reader = new BufferedReader( new FileReader("src/test/js/" + scriptName + ".js") );
			myScript = context.compileReader(reader, "source", 0, null);
			Object object = myScript.exec(context, scriptable);
			generator = Context.jsToJava(object, this.interfaceType);
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
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
