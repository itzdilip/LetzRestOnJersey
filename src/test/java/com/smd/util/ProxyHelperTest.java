package com.smd.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProxyHelperTest {

	@Autowired
	public ProxyHelper proxyHelper;
	
	@Test
	@Disabled("Requires a real proxy server to run")
	public void testGetResourceViaProxy() {
		try {
			String outPut = proxyHelper.getResourceViaProxy("http://proxyserver:8080", "http://example.com");
			System.out.println(outPut);
			assertNotNull(outPut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
