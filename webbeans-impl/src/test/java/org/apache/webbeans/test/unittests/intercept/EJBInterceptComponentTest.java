/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.webbeans.test.unittests.intercept;

import java.util.List;

import javax.servlet.ServletContext;
import javax.webbeans.RequestScoped;

import junit.framework.Assert;

import org.apache.webbeans.component.AbstractComponent;
import org.apache.webbeans.context.ContextFactory;
import org.apache.webbeans.exception.WebBeansConfigurationException;
import org.apache.webbeans.test.component.intercept.InterceptedComponent;
import org.apache.webbeans.test.component.intercept.InterceptorWithSuperClassInterceptedComponent;
import org.apache.webbeans.test.component.intercept.MultipleInterceptedComponent;
import org.apache.webbeans.test.component.intercept.MultipleListOfInterceptedComponent;
import org.apache.webbeans.test.component.intercept.MultipleListOfInterceptedWithExcludeClassComponent;
import org.apache.webbeans.test.servlet.TestContext;
import org.junit.Before;
import org.junit.Test;


public class EJBInterceptComponentTest extends TestContext
{
	
	public EJBInterceptComponentTest()
	{
		super(EJBInterceptComponentTest.class.getName());
	}

	public void endTests(ServletContext ctx)
	{
		
	}

	@Before
	public void init()
	{
		super.init();
	}

	
	public void startTests(ServletContext ctx)
	{
		
	}
	
	@Test
	public void testInterceptedComponent()
	{
		WebBeansConfigurationException exc = null;
		
		try
		{
			defineSimpleWebBean(InterceptedComponent.class);
			
		}catch(WebBeansConfigurationException e)
		{
			System.out.println(e.getMessage());
			exc = e;

		}
		
		Assert.assertNull(exc);
	}

	@Test
	public void testInterceptorCalls()
	{
		clear();
		defineSimpleWebBean(InterceptedComponent.class);
		
		ContextFactory.initRequestContext(null);
		List<AbstractComponent<?>> comps = getComponents();
		
		Object object = getContext(RequestScoped.class).get(comps.get(0), true);
		
		Assert.assertTrue(object instanceof InterceptedComponent);
		
		InterceptedComponent comp = (InterceptedComponent)object;
		Object s = comp.hello(null);
		
		Assert.assertEquals(new Integer(5), s);
		
		ContextFactory.destroyRequestContext(null);
	}
	
	@Test
	public void testMultipleInterceptedComponent()
	{
		clear();
		defineSimpleWebBean(MultipleInterceptedComponent.class);
		
		ContextFactory.initRequestContext(null);
		List<AbstractComponent<?>> comps = getComponents();
		
		Object object = getContext(RequestScoped.class).get(comps.get(0), true);
		
		Assert.assertTrue(object instanceof MultipleInterceptedComponent);
		
		MultipleInterceptedComponent comp = (MultipleInterceptedComponent)object;
		Object obj = comp.intercepted();
		
		Assert.assertTrue(obj instanceof String[]);
		
		String[] arr = (String[])obj;
		
		Assert.assertEquals(2, arr.length);
		Assert.assertEquals("key", arr[0]);
		Assert.assertEquals("key2", arr[1]);
		
		ContextFactory.destroyRequestContext(null);
	}
	
	@Test
	public void testInterceptorWithSuperClassComponent()
	{
		clear();
		defineSimpleWebBean(InterceptorWithSuperClassInterceptedComponent.class);
		
		ContextFactory.initRequestContext(null);
		List<AbstractComponent<?>> comps = getComponents();
		
		Object object = getContext(RequestScoped.class).get(comps.get(0), true);
		
		Assert.assertTrue(object instanceof InterceptorWithSuperClassInterceptedComponent);
		
		InterceptorWithSuperClassInterceptedComponent comp = (InterceptorWithSuperClassInterceptedComponent)object;
		Object obj = comp.intercepted();
		
		Assert.assertTrue(obj instanceof String[]);
		
		String[] arr = (String[])obj;
		
		Assert.assertEquals(2, arr.length);
		Assert.assertEquals("key", arr[0]);
		Assert.assertEquals("key0", arr[1]);
		
		ContextFactory.destroyRequestContext(null);
	}
	
	@Test
	public void testMultipleListOfInterceptedComponent()
	{
		clear();
		defineSimpleWebBean(MultipleListOfInterceptedComponent.class);
		
		ContextFactory.initRequestContext(null);
		List<AbstractComponent<?>> comps = getComponents();
		
		Object object = getContext(RequestScoped.class).get(comps.get(0), true);
		
		Assert.assertTrue(object instanceof MultipleListOfInterceptedComponent);
		
		MultipleListOfInterceptedComponent comp = (MultipleListOfInterceptedComponent)object;
		Object obj = comp.intercepted();
		
		Assert.assertTrue(obj instanceof String);
		
		
		Assert.assertEquals("ok", (String)obj);
		
		ContextFactory.destroyRequestContext(null);
	}


	@Test
	public void testMultipleListOfInterceptedWithExcludeClassComponent()
	{
		clear();
		defineSimpleWebBean(MultipleListOfInterceptedWithExcludeClassComponent.class);
		
		ContextFactory.initRequestContext(null);
		List<AbstractComponent<?>> comps = getComponents();
		
		Object object = getContext(RequestScoped.class).get(comps.get(0), true);
		
		Assert.assertTrue(object instanceof MultipleListOfInterceptedWithExcludeClassComponent);
		
		MultipleListOfInterceptedWithExcludeClassComponent comp = (MultipleListOfInterceptedWithExcludeClassComponent)object;
		Object obj = comp.intercepted();
		
		Assert.assertTrue(obj instanceof String);
		
		
		Assert.assertEquals("value2", (String)obj);
		
		ContextFactory.destroyRequestContext(null);
	}

}
