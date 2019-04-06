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

package com.envimate.httpmate.processors;

import com.envimate.httpmate.chains.HttpMateChainKeys;
import com.envimate.httpmate.chains.MetaData;
import com.envimate.httpmate.chains.rules.Processor;
import com.envimate.httpmate.mapper.ResponseTemplate;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateHttpResponseBuilderProcessor implements Processor {
    private final ResponseTemplate responseTemplate;

    public static Processor createHttpResponseBuilderProcessor(final ResponseTemplate responseTemplate) {
        return new CreateHttpResponseBuilderProcessor(responseTemplate);
    }

    @Override
    public void apply(final MetaData metaData) {
        metaData.set(HttpMateChainKeys.RESPONSE_HEADERS, new HashMap<>());
        responseTemplate.apply(metaData);
    }
}
