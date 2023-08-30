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

public interface ILogger {

    void addMessage(File file, int line, int pos, String message, Throwable e);

    /**
     * Log a message at the DEBUG level.
     *
     * @param message
     *            the message string to be logged
     */
    void debug(String message);

    /**
     * Log a message at the DEBUG level.
     *
     * @param message
     *            the message string to be logged
     * @param throwable
     *            the exception (throwable) to log
     */
    void debug(String message, Throwable throwable);

    /**
     * Log a message at the INFO level.
     *
     * @param message
     *            the message string to be logged
     */
    void info(String message);

    /**
     * Log a message at the INFO level.
     *
     * @param message
     *            the message string to be logged
     * @param throwable
     *            the exception (throwable) to log
     */
    void info(String message, Throwable throwable);

    /**
     * Log a message at the WARN level.
     *
     * @param message
     *            the message string to be logged
     */
    void warn(String message);

    /**
     * Log a message at the WARN level.
     *
     * @param message
     *            the message string to be logged
     * @param throwable
     *            the exception (throwable) to log
     */
    void warn(String message, Throwable throwable);

    /**
     * Log a message at the ERROR level.
     *
     * @param message
     *            the message string to be logged
     */
    void error(String message);

    /**
     * Log a message at the ERROR level.
     *
     * @param message
     *            the message string to be logged
     * @param throwable
     *            the exception (throwable) to log
     */
    void error(String message, Throwable throwable);
}
