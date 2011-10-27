package com.instrument.triface.template.renderer;

import java.io.File;
import java.util.Map;

import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.interop.JRubyObjectFactory;
import com.instrument.triface.interop.JythonObjectFactory;

public class JinjaTemplateRenderer implements ITrifaceTemplateRenderer {
	
	IObjectFactory objectFactory;
	ITrifaceTemplateRenderer JinjaRenderer;
	
	public JinjaTemplateRenderer()
	{
		File f = new File("src/test/python/" + "TemplateTest" + ".py");
		objectFactory = new JythonObjectFactory(ITrifaceTemplateRenderer.class, f);
		JinjaRenderer = (ITrifaceTemplateRenderer) objectFactory.createObject();		
	}

	@Override
	public String render(Map<Object, Object> objectMap, File template) {
		return JinjaRenderer.render(objectMap, template);
	}

}
