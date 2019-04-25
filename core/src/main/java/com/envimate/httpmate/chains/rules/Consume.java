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

package com.envimate.httpmate.chains.rules;

import com.envimate.httpmate.chains.ChainName;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Optional;

import static java.util.Optional.empty;

@ToString
@EqualsAndHashCode
public final class Consume implements Action {
    private Consume() {
    }

    public static Action consume() {
        return new Consume();
    }

    @Override
    public Optional<ChainName> target() {
        return empty();
    }
}
