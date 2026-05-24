package com.smd.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.smd.exception.JodiCloudException;
import com.smd.response.ServiceResponse;

@Provider
@Component
public class JodiCloudExceptionMapper implements ExceptionMapper<JodiCloudException> {

    public JodiCloudExceptionMapper() {
        System.out.println("JodiCloudExceptionMapper invoked!!!");
    }

    @Override
    public Response toResponse(JodiCloudException exception) {
        System.out.println("JodiCloudExceptionMapper " + exception.getMessage());
        
        ServiceResponse<String> serviceResponse = new ServiceResponse<>();
        serviceResponse.setErrorcode("500");
        serviceResponse.setHttpMessage(exception.getMessage());
        
        return Response.status(500).entity(serviceResponse).build();
    }
}
