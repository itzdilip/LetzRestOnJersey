package com.jodicloud.exception;

public class JodiCloudException extends Exception {

	public JodiCloudException() {
	}

	public JodiCloudException(String message) {
		super(message);
	}

	public JodiCloudException(Throwable cause) {
		super(cause);
	}

	public JodiCloudException(String message, Throwable cause) {
		super(message, cause);
	}

}
