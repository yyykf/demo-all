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

import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 自定义的 Dubbo 异常处理器
 */
public class CustomExceptionMapper implements ExceptionMapper<NotFoundException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionMapper.class);

    public Response toResponse(NotFoundException e) {
        LOGGER.error("Exception mapper successfully got an exception, Client IP is {}",
                RpcContext.getContext().getRemoteAddressString(), e);

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Oops! the requested resource is not found!")
                .type("text/plain").build();
    }
}
