/*
 * Copyright (c) 2019 envimate GmbH - https://envimate.com/.
 *
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

package com.envimate.httpmate.usecases.builder;

import com.envimate.httpmate.http.HttpRequestMethod;

@FunctionalInterface
public interface UseCaseStage3<T> {

    /**
     * Configures a single http method on which the use case that was configured in
     * {@link UseCaseStage1#servingTheUseCase(Class)} will be served.
     *
     * @param requestMethod the http method on which the use case will be served
     * @return the next step in the fluent builder
     */
    default T andRequestMethod(final HttpRequestMethod requestMethod) {
        return andRequestMethods(requestMethod);
    }

    /**
     * Configures the http methods on which the use case that was configured in
     * {@link UseCaseStage1#servingTheUseCase(Class)} will be served.
     *
     * @param requestMethods the http methods on which the use case will be served
     * @return the next step in the fluent builder
     */
    T andRequestMethods(HttpRequestMethod... requestMethods);
}
