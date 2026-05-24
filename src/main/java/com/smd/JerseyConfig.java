package com.smd;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.smd.resources.ServersResource;
import com.smd.exception.mapper.JodiCloudExceptionMapper;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ServersResource.class);
        // Register the ExceptionMapper
        register(JodiCloudExceptionMapper.class);
        // Register Swagger/OpenAPI resources
        register(io.swagger.v3.jaxrs2.integration.resources.OpenApiResource.class);
    }
}
