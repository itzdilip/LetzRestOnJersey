package com.jodicloud.common.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.client.apache.ApacheHttpClient;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;



public class ProxyHelper {

	static String getResourceViaProxy(String proxyURL, String resourceURl) throws Exception{
		/*
		 * System.setProperty("http.proxyHost", "172.28.84.6");
		 * System.setProperty("http.proxyPort", "3128");
		 */

		DefaultApacheHttpClientConfig cc = new DefaultApacheHttpClientConfig();
		cc.getProperties().put(
				DefaultApacheHttpClientConfig.PROPERTY_PROXY_URI, proxyURL);

		Client client = ApacheHttpClient.create(cc);
		WebResource webResource = client
				.resource(resourceURl);

		ClientResponse response = webResource.accept("application/json").get(
				ClientResponse.class);

		

		return response.getEntity(String.class);

	}

	
}