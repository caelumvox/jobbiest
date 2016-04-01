package com.acervusltd.jobbiest.web;

import org.glassfish.jersey.server.ResourceConfig;

import com.acervusltd.jobbiest.rest.OpportunitiesService;
import com.acervusltd.jobbiest.rest.OpportunityService;

public class JobbiestResourceConfig extends ResourceConfig {

    public JobbiestResourceConfig() {
        super();
        
        register(OpportunityService.class);
        register(OpportunitiesService.class);
    }
}
