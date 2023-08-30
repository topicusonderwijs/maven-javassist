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
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.sonatype.plexus.build.incremental.BuildContext;

public class ClassNameJarIterator implements ClassFileIterator {
    private Iterator<String> classFiles = new ArrayList<String>().iterator();
    private Path jarFile;

    public ClassNameJarIterator(final Path classPath, final BuildContext buildContext) throws IOException {

        jarFile = classPath;
        if (buildContext.hasDelta(classPath.toFile())) {
            List<String> classNames = new ArrayList<>();
            try (InputStream fis = java.nio.file.Files.newInputStream(jarFile);
                    JarInputStream jarFileStream = new JarInputStream(fis)) {
                JarEntry jarEntry;

                while (true) {
                    jarEntry = jarFileStream.getNextJarEntry();
                    if (jarEntry == null) {
                        break;
                    }

                    if (jarEntry.getName().endsWith(".class")) {
                        classNames.add(jarEntry.getName().replaceAll("/", "\\."));
                    }
                }
            }
            classFiles = classNames.iterator();
        } else {
            classFiles = Collections.emptyIterator();
        }
    }

    @Override
    public boolean hasNext() {
        return classFiles.hasNext();
    }

    @Override
    public String next() {
        String ret = classFiles.next().replace(File.separator, ".");
        return ret.substring(0, ret.length() - 5);
    }

    @Override
    public Path getLastFile() {
        return jarFile;
    }

    @Override
    public void remove() {
        classFiles.remove();
    }
}
