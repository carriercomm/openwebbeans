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
package org.apache.webbeans.test.component;

import javax.webbeans.Initializer;
import javax.webbeans.Named;
import javax.webbeans.Production;
import javax.webbeans.RequestScoped;

import org.apache.webbeans.test.annotation.binding.Binding1;
import org.apache.webbeans.test.annotation.binding.Binding2;
import org.apache.webbeans.test.component.service.ITyped2;


@Production
@RequestScoped
@Named("current")
@SuppressWarnings("unchecked")
public class CurrentBindingComponent
{
	private ITyped2 typed2 = null;
	
	public CurrentBindingComponent()
	{
		
	}
	
	
	@Initializer
	public CurrentBindingComponent(@Binding1 @Binding2 ITyped2 typed2)
	{
		this.typed2 = typed2;
	}
	
	public ITyped2 getTyped2()
	{
		return this.typed2;
	}
	

}
