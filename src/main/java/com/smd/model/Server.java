package com.smd.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "servers")
@XmlRootElement
public class Server {

	@Id
	private String serverID;
	
	private String serverName;
	
	private String serverStatus;
	
	private String startTime;

	@Transient
	private Links links;

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
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
