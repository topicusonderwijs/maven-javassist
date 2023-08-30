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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import org.sonatype.plexus.build.incremental.BuildContext;

public class ClassNameDirectoryIterator implements ClassFileIterator {
    private final Path classPath;
    private Iterator<Path> classFiles = new ArrayList<Path>().iterator();
    private Path lastFile;

    public ClassNameDirectoryIterator(final Path classPath, final BuildContext buildContext) throws IOException {
        this.classPath = classPath;
        this.classFiles = Files.walk(classPath)
                .filter(p -> p.getFileName().toString().endsWith(".class") && buildContext.hasDelta(p.toFile()))
                .iterator();
    }

    @Override
    public boolean hasNext() {
        return classFiles.hasNext();
    }

    @Override
    public String next() {
        final Path classFile = classFiles.next();
        lastFile = classFile;
        final String qualifiedFileName = classPath.relativize(classFile).toString();
        return qualifiedFileName.replace(File.separator, ".").substring(0, qualifiedFileName.length() - 5);
    }

    @Override
    public Path getLastFile() {
        return lastFile;
    }

    @Override
    public void remove() {
        classFiles.remove();
    }
}
