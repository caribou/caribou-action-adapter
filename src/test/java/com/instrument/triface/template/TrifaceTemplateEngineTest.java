package com.instrument.triface.template;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TrifaceTemplateEngineTest {

	TrifaceTemplateEngine te;
	
	@Before
	public void setup()
	{
		te = new TrifaceTemplateEngine();
		te.setBaseTemplateDirectory(new File("src/test/templates/"));
	}
	
	@Test
	public void freemarkerTest()
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("foo", "bar");
		
		String ret = te.render(map, new File("src/test/templates/test.ftl"));
		assertEquals("foo:bar", ret);
	}

	// TODO
	@Ignore
	public void ERBTest()
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("foo", "bar");
		
		String ret = te.render(map, new File("src/test/templates/test.rb"));
		assertEquals("foo:bar", ret);
	}
	
	// TODO
	@Ignore
	public void JinjaTest()
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("foo", "bar");
		
		String ret = te.render(map, new File("src/test/templates/test.py"));
		assertEquals("foo:bar", ret);
	}
}
