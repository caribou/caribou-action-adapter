package com.instrument.triface.template.renderer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerTemplateRenderer implements ITrifaceTemplateRenderer {

	private Configuration cfg;
	private File baseTemplateDirectory;
	
	public FreemarkerTemplateRenderer(File baseTemplateDirectory){
		try {
			this.baseTemplateDirectory = baseTemplateDirectory;
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(baseTemplateDirectory);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
		} catch (IOException e) {
			// TODO: do something with this
			e.printStackTrace();
		}		
	}
	
	public String render(Map<Object, Object> objectMap, File template)
	{
		StringWriter writer = new StringWriter();
		try {
			Template temp = cfg.getTemplate(calculateTemplateName(template));
			temp.process(objectMap, writer);
		} catch (IOException e) {
			// TODO: do something with this
			e.printStackTrace();
		} catch (TemplateException e)
		{
			// TODO: do something with this
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	private String calculateTemplateName(File template)
	{
		return template.getPath().replace(baseTemplateDirectory.getPath() + "/", "");
	}

}