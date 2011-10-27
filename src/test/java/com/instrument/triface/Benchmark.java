package com.instrument.triface;

import com.instrument.triface.action.ITrifaceAction;
import com.instrument.triface.interop.IObjectFactory;
import com.instrument.triface.util.FactoryUtils;

/**
 * Dumb test benchmarks
 * 
 * @author feigner
 *
 */
public class Benchmark {

	private void simpleJRubyTest()
	{
		System.out.print("JR: ");
		long init = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			long start = System.currentTimeMillis();
			IObjectFactory factory = FactoryUtils.getJRubyObjectFactory("DummyAction");
			ITrifaceAction action = (ITrifaceAction) factory.createObject();
			action.execute();
			long end = System.currentTimeMillis();
			System.out.print(end - start + ", ");
		}
		System.out.println();
	}
	
	private void simpleJRubyTest2()
	{
		System.out.print("JR: ");
		long init = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			long start = System.currentTimeMillis();
			IObjectFactory factory = FactoryUtils.getJRubyObjectFactory("NativeTypesAction");
			ITrifaceAction action = (ITrifaceAction) factory.createObject();
			action.execute();
			long end = System.currentTimeMillis();
			System.out.print(end - start + ", ");
		}
		System.out.println();
		
	}	
	
	private void simpleJythonTest()
	{
		System.out.print("JY: ");
		long init = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			long start = System.currentTimeMillis();
			IObjectFactory factory = FactoryUtils.getJythonObjectFactory("DummyAction");
			ITrifaceAction action = (ITrifaceAction) factory.createObject();
			action.execute();
			long end = System.currentTimeMillis();
			System.out.print(end - start + ", ");
		}
		System.out.println();
	}
	
	private void simpleJythonTest2()
	{
		System.out.print("JY: ");
		long init = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			long start = System.currentTimeMillis();
			IObjectFactory factory = FactoryUtils.getJythonObjectFactory("NativeTypesAction");
			ITrifaceAction action = (ITrifaceAction) factory.createObject();
			action.execute();
			long end = System.currentTimeMillis();
			System.out.print(end - start + ", ");
		}
		System.out.println();
	}	
	
	private void simpleJSTest()
	{
		System.out.print("JS: ");
		long init = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			long start = System.currentTimeMillis();
			IObjectFactory factory = FactoryUtils.getJSObjectFactory("DummyAction");
			ITrifaceAction action = (ITrifaceAction) factory.createObject();
			action.execute();
			long end = System.currentTimeMillis();
			System.out.print(end - start + ", ");
		}
		System.out.println();
	}	
	
	private void simpleJSTest2()
	{
		System.out.print("JS: ");
		long init = System.currentTimeMillis();
		for(int i = 0; i < 100; i++)
		{
			long start = System.currentTimeMillis();
			IObjectFactory factory = FactoryUtils.getTrifaceJSObjectFactory("NativeTypesAction");
			ITrifaceAction action = (ITrifaceAction) factory.createObject();
			action.execute();
			long end = System.currentTimeMillis();
			System.out.print(end - start + ", ");
		}
		System.out.println();
	}			
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Benchmark m = new Benchmark();
		m.simpleJRubyTest();
		m.simpleJRubyTest2();
		m.simpleJythonTest();
		m.simpleJythonTest2();
		m.simpleJSTest();
		m.simpleJSTest2();
	}

}
