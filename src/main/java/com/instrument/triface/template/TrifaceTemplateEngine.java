package com.instrument.triface.template;

import java.io.File;
import java.util.Map;

import com.instrument.triface.template.renderer.ERBTemplateRenderer;
import com.instrument.triface.template.renderer.FreemarkerTemplateRenderer;
import com.instrument.triface.template.renderer.ITrifaceTemplateRenderer;
import com.instrument.triface.template.renderer.JinjaTemplateRenderer;
import com.instrument.triface.util.FileUtils;

/**
 * Triface template engine. Supports FreeMarker, ERB and Jinja
 * 
 * TODO: ERB / Jinja...
 * 
 * @author feigner
 */
public final class TrifaceTemplateEngine {
	
	/* hold local references to enginges to avoid reinit... */
	private static ITrifaceTemplateRenderer freemarkerEngine;
	private static ITrifaceTemplateRenderer ERBEngine;
	private static ITrifaceTemplateRenderer jinjaEngine;
	
	/**
	 * This lameness is due to the way that FreeMarker loads templates -- needs
	 * to be set a priori.
	 */
	private File baseTemplateDirectory;
	
	/**
	 * Default constructor. Be sure to set the base template directory.
	 */
	public TrifaceTemplateEngine(){}
	
	/**
	 * Convenience constructor.
	 * 
	 * @param baseTemplateDirectory the base template directory
	 */
	public TrifaceTemplateEngine(File baseTemplateDirectory)
	{
		this.baseTemplateDirectory = baseTemplateDirectory;
	}
	
	public String render(Map<Object, Object> objectMap, File template)
	{
		// determine the appropriate template renderer
		ITrifaceTemplateRenderer renderer = null;
		String extension = FileUtils.getFileExtension(template);
		if(extension.equalsIgnoreCase(".rb"))
		{
			if(ERBEngine == null)
			{
				ERBEngine = new ERBTemplateRenderer();
			}
			renderer = ERBEngine;
		}
		else if(extension.equalsIgnoreCase(".py"))
		{
			if(jinjaEngine == null)
			{
				jinjaEngine = new JinjaTemplateRenderer();
			}
			renderer = jinjaEngine;
		}
		else if(extension.equalsIgnoreCase(".ftl"))
		{
			if(freemarkerEngine == null)
			{
				freemarkerEngine = new FreemarkerTemplateRenderer(baseTemplateDirectory);
			}
			renderer = freemarkerEngine;
		}

		// invoke the determined renderer		
		if(renderer != null)
		{
			return renderer.render(objectMap, template);
		}
		else
		{
			// unable to find renderer for the specified template.
			// TODO: do something
			return null;
		}
	}
	
	public void setBaseTemplateDirectory(File baseTemplateDirectory)
	{
		this.baseTemplateDirectory = baseTemplateDirectory;
	}	
	
}
