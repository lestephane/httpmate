/*
 * Copyright (c) 2018 envimate GmbH - https://envimate.com/.
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

package com.envimate.httpmate.builder.configurators;

import com.envimate.httpmate.HttpMate;
import com.envimate.httpmate.mapper.ResponseMapper;
import com.envimate.httpmate.mapper.ResponseTemplate;

public interface ResponseTemplateConfigurator {

    /**
     * Configures a handler that will be called on <b>all</b> responses before their actually
     * processing by the respective {@link ResponseMapper}. This can be used to specify global defaults
     * such as the Content-Type header of responses.
     *
     * @param responseTemplate a handler function that sets defaults in the response
     * @return the next step in the fluent builder
     */
    void usingTheResponseTemplate(ResponseTemplate responseTemplate);

    /**
     * Configure {@link HttpMate} to start every response from scratch without using any defaults.
     *
     * @return the next step in the fluent builder
     */
    default void usingAnEmptyResponseTemplate() {
        usingTheResponseTemplate(ResponseTemplate.EMPTY_RESPONSE_TEMPLATE);
    }
}
