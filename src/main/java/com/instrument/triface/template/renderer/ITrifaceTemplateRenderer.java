package com.instrument.triface.template.renderer;

import java.io.File;
import java.util.Map;

public interface ITrifaceTemplateRenderer {
	public String render(Map<Object, Object> objectMap, File template);
}
