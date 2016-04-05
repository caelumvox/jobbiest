package com.acervusltd.jobbiest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acervusltd.jobbiest.db.SeekerTableGateway;
import com.acervusltd.jobbiest.model.Seeker;

@Component
@Path("/seeker")
public class SeekerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeekerService.class);

    @Autowired
    SeekerTableGateway seekerTableGateway;
    
    @GET
    @Path("/{seekerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seeker getSeeker(@PathParam("seekerId") Integer seekerId) {
        LOGGER.trace("Seeker with id %d requested.", seekerId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }

        Seeker seeker = seekerTableGateway.getSeeker(seekerId);

        return seeker;
    }

    @POST
    @Path("/{seekerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seeker postSeeker(@PathParam("seekerId") Integer seekerId) {
        LOGGER.trace("Seeker with id %d requested.", seekerId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }

        Seeker seeker = seekerTableGateway.getSeeker(seekerId);

        return seeker;
    }
}
