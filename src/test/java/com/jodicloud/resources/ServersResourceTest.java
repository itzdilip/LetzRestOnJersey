package com.jodicloud.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class ServersResourceTest extends JerseyTest {
	private static final String PACKAGE_NAME = "com.jodicloud.resources,com.jodicloud.model,com.jodicloud.exception.mapper";

	private WebResource webResource;

	public ServersResourceTest() {
		super(
				new WebAppDescriptor.Builder(PACKAGE_NAME)
						.contextParam("contextConfigLocation",
								"classpath:**/*Context.xml")
						.initParam(
								"com.sun.jersey.api.json.POJOMappingFeature",
								"true").servletClass(SpringServlet.class)
						.contextListenerClass(ContextLoaderListener.class)
						.requestListenerClass(RequestContextListener.class)
						.build());

	}

	@Test
	public void testPing() {
		try {
			webResource = resource().path("servers").path("ping");
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			System.out.println(response.getEntity(String.class));
			assertTrue(response != null);
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testExceptionMapper() {
		try {
			webResource = resource().path("servers").path("exception");
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			assertNotNull(response);
			assertEquals("Exception demo", response.getEntity(String.class));
			assertEquals(500, response.getStatus());
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testPost() throws JSONException {

		webResource = resource().path("servers");
		JSONObject payLoad = new JSONObject();
		payLoad.put("serverName", "Test");
		ClientResponse response = webResource.entity(payLoad)
				.type(MediaType.APPLICATION_JSON).post(ClientResponse.class);
		System.out.println("Post request Test!");
		System.out.println(response);
		System.out.println(response.getEntity(String.class));
		assertTrue(response != null);
		assertEquals(response.getStatus(), 201);

	}

	@Test
	public void testGetAllServers() {
		try {
			webResource = resource().path("servers");
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			System.out.println(response.getEntity(String.class));

		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore(" Not implemented")
	@Test
	public void testGetProcess() {
		try {
			String procesID = "";
			webResource = resource().path("servers").path(procesID);
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);
			System.out.println(response.getEntity(String.class));
			assertTrue(response != null);
			assertEquals(response.getStatus(), 200);
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
