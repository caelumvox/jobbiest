package com.acervusltd.jobbiest.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acervusltd.jobbiest.db.StatesTableGateway;
import com.acervusltd.jobbiest.model.State;

@Component
@Path("/states")
public class StatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatesService.class);

    @Autowired
    StatesTableGateway stateTableGateway;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<State> getStates() {
        LOGGER.trace("States list requested.");

        return stateTableGateway.getStatesList();
    }
}
