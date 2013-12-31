package com.jodicloud.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Server {

	
	private String ServerID;
	
	private String serverName;
	
	private String serverStatus;
	
	private String startTime;

	private Links links;
	
	
	
	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getServerID() {
		return ServerID;
	}

	public void setServerID(String serverID) {
		ServerID = serverID;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
	
	
	
	
	
	
	
}
