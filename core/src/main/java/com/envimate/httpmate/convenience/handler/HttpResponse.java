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

package com.envimate.httpmate.convenience.handler;

import com.envimate.httpmate.chains.MetaData;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.InputStream;

import static com.envimate.httpmate.HttpMateChainKeys.*;
import static com.envimate.httpmate.util.Validators.validateNotNull;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpResponse {
    private final MetaData metaData;

    public static HttpResponse httpResponse(final MetaData metaData) {
        validateNotNull(metaData, "metaData");
        return new HttpResponse(metaData);
    }

    public void addHeader(final String key, final String value) {
        metaData.get(RESPONSE_HEADERS).put(key, value);
    }

    public void setStatus(final int status) {
        metaData.set(RESPONSE_STATUS, status);
    }

    public void setBody(final String body) {
        metaData.set(RESPONSE_STRING, body);
    }

    public void setBody(final InputStream inputStream) {
        metaData.set(RESPONSE_STREAM, inputStream);
    }
}
