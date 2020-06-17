package com.smd.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;

public class ProxyHelper {

	@Autowired
	private DefaultApacheHttpClientConfig clientConfig;

	String getResourceViaProxy(String proxyURL, String resourceURl)
			throws Exception {

		System.out.println(" Is client config null ?? "
				+ (clientConfig == null));
		// DefaultApacheHttpClientConfig clientConfig = new
		// DefaultApacheHttpClientConfig();
		clientConfig.getProperties().put(
				DefaultApacheHttpClientConfig.PROPERTY_PROXY_URI, proxyURL);

		Client client = ApacheHttpClient.create(clientConfig);
		WebResource webResource = client.resource(resourceURl);

		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);

		return response.getEntity(String.class);

	}

}