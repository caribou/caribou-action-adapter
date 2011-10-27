package com.instrument.triface.template.renderer;

import java.io.File;

import org.junit.Test;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.interop.JRubyObjectFactory;
import com.instrument.triface.template.renderer.ITrifaceTemplateRenderer;

public class ERBTest {

	@Test
	public void ERBTest()
	{
		File f = new File("src/test/ruby/" + "TemplateTest" + ".rb");
		IObjectFactory objectFactory = new JRubyObjectFactory(ITrifaceTemplateRenderer.class, f);
		
		ITrifaceTemplateRenderer engine = (ITrifaceTemplateRenderer) objectFactory.createObject();
		System.out.println(engine.render(null, null));
	}
	
}
