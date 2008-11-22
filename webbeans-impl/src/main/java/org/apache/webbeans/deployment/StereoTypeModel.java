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
package org.apache.webbeans.deployment;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.webbeans.DeploymentType;
import javax.webbeans.ScopeType;
import javax.webbeans.Stereotype;

import org.apache.webbeans.util.AnnotationUtil;
import org.apache.webbeans.util.Asserts;

public class StereoTypeModel
{
	private String name;
	
	private Annotation defaultDeploymentType;
	
	private Annotation defaultScopeType;
	
	private Set<Class<? extends Annotation>> supportedScopes = null;
	
	private Set<Class<?>>  restrictedTypes = null;
	
	
	public StereoTypeModel(Class<?> clazz)
	{
		this.name = clazz.getName();
		
		if(AnnotationUtil.isMetaAnnotationExist(clazz.getAnnotations(), DeploymentType.class))
		{
			this.defaultDeploymentType = AnnotationUtil.getMetaAnnotations(clazz.getAnnotations(), DeploymentType.class)[0];
		}
		
		if(AnnotationUtil.isMetaAnnotationExist(clazz.getAnnotations(), ScopeType.class))
		{
			this.defaultScopeType = AnnotationUtil.getMetaAnnotations(clazz.getAnnotations(), ScopeType.class)[0];
		}
		
		configureScopes(clazz);
		configureTypes(clazz);
	}
	
	private void configureScopes(Class<?> clazz)
	{
		Asserts.nullCheckForClass(clazz);
		Stereotype type = clazz.getAnnotation(Stereotype.class);
		
		Class<? extends Annotation>[] supportedScopes = type.supportedScopes();
		this.supportedScopes = new HashSet<Class<? extends Annotation>>(Arrays.asList(supportedScopes));
		
		
	}
	
	private void configureTypes(Class<?> clazz)
	{
		Asserts.nullCheckForClass(clazz);
		Stereotype type = clazz.getAnnotation(Stereotype.class);
		this.restrictedTypes = new HashSet<Class<?>>(Arrays.asList(type.requiredTypes()));
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the defaultDeploymentType
	 */
	public Annotation getDefaultDeploymentType()
	{
		return defaultDeploymentType;
	}

	/**
	 * @return the defaultScopeType
	 */
	public Annotation getDefaultScopeType()
	{
		return defaultScopeType;
	}


	/**
	 * @return the supportedScopes
	 */
	public Set<Class<? extends Annotation>> getSupportedScopes()
	{
		return Collections.unmodifiableSet(this.supportedScopes);
	}

	/**
	 * @return the restrictedTypes
	 */
	public Set<Class<?>> getRestrictedTypes()
	{
		return Collections.unmodifiableSet(this.restrictedTypes);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		
		if(!(obj instanceof StereoTypeModel))
			return false;
		
		StereoTypeModel model = (StereoTypeModel)obj;
		
		return model.name.equals(this.name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}
}