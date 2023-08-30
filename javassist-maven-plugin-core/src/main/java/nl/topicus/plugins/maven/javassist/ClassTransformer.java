/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package nl.topicus.plugins.maven.javassist;

import java.util.ArrayList;
import java.util.List;
import javassist.ClassPool;
import javassist.CtClass;

public abstract class ClassTransformer {

    private List<String> processInclusions = new ArrayList<>();

    private List<String> processExclusions = new ArrayList<>();

    private List<String> exclusions = new ArrayList<>();

    private ILogger logger;

    public final ILogger getLogger() {
        return logger;
    }

    public final void setLogger(ILogger logger) {
        this.logger = logger;
    }

    public List<String> getProcessInclusions() {
        return processInclusions;
    }

    public void setProcessInclusions(List<String> processInclusions) {
        this.processInclusions = processInclusions;
    }

    public List<String> getProcessExclusions() {
        return processExclusions;
    }

    public void setProcessExclusions(List<String> processExclusions) {
        this.processExclusions = processExclusions;
    }

    public List<String> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<String> exclusions) {
        this.exclusions = exclusions;
    }

    public abstract void applyTransformations(ClassPool classPool, CtClass classToTransform)
            throws TransformationException;

    public boolean processClassName(String className) {

        for (String exclusion : getProcessExclusions()) {
            if (className.startsWith(exclusion)) {
                return false;
            }
        }

        for (String inclusion : getProcessInclusions()) {
            if (className.startsWith(inclusion)) {
                return true;
            }
        }

        return false;
    }
}
