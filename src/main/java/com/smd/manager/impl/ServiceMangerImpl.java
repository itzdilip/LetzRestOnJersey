package com.smd.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.smd.exception.JodiCloudException;
import com.smd.manager.IServiceManager;
import com.smd.model.Server;
import com.smd.model.ServerActionRequest;
import com.smd.model.ServerRequest;
import com.smd.model.Servers;
@Component
public class ServiceMangerImpl implements IServiceManager {

	private HashMap<String, Server> serverHolder = new HashMap<String, Server>();

	public Server createServer(ServerRequest serverRequest)
			throws JodiCloudException {

		String serverId = UUID.randomUUID().toString();
		Server server = new Server();
		server.setServerID(serverId);
		server.setServerName(serverRequest.getServerName());
		server.setServerStatus("Created");
		server.setStartTime(new Date().toString());
		serverHolder.put(serverId, server);
		return server;
	}

	public Server actionOnServer(ServerActionRequest actionRequest) {

		Server server = new Server();
		server.setServerID(actionRequest.getServerID());
		server.setServerStatus(actionRequest.getAction());
		return server;
	}

	public Servers getAllServers() {
		Servers servers = new Servers();
		ArrayList<Server> serverList = new ArrayList<Server>();
		for (Map.Entry<String, Server> entry : serverHolder.entrySet()) {
			Server server = entry.getValue();

			serverList.add(server);

		}
		servers.setServers(serverList);
		return servers;
	}

	public Server getServerDetails(String serverID) {
		Server server= new Server();
		server =serverHolder.get(serverID);
		return server;
	}

}
