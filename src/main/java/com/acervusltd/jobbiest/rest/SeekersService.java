package com.acervusltd.jobbiest.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acervusltd.jobbiest.db.SeekersTableGateway;
import com.acervusltd.jobbiest.model.Seeker;

@Component
@Path("/seekers")
public class SeekersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeekersService.class);

    @Autowired
    SeekersTableGateway seekerTableGateway;
    
    @GET
    @Path("/{seekerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Seeker getSeeker(@PathParam("seekerId") Integer seekerId) {
        LOGGER.trace("Seeker with id %d requested.", seekerId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }

        Seeker seeker = seekerTableGateway.getSeekerById(seekerId);

        return seeker;
    }

    @POST
    @Path("/{seekerId}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Seeker postSeeker(@PathParam("seekerId") Integer seekerId, MultivaluedMap<String, String> formParams) {
        LOGGER.trace("Seeker with id %d requested.", seekerId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }

        Seeker seeker = seekerTableGateway.getSeekerById(seekerId);
        
        for (String key: formParams.keySet()) {
            String value = formParams.getFirst(key);
            
            if (value != null) {
                switch(key) {
                    case "firstname":
                        seeker.setFirstname(value);
                        break;
                    case "lastname":
                        seeker.setLastname(value);
                        break;
                    case "address":
                        seeker.setAddress(value);
                        break;
                    case "city":
                        seeker.setCity(value);
                        break;
                    case "state":
                        seeker.setState(value);
                        break;
                    case "email":
                        seeker.setEmail(value);
                        break;
                    case "username":
                        seeker.setUsername(value);
                        break;
                    default:
                        LOGGER.warn("Unidentified parameter %s, ignoring.", key);
                }
            }
        }
        seekerTableGateway.updateSeeker(seeker);

        return seeker;
    }
}
