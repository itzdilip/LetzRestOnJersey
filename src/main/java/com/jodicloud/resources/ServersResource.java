package com.jodicloud.resources;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jodicloud.exception.JodiCloudException;
import com.jodicloud.manager.IServiceManager;
import com.jodicloud.model.Link;
import com.jodicloud.model.Links;
import com.jodicloud.model.Server;
import com.jodicloud.model.ServerActionRequest;
import com.jodicloud.model.ServerRequest;
import com.jodicloud.model.Servers;
import com.jodicloud.response.ServiceResponse;

@Component
@Path("/servers")
public class ServersResource {
	@Context
	UriInfo info;
	@Autowired
	IServiceManager manager;
	Logger logger = Logger.getAnonymousLogger();

	public ServersResource() {

		logger.info("REST is up to serve!!");

	}

	@GET
	@Path("/{serverid}")
	@Produces({ "application/json", "application/xml" })
	public Response getServerDetails(@PathParam("serverid") String serverID)
			throws JodiCloudException {
		Server server = manager.getServerDetails(serverID);

		return Response.status(200)
				.entity(buildResponse(server, "200", "server Details")).build();
	}

	@GET
	@Path("/ping")
	@Produces({ "application/json", "application/xml" })
	public Response ping() {

		logger.info("Up and Running");
		return Response
				.status(200)
				.entity(buildResponse("http://localhost/servers/123", "200",
						"ping sucess")).build();
	}

	@GET
	@Path("/exception")
	@Produces({ "application/json", "application/xml" })
	public Response exception() throws JodiCloudException {

		logger.info("exception");
		throw new JodiCloudException("Exception demo");
	}

	@GET
	@Produces({ "application/json", "application/xml" })
	public Response getAllServers() throws JodiCloudException {

		Servers serversResource = manager.getAllServers();

		return Response.status(201)
				.entity(buildResponse(serversResource, "200", "ok")).build();
	}

	@GET
	@Path("/active")
	@Produces({ "application/json", "application/xml" })
	public Response getAllRunningServers() throws JodiCloudException {
		Servers serversResource = manager.getAllServers();

		return Response.status(201)
				.entity(buildResponse(serversResource, "200", "active List"))
				.build();

	}

	@POST
	@Path("{serverid}/action")
	@Produces({ "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	public Response actionOnServer(@PathParam("serverid") String serverID,
			ServerActionRequest actionRequest) throws JodiCloudException {
		Server serverResponse = manager.actionOnServer(actionRequest);

		return Response.status(201)
				.entity(buildResponse(serverResponse, "201", "Inprogress"))
				.build();

	}

	@POST
	@Produces({ "application/json", "application/xml" })
	@Consumes({ "application/json", "application/xml" })
	@ResponseLinks(annotatedMethodNames = { "getServerDetails",
			"" }, resourceName = ServersResource.class)
	public Response createServer(ServerRequest serverRequest)
			throws JodiCloudException {

		Server serverResponse = manager.createServer(serverRequest);
		UriBuilder builder = hateoasBuilder();

		URI uri = builder.build(serverResponse.getServerID());

		return Response
				.status(201)
				.entity(buildResponse(setLinkToResource(uri.toString()), "201",
						"Inprogress")).build();

	}

	private UriBuilder hateoasBuilder() {
		UriBuilder builder = info.getAbsolutePathBuilder();
		Class<?> resource = null;
		String[] methodName = null;

		Method[] methods = getClass().getMethods();

		for (Method method : methods) {
			ResponseLinks links = method
					.getAnnotation(ResponseLinks.class);
			Annotation[] allAnnotaion =method.getDeclaredAnnotations();
			
			
			
			for(Annotation annotaion : allAnnotaion)
			{
				System.out.println(annotaion.toString());
			}
			if (links != null) {
				try {
					resource = links.resourceName();
					methodName = links.annotatedMethodNames();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		builder.path(resource, methodName[0]);
		return builder;
	}

	/**
	 * @param <T>
	 * @return
	 */
	private <T> UriBuilder hateoasBuilder(T resource, String methodName) {
		// Testing URI Builder Code
		UriBuilder builder = info.getAbsolutePathBuilder();
		builder.path(resource.getClass(), methodName);
		
		
		return builder;
	}

	private <T> ServiceResponse<T> buildResponse(T payLoad, String status,
			String message) {

		ServiceResponse<T> serviceResponse = new ServiceResponse<T>();
		serviceResponse.setPayLoad(payLoad);
		serviceResponse.setErrorcode(status);
		serviceResponse.setHttpMessage(message);

		return serviceResponse;

	}

	/**
	 * @return
	 */
	private Server setLinkToResource(String uriString) {
		Link link = new Link();
		link.setHref(uriString);

		Links links = new Links();

		List<Link> linkList = new ArrayList<Link>();

		linkList.add(link);
		links.setLinks(linkList);
		Server server = new Server();
		server.setLinks(links);
		return server;
	}

}
