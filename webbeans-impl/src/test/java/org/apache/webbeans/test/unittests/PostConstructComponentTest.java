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
package org.apache.webbeans.test.unittests;

import java.util.List;

import javax.servlet.ServletContext;
import javax.webbeans.RequestScoped;
import javax.webbeans.manager.Manager;

import junit.framework.Assert;

import org.apache.webbeans.component.AbstractComponent;
import org.apache.webbeans.component.ComponentImpl;
import org.apache.webbeans.container.ManagerImpl;
import org.apache.webbeans.context.ContextFactory;
import org.apache.webbeans.intercept.InterceptorData;
import org.apache.webbeans.test.component.CheckWithCheckPayment;
import org.apache.webbeans.test.component.PostConstructComponent;
import org.apache.webbeans.test.servlet.TestContext;
import org.junit.Before;
import org.junit.Test;


public class PostConstructComponentTest extends TestContext
{
	Manager container = null;

	public PostConstructComponentTest()
	{
		super(PostConstructComponentTest.class.getSimpleName());
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTypedComponent() throws Throwable
	{
		clear();
		
		defineSimpleWebBean(CheckWithCheckPayment.class);
		defineSimpleWebBean(PostConstructComponent.class);
		List<AbstractComponent<?>> comps = getComponents();
		
		ContextFactory.initRequestContext(null);
		
		Assert.assertEquals(2, comps.size());
		
		Object object = ManagerImpl.getManager().getContext(RequestScoped.class).get(comps.get(0), true);
		Object object2 = ManagerImpl.getManager().getContext(RequestScoped.class).get(comps.get(1), true);
		
		Assert.assertTrue(object instanceof CheckWithCheckPayment);
		Assert.assertTrue(object2 instanceof PostConstructComponent);
		
		PostConstructComponent pcc = (PostConstructComponent)object2;
		
		ComponentImpl<PostConstructComponent> s = (ComponentImpl<PostConstructComponent>) comps.get(1);
		List<InterceptorData> stack = s.getInterceptorStack();
		
		Assert.assertEquals(1, stack.size());
		
		
		Assert.assertNotNull(pcc.getP());
		Assert.assertEquals(object, pcc.getP());
		
		ContextFactory.destroyRequestContext(null);
 	}

}
