package com.jodicloud.response;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable {
	private T payLoad;
	private String errorcode;
	private String httpMessage;
	private Exception exception;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getHttpMessage() {
		return httpMessage;
	}

	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public T getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(T payLoad) {
		this.payLoad = payLoad;
	}

}
