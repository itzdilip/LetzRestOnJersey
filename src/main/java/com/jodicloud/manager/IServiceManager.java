package com.jodicloud.manager;

import org.springframework.stereotype.Service;

import com.jodicloud.exception.JodiCloudException;
import com.jodicloud.model.Server;
import com.jodicloud.model.ServerActionRequest;
import com.jodicloud.model.ServerRequest;
import com.jodicloud.model.Servers;
@Service
public interface IServiceManager {

	Server createServer(ServerRequest serverRequest) throws JodiCloudException;

	Server actionOnServer(ServerActionRequest actionRequest)throws JodiCloudException;

	Servers getAllServers()throws JodiCloudException;

	Server getServerDetails(String serverID)throws JodiCloudException;

}
