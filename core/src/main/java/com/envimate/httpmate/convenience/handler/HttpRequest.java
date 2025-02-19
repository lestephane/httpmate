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
import com.envimate.httpmate.http.Headers;
import com.envimate.httpmate.http.PathParameters;
import com.envimate.httpmate.http.QueryParameters;
import com.envimate.httpmate.path.Path;
import lombok.*;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import static com.envimate.httpmate.HttpMateChainKeys.*;
import static com.envimate.httpmate.util.Validators.validateNotNull;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpRequest {
    private final MetaData metaData;

    public static HttpRequest httpRequest(final MetaData metaData) {
        validateNotNull(metaData, "metaData");
        return new HttpRequest(metaData);
    }

    public Path path() {
        return metaData.get(PATH);
    }

    public PathParameters pathParameters() {
        return metaData.get(PATH_PARAMETERS);
    }

    public QueryParameters queryParameters() {
        return metaData.get(QUERY_PARAMETERS);
    }

    public Headers headers() {
        return metaData.get(HEADERS);
    }

    public Optional<String> bodyString() {
        return metaData.getOptional(BODY_STRING);
    }

    public InputStream bodyStream() {
        return metaData.get(BODY_STREAM);
    }

    public Optional<Object> authenticationInformation() {
        return metaData.getOptional(AUTHENTICATION_INFORMATION);
    }

    public Optional<Map<String, Object>> bodyAsMap() {
        return metaData.getOptional(BODY_MAP);
    }
}
