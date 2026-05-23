package com.smd;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.smd.resources.ServersResource;
import com.smd.exception.mapper.ExceptionMapper;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ServersResource.class);
        // Register the ExceptionMapper
        register(com.smd.exception.mapper.JodiCloudExceptionMapper.class);
    }
}
