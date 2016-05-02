package com.acervusltd.jobbiest.config;

import org.glassfish.jersey.server.ResourceConfig;

import com.acervusltd.jobbiest.rest.EventsService;
import com.acervusltd.jobbiest.rest.OpportunitiesService;
import com.acervusltd.jobbiest.rest.SeekerService;
import com.acervusltd.jobbiest.rest.StatesService;

public class JobbiestResourceConfig extends ResourceConfig {

    public JobbiestResourceConfig() {
        super();

        register(EventsService.class);
        register(OpportunitiesService.class);
        register(SeekerService.class);
        register(StatesService.class);
    }
}
