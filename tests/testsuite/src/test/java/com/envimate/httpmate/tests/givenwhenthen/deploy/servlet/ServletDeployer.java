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

package com.envimate.httpmate.tests.givenwhenthen.deploy.servlet;

import com.envimate.httpmate.HttpMate;
import com.envimate.httpmate.tests.givenwhenthen.client.ClientFactory;
import com.envimate.httpmate.tests.givenwhenthen.deploy.Deployer;
import com.envimate.httpmate.tests.givenwhenthen.deploy.Deployment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import java.util.List;

import static com.envimate.httpmate.tests.givenwhenthen.client.real.RealHttpMateClientFactory.theRealHttpMateClient;
import static com.envimate.httpmate.tests.givenwhenthen.client.real.RealHttpMateClientWithConnectionReuseFactory.theRealHttpMateClientWithConnectionReuse;
import static com.envimate.httpmate.tests.givenwhenthen.client.shitty.ShittyClientFactory.theShittyTestClient;
import static com.envimate.httpmate.tests.givenwhenthen.deploy.Deployment.httpDeployment;
import static com.envimate.httpmate.tests.givenwhenthen.deploy.FreePortPool.freePort;
import static java.util.Arrays.asList;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletDeployer implements Deployer {

    private Server current;

    public static Deployer servletDeployer() {
        return new ServletDeployer();
    }

    @Override
    public Deployment ensureTheTestHttpMateInstanceIsDeployed() {
        cleanUp();
        final int port = freePort();
        current = new Server(port);
        final ServletHandler handler = new ServletHandler();
        current.setHandler(handler);
        handler.addServletWithMapping(Servlet.class, "/*");
        try {
            current.start();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        return httpDeployment("localhost", port);
    }

    @Override
    public Deployment deploy(final HttpMate httpMate) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void cleanUp() {
        if (current != null) {
            try {
                current.destroy();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "servlet";
    }

    @Override
    public List<ClientFactory> supportedClients() {
        return asList(theShittyTestClient(), theRealHttpMateClient(), theRealHttpMateClientWithConnectionReuse());
    }
}
