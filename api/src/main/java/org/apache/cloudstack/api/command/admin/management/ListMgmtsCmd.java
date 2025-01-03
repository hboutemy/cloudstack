// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.apache.cloudstack.api.command.admin.management;

import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiCommandResourceType;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.BaseListCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.response.ListResponse;
import org.apache.cloudstack.api.response.ManagementServerResponse;
import org.apache.commons.lang3.BooleanUtils;

@APICommand(name = "listManagementServers", description = "Lists management servers.", responseObject = ManagementServerResponse.class,
        requestHasSensitiveInfo = false, responseHasSensitiveInfo = false)
public class ListMgmtsCmd extends BaseListCmd {


    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ApiConstants.ID, type = CommandType.UUID, entityType = ManagementServerResponse.class, description = "the id of the management server")
    private Long id;

    @Parameter(name = ApiConstants.NAME, type = CommandType.STRING, description = "the name of the management server")
    private String hostName;

    @Parameter(name = ApiConstants.PEERS, type = CommandType.BOOLEAN,
            description = "Whether to return the management server peers or not. By default, the management server peers will not be returned.",
            since = "4.20.0.0")
    private Boolean peers;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public String getHostName() {
        return hostName;
    }

    public Boolean getPeers() {
        return BooleanUtils.toBooleanDefaultIfNull(peers, false);
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @Override
    public ApiCommandResourceType getApiResourceType() {
        return ApiCommandResourceType.Host;
    }

    @Override
    public void execute() {
        ListResponse<ManagementServerResponse> response = _queryService.listManagementServers(this);
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
    }
}
