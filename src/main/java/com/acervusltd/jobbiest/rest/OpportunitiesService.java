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
@Path("/opportunities/{seekerId}")
public class OpportunitiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpportunitiesService.class);

    @Autowired
    OpportunityTableGateway opportunityTableGateway;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getOpportunities(@PathParam("seekerId") Integer seekerId) {
        LOGGER.trace("Opportunities list requested.");

        List<Map<String, Object>> returnList = new LinkedList<Map<String, Object>>();

        if (seekerId == null) {
            LOGGER.warn("No seeker_id supplied.  Not returning result.");
        }

        List<Opportunity> opportunityList = opportunityTableGateway.getOpportunityList(seekerId);

        for (Opportunity opportunity : opportunityList) {
            Map<String, Object> opportunityMap = new TreeMap<>();
            opportunityMap.put("opp_id", opportunity.getOpportunityId());
            opportunityMap.put("name", opportunity.getName());
            opportunityMap.put("status", opportunity.getStatus());
            opportunityMap.put("industry", opportunity.getIndustry());
            opportunityMap.put("city", opportunity.getCity());
            opportunityMap.put("state", opportunity.getState());
            returnList.add(opportunityMap);
        }

        return returnList;
    }
}
