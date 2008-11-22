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
import javax.servlet.http.HttpSession;
import javax.webbeans.SessionScoped;
import javax.webbeans.manager.Manager;

import junit.framework.Assert;

import org.apache.webbeans.component.AbstractComponent;
import org.apache.webbeans.container.ManagerImpl;
import org.apache.webbeans.context.ContextFactory;
import org.apache.webbeans.test.component.BindingComponent;
import org.apache.webbeans.test.component.NonBindingComponent;
import org.apache.webbeans.test.servlet.TestContext;
import org.junit.Before;
import org.junit.Test;


public class InjectedComponentTestWithMember extends TestContext
{
	Manager container = null;

	public InjectedComponentTestWithMember()
	{
		super(InjectedComponentTestWithMember.class.getSimpleName());
	}

	public void endTests(ServletContext ctx)
	{
		
	}

	@Before
	public void init()
	{
		super.init();
		this.container = ManagerImpl.getManager();
	}

	public void startTests(ServletContext ctx)
	{
		
	}
	
	@Test
	public void testTypedComponent() throws Throwable
	{
		clear();
		defineSimpleWebBean(BindingComponent.class);
		defineSimpleWebBean(NonBindingComponent.class);
		List<AbstractComponent<?>> comps = getComponents();
		
		HttpSession session = getSession();
		ContextFactory.initSessionContext(session);
		
		Assert.assertEquals(2, comps.size());
		
		getContext(SessionScoped.class).get(comps.get(0), true);
		Object object = getContext(SessionScoped.class).get(comps.get(1), true);
		
		Assert.assertTrue(object instanceof NonBindingComponent);
		
		NonBindingComponent comp = (NonBindingComponent)object;
		BindingComponent bc = comp.getComponent();
		
		Assert.assertTrue(bc != null);
		
		ContextFactory.destroySessionContext(session);
 	}

}
