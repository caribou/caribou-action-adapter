package com.instrument.triface.template.renderer;

import java.io.File;
import java.util.Map;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.interop.JRubyObjectFactory;

public class ERBTemplateRenderer implements ITrifaceTemplateRenderer {

	IObjectFactory objectFactory;
	ITrifaceTemplateRenderer ERBRenderer;
	
	public ERBTemplateRenderer()
	{
		File f = new File("src/test/ruby/" + "TemplateTest" + ".rb");
		objectFactory = new JRubyObjectFactory(ITrifaceTemplateRenderer.class, f);
		ERBRenderer = (ITrifaceTemplateRenderer) objectFactory.createObject();		
	}
	
	@Override
	public String render(Map<Object, Object> objectMap, File template) {
		return ERBRenderer.render(objectMap, template);
	}

}
