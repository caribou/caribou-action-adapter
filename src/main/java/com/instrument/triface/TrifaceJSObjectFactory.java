package com.instrument.triface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;

/**
 * Triface-specific version of the JSObjectFactory.
 * 
 * @author feigner
 *
 */
public class TrifaceJSObjectFactory extends JSObjectFactory {
	
	public TrifaceJSObjectFactory(Class interfaceType, String scriptName) {
		super(interfaceType, scriptName);
	}

	/**
	 * Create a JS -> Java object. Handles running some triface-action specific scripts
	 * prior to object creation. Workaround since extending / inheriting Java classes
	 * within Rhino Javascript is problematic.
	 */
	@Override
	public Object createObject() {
		Object generator = null;
		try {
			BufferedReader reader;
			Script myScript;
						
			reader = new BufferedReader( new FileReader("lib/js/TrifaceJSActionPre.js") );
			myScript = context.compileReader(reader, "source", 0, null);
			myScript.exec(context, scriptable);

			// TODO: don't hardcode this path -- figure a way to resolve the scriptname automagically.
			reader = new BufferedReader( new FileReader("src/test/js/" + scriptName + ".js") );
			myScript = context.compileReader(reader, "source", 0, null);
			myScript.exec(context, scriptable);
			
			reader = new BufferedReader( new FileReader("lib/js/TrifaceJSActionPost.js") );
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
}
