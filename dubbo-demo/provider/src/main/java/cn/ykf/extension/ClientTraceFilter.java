/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package cn.ykf.extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

/**
 * 只有当 rest 消费端也是 Dubbo 应用时才有效
 */
public class ClientTraceFilter implements ClientRequestFilter, ClientResponseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientTraceFilter.class);

    public void filter(ClientRequestContext requestContext) throws IOException {
        LOGGER.info("Client request filter invoked");
    }

    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws
            IOException {
        LOGGER.info("Client response filter invoked");
    }
}
