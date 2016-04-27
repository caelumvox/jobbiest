package com.acervusltd.jobbiest.web;

import org.glassfish.jersey.server.ResourceConfig;

import com.acervusltd.jobbiest.rest.EventService;
import com.acervusltd.jobbiest.rest.EventsService;
import com.acervusltd.jobbiest.rest.OpportunitiesService;
import com.acervusltd.jobbiest.rest.OpportunityService;
import com.acervusltd.jobbiest.rest.SeekerService;
import com.acervusltd.jobbiest.rest.StatesService;

public class JobbiestResourceConfig extends ResourceConfig {

    public JobbiestResourceConfig() {
        super();

        register(EventService.class);
        register(EventsService.class);
        register(OpportunityService.class);
        register(OpportunitiesService.class);
        register(SeekerService.class);
        register(StatesService.class);
    }
}
