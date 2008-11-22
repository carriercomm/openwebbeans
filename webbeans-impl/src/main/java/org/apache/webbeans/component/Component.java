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
package org.apache.webbeans.component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import javax.webbeans.manager.Bean;
import javax.webbeans.manager.Manager;

import org.apache.webbeans.intercept.InterceptorData;

/**
 * Extends the unpublished {@link Bean} interface for
 * backward capability with EDR-1 of the specification.
 * 
 * <p>
 *   <b>This class is not used by the client. It is used 
 *   entirely as internal. It exists only for compatibility problems.</b>
 * </p>
 * 
 * @author <a href="mailto:gurkanerdogdu@yahoo.com">Gurkan Erdogdu</a>
 * @since 1.0
 *
 */
public abstract class Component<T> extends Bean<T>
{
	protected Component(Manager manager)
	{
		super(manager);
	}
	
	/**
	 * Creates the new web beans component instance.
	 * 
	 * @return the new web beans component instance
	 */
	abstract public T create();

	/**
	 * Destorys the web beans component instance.
	 * 
	 * @param instance web beans component instance
	 */
	abstract public void destroy(T instance);
	
	abstract public Annotation getType();
	
	abstract public void setType(Annotation type);
	
	abstract public Annotation getImplScopeType();
	
	abstract public void setImplScopeType(Annotation scopeType);
	
	abstract public WebBeansType getWebBeansType();
	
	abstract public void addBindingType(Annotation bindingType);
	
	abstract public Set<Annotation> getImplBindingTypes();
		
	abstract public void setName(String name);
	
	abstract public int getPrecedence();
	
	abstract public Class<T> getReturnType();
	
	abstract public Object getDependent(Component<?> dependentComponent);
	
	abstract public List<InterceptorData> getInterceptorStack();
	
	abstract public List<Object> getDecoratorStack();
	
	abstract public void setSerializable(boolean serializable);
	
	abstract public void setNullable(boolean nullable);
}
