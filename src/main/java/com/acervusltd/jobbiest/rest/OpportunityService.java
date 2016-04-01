package com.acervusltd.jobbiest.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acervusltd.jobbiest.db.OpportunityTableGateway;
import com.acervusltd.jobbiest.model.Opportunity;

@Component
@Path("/opportunity/{opportunityId}")
public class OpportunityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpportunityService.class);
    
    @Autowired
    OpportunityTableGateway opportunityTableGateway;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getOpportunity(@PathParam("opportunityId") Integer opportunityId) {
        LOGGER.trace("Opportunity with id %d requested.", opportunityId);
        
        if (opportunityId == null) {
        	LOGGER.warn("No opportunityId supplied.  Not returning result.");
        }
        
        Opportunity opportunity = opportunityTableGateway.getOpportunity(opportunityId);
        
        Map<String, Object> opportunityMap = new TreeMap<>();
        opportunityMap.put("opp_id", opportunity.getOpportunityId());
        opportunityMap.put("name", opportunity.getName());
        opportunityMap.put("status", opportunity.getStatus());
        opportunityMap.put("industry", opportunity.getIndustry());
        opportunityMap.put("city", opportunity.getCity());
        opportunityMap.put("state", opportunity.getState());
        
        return opportunityMap;    
    }
}
