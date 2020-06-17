package com.smd.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.smd.exception.JodiCloudException;

@Provider
public class JodiCloudExceptionMapper implements
		ExceptionMapper<JodiCloudException> {

	public JodiCloudExceptionMapper() {
		System.out.println("JodiCloudExceptionMapper invoked!!!");
	}

	public Response toResponse(JodiCloudException exception) {
		System.out
				.println("JodiCloudExceptionMapper " + exception.getMessage());
		return Response.status(500).entity(exception.getMessage()).build();
	}

}
