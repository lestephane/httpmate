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

package com.envimate.httpmate.unpacking;

import com.envimate.httpmate.chains.Configurator;
import com.envimate.httpmate.http.ContentType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.envimate.httpmate.chains.Configurator.toUseModules;
import static com.envimate.httpmate.unpacking.BodyMapParsingModule.bodyMapParsingModule;
import static com.envimate.httpmate.util.Validators.validateNotNull;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BodyMapParsingModuleBuilder {
    private final Map<ContentType, Function<String, Map<String, Object>>> bodyParsers;

    static BodyMapParsingModuleBuilder toParseBodiesBy() {
        return new BodyMapParsingModuleBuilder(new HashMap<>());
    }

    public With parsingContentType(final ContentType contentType) {
        validateNotNull(contentType, "contentType");
        return parser -> {
            validateNotNull(parser, "parser");
            bodyParsers.put(contentType, parser);
            return this;
        };
    }

    public Configurator usingTheDefaultContentType(final ContentType contentType) {
        validateNotNull(contentType, "contentType");
        return toUseModules(bodyMapParsingModule(contentType, bodyParsers));
    }
}
