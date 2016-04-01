package com.acervusltd.jobbiest.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.stereotype.Component;

@Component
@Path("/opportunity")
public class Opportunity {
	/*
	@GET
	@Produces("text/html")
	public Response getOpportunity() {
		Map<String, Object> modelMap = new HashMap<>();
		return new Viewable("/opportunity"), modelMap);
	}
	*/
}
