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

package com.envimate.httpmate.multipart.handler;

import com.envimate.httpmate.convenience.handler.HttpHandler;
import com.envimate.httpmate.convenience.handler.HttpRequest;
import com.envimate.httpmate.convenience.handler.HttpResponse;

import static com.envimate.httpmate.multipart.handler.MultipartRequest.multipartRequest;

public interface MultipartHandler extends HttpHandler {

    @Override
    default void handle(final HttpRequest request, final HttpResponse response) {
        final MultipartRequest multipartRequest = multipartRequest(request);
        handle(multipartRequest, response);
    }

    void handle(MultipartRequest request, HttpResponse response);
}
