package com.smd.util;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Component
public class ProxyHelper {

	public String getResourceViaProxy(String proxyURL, String resourceURL) throws Exception {
		// Modern Spring way to handle proxies with RestTemplate
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		
		URI proxyUri = new URI(proxyURL);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUri.getHost(), proxyUri.getPort()));
		requestFactory.setProxy(proxy);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate.getForObject(resourceURL, String.class);
	}

}