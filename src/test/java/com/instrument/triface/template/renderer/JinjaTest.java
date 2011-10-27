package com.instrument.triface.template.renderer;

import java.io.File;

import org.junit.Test;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.interop.JythonObjectFactory;
import com.instrument.triface.template.renderer.ITrifaceTemplateRenderer;

public class JinjaTest {
	
	@Test
	public void JinjaTest()
	{		
		File f = new File("src/test/python/" + "TemplateTest" + ".py");
		IObjectFactory objectFactory = new JythonObjectFactory(ITrifaceTemplateRenderer.class, f);
		
		ITrifaceTemplateRenderer engine = (ITrifaceTemplateRenderer) objectFactory.createObject();
		System.out.println(engine.render(null, null));
	}
}
