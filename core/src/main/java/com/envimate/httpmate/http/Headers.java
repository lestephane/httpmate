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

package com.envimate.httpmate.http;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Map;
import java.util.Optional;

import static com.envimate.httpmate.http.HeaderKey.headerKey;
import static com.envimate.httpmate.util.Maps.*;
import static com.envimate.httpmate.util.Validators.validateNotNull;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Headers {
    private final Map<HeaderKey, HeaderValue> headers;

    public static Headers headers(final Map<String, String> stringMap) {
        validateNotNull(stringMap, "stringMap");
        final Map<HeaderKey, HeaderValue> headers = stringsToValueObjects(
                stringMap,
                HeaderKey::headerKey,
                HeaderValue::headerValue);
        return new Headers(headers);
    }

    public Optional<String> getHeader(final String key) {
        final HeaderKey headerKey = headerKey(key);
        return getOptionally(headers, headerKey).map(HeaderValue::stringValue);
    }

    public Map<String, String> asStringMap() {
        return valueObjectsToStrings(headers, HeaderKey::stringValue, HeaderValue::stringValue);
    }
}
