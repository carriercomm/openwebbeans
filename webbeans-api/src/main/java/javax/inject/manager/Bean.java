/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package javax.inject.manager;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.context.Contextual;

/**
 * Web Beans component contract.
 */
public abstract class Bean<T> implements Contextual<T>
{
    /** Manager instance */
    private final Manager manager;

    /**
     * Constructs new web bean.
     * 
     * @param manager manager instance
     */
    protected Bean(Manager manager)
    {
        this.manager = manager;
    }

    /**
     * Gets manager.
     * 
     * @return the manager
     */
    protected Manager getManager()
    {
        return manager;
    }

    /**
     * Gets the set of api types of the web beans component.
     * 
     * @return the set of api types
     */
    public abstract Set<Class<?>> getTypes();

    /**
     * Gets the set of binding types of the web beans component.
     * 
     * @return the binding types
     */
    public abstract Set<Annotation> getBindings();

    /**
     * Gets the scope type of the web beans component.
     * 
     * @return scope type
     */
    public abstract Class<? extends Annotation> getScopeType();

    /**
     * Gets the deployment type of the web beans component.
     * 
     * @return deployment type
     */
    public abstract Class<? extends Annotation> getDeploymentType();

    /**
     * Gets the name of the web beans component.
     * 
     * @return name of the component
     */
    public abstract String getName();

     /**
     * True if serializable.
     * 
     * @return true if serializable
     */
    public abstract boolean isSerializable();

    /**
     * True if nullable.
     * 
     * @return true if nullable
     */
    public abstract boolean isNullable();

    public abstract Set<InjectionPoint> getInjectionPoints();

}