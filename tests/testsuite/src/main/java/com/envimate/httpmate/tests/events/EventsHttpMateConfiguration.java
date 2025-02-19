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

package com.envimate.httpmate.tests.events;

import com.envimate.httpmate.HttpMate;
import com.envimate.messageMate.identification.CorrelationId;
import com.envimate.messageMate.messageBus.MessageBus;
import com.envimate.messageMate.messageBus.MessageBusType;

import java.util.HashMap;

import static com.envimate.httpmate.HttpMate.anHttpMateConfiguredAs;
import static com.envimate.httpmate.HttpMateChainKeys.BODY_STRING;
import static com.envimate.httpmate.events.EventDrivenBuilder.EVENT_DRIVEN;
import static com.envimate.httpmate.http.HttpRequestMethod.GET;
import static com.envimate.messageMate.internal.pipe.configuration.AsynchronousConfiguration.constantPoolSizeAsynchronousPipeConfiguration;
import static com.envimate.messageMate.messageBus.MessageBusBuilder.aMessageBus;
import static com.envimate.messageMate.processingContext.EventType.eventTypeFromString;

public final class EventsHttpMateConfiguration {
    public static MessageBus messageBus;

    private EventsHttpMateConfiguration() {
    }

    public static HttpMate theEventsHttpMateInstanceUsedForTesting() {
        messageBus = aMessageBus()
                .forType(MessageBusType.ASYNCHRONOUS)
                .withAsynchronousConfiguration(constantPoolSizeAsynchronousPipeConfiguration(4))
                .build();

        final HttpMate httpMate = anHttpMateConfiguredAs(EVENT_DRIVEN).attachedTo(messageBus)
                .triggeringTheEvent("trigger").forRequestPath("/trigger").andRequestMethod(GET)
                .mappingResponsesUsing((event, metaData) -> metaData.set(BODY_STRING, event.toString()))
                .build();

        messageBus.subscribeRaw(eventTypeFromString("trigger"), processingContext -> {
            final CorrelationId correlationId = processingContext.generateCorrelationIdForAnswer();
            messageBus.send(eventTypeFromString("answer"), new HashMap<>(), correlationId);
        });

        return httpMate;
    }
}
