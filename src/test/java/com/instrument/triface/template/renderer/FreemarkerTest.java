package com.instrument.triface.template.renderer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerTest {
  
  @Test
  public void ftlTest() throws IOException, TemplateException
  {
    
	 /* this should be done ONLY ONCE in the whole application life-cycle:   */   
    Configuration cfg = new Configuration();
    cfg.setDirectoryForTemplateLoading(new File("src/test/templates/"));
    cfg.setObjectWrapper(new DefaultObjectWrapper());  
    
    Template temp = cfg.getTemplate("test.ftl");
    
    StringWriter writer = new StringWriter();
    
    Map map = new HashMap<String, Object>();
    map.put("foo", "bar");
    
    temp.process(map, writer);
    
    assertEquals("foo:bar", writer.toString());
  }
}
