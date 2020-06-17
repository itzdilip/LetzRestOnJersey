package com.smd.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.smd.util.ProxyHelper;

public class ProxyHelperTest {

	@Autowired
	public ProxyHelper proxyHelper;
	
	@Test
	public void testGetResourceViaProxy() {
		try {
			String outPut=proxyHelper.getResourceViaProxy("http://proxyserver:portnumber", "http://www.rabbitmq.com/how.html");
			
			System.out.println(outPut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
