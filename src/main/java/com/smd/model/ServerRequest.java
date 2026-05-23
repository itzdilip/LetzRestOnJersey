package com.smd.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ServerRequest {
	
	@NotBlank(message = "Server name cannot be blank")
	@Size(min = 3, max = 50, message = "Server name must be between 3 and 50 characters")
	private String serverName;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
