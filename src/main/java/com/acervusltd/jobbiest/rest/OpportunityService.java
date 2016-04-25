package com.acervusltd.jobbiest.rest;

import java.util.Map;
import java.util.TreeMap;

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

import com.acervusltd.jobbiest.db.OpportunityTableGateway;
import com.acervusltd.jobbiest.model.Opportunity;

@Component
@Path("/opportunity")
public class OpportunityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpportunityService.class);

    private static final String NAME_KEY = "name";
    private static final String INDUSTRY_KEY = "industry";
    private static final String ADDRESS_KEY = "address";
    private static final String CITY_KEY = "city";
    private static final String STATE_KEY = "state";
    private static final String ZIP_KEY = "zip";
    private static final String URL_KEY = "url";
    private static final String STATUS_KEY = "status";
    private static final String SEEKER_ID_KEY = "seeker_id";

    @Autowired
    OpportunityTableGateway opportunityTableGateway;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Opportunity postOpportunity(MultivaluedMap<String, String> formParams) {
        LOGGER.trace("Creating new opportunity.");

        Opportunity opportunity = new Opportunity();

        for (String key : formParams.keySet()) {
            String value = formParams.getFirst(key);

            if (value != null) {
                switch (key) {
                    case NAME_KEY:
                        opportunity.setName(value);
                        break;
                    case INDUSTRY_KEY:
                        opportunity.setIndustry(value);
                        break;
                    case ADDRESS_KEY:
                        opportunity.setAddress(value);
                        break;
                    case CITY_KEY:
                        opportunity.setCity(value);
                        break;
                    case STATE_KEY:
                        opportunity.setState(value);
                        break;
                    case ZIP_KEY:
                        opportunity.setZip(value);
                        break;
                    case URL_KEY:
                        opportunity.setUrl(value);
                        break;
                    case STATUS_KEY:
                        opportunity.setStatus(value.toUpperCase());
                        break;
                    case SEEKER_ID_KEY:
                        opportunity.setSeekerId(Integer.valueOf(value));
                        break;
                    default:
                        LOGGER.warn("Unidentified parameter %s, ignoring.", key);
                }
            }
        }
        int opportunityId = opportunityTableGateway.createOpportunity(opportunity);
        opportunity.setOpportunityId(opportunityId);

        return opportunity;
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{opportunityId}")
    public void postOpportunityParameter(@PathParam("opportunityId") Integer opportunityId,
            MultivaluedMap<String, String> formParams) {
        LOGGER.trace("Creating new opportunity.");

        for (String key : formParams.keySet()) {
            String value = formParams.getFirst(key);

            if (value != null) {
                switch (key) {
                    case NAME_KEY:
                        opportunityTableGateway.updateOpportunityName(opportunityId, value);
                        break;
                    case INDUSTRY_KEY:
                        opportunityTableGateway.updateOpportunityIndustry(opportunityId, value);
                        break;
                    case ADDRESS_KEY:
                        opportunityTableGateway.updateOpportunityAddress(opportunityId, value);
                        break;
                    case CITY_KEY:
                        opportunityTableGateway.updateOpportunityCity(opportunityId, value);
                        break;
                    case STATE_KEY:
                        opportunityTableGateway.updateOpportunityState(opportunityId, value);
                        break;
                    case ZIP_KEY:
                        opportunityTableGateway.updateOpportunityZip(opportunityId, value);
                        break;
                    case URL_KEY:
                        opportunityTableGateway.updateOpportunityUrl(opportunityId, value);
                        break;
                    case STATUS_KEY:
                        opportunityTableGateway.updateOpportunityStatus(opportunityId, value);
                        break;
                    default:
                        LOGGER.warn("Unidentified parameter %s, ignoring.", key);
                }
            }
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{opportunityId}")
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
        opportunityMap.put("address", opportunity.getAddress());
        opportunityMap.put("city", opportunity.getCity());
        opportunityMap.put("state", opportunity.getState());
        opportunityMap.put("zip", opportunity.getZip());

        return opportunityMap;
    }
}
