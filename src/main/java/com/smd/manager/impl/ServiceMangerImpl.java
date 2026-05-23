package com.smd.manager.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smd.exception.JodiCloudException;
import com.smd.manager.IServiceManager;
import com.smd.model.Server;
import com.smd.model.ServerActionRequest;
import com.smd.model.ServerRequest;
import com.smd.model.Servers;
import com.smd.repository.ServerRepository;

@Component
public class ServiceMangerImpl implements IServiceManager {

	@Autowired
	private ServerRepository serverRepository;

	public Server createServer(ServerRequest serverRequest)
			throws JodiCloudException {

		String serverId = UUID.randomUUID().toString();
		Server server = new Server();
		server.setServerID(serverId);
		server.setServerName(serverRequest.getServerName());
		server.setServerStatus("Created");
		server.setStartTime(new Date().toString());
		
		return serverRepository.save(server);
	}

	public Server actionOnServer(ServerActionRequest actionRequest) {
		Server server = serverRepository.findById(actionRequest.getServerID()).orElse(new Server());
		server.setServerID(actionRequest.getServerID());
		server.setServerStatus(actionRequest.getAction());
		return serverRepository.save(server);
	}

	public Servers getAllServers() {
		Servers servers = new Servers();
		servers.setServers(new java.util.ArrayList<>(serverRepository.findAll()));
		return servers;
	}

	public Server getServerDetails(String serverID) {
		return serverRepository.findById(serverID).orElse(null);
	}

}
