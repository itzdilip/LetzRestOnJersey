package com.smd.manager;

import org.springframework.stereotype.Service;

import com.smd.exception.JodiCloudException;
import com.smd.model.Server;
import com.smd.model.ServerActionRequest;
import com.smd.model.ServerRequest;
import com.smd.model.Servers;
@Service
public interface IServiceManager {

	Server createServer(ServerRequest serverRequest) throws JodiCloudException;

	Server actionOnServer(ServerActionRequest actionRequest)throws JodiCloudException;

	Servers getAllServers()throws JodiCloudException;

	Server getServerDetails(String serverID)throws JodiCloudException;

}
