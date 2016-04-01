package com.acervusltd.jobbiest.db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.Opportunity;

public class OpportunityTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpportunityTableGateway.class);
    
    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;
    
    private static final String opportunityListQuery = "select * from opportunity where seeker_id = :seeker_id";
    
    private static final String opportunityQuery = "select * from opportunity where opp_id = :opp_id";
    
    public List<Opportunity> getOpportunityList(int seekerId) {
    	LOGGER.trace("Fetching opportunity list for seeker %d", seekerId);
    	List<Opportunity> opportunityList = new LinkedList<>();
    	
    	Map<String, Object> parameterMap = new HashMap<>();
    	parameterMap.put("seeker_id", seekerId);
    	List<Map<String,Object>> resultList =
    			jobbiestNamedParamJDBCTemplate.queryForList(opportunityListQuery, parameterMap);
    	
    	for (Map<String,Object> result: resultList) {
    		Opportunity opportunity = new Opportunity();
    		opportunity.setOpportunityId((int) result.get("opp_id"));
    		opportunity.setName((String) result.get("name"));
    		opportunity.setStatus((String) result.get("status"));
    		opportunity.setIndustry((String) result.get("industry"));
    		opportunity.setCity((String) result.get("city"));
    		opportunity.setState((String) result.get("state"));
    		
    		opportunityList.add(opportunity);
    	}
    	
    	return opportunityList;
    }
    
    public Opportunity getOpportunity(int opportunityId) {
    	LOGGER.trace("Fetching opportunity with id %d", opportunityId);
    	
    	Map<String, Object> parameterMap = new HashMap<>();
    	parameterMap.put("opp_id", opportunityId);
    	Map<String,Object> resultMap =
    			jobbiestNamedParamJDBCTemplate.queryForMap(opportunityQuery, parameterMap);
    	
    	// TODO: Refactor to use a row mapper
		Opportunity opportunity = new Opportunity();
		opportunity.setOpportunityId((int) resultMap.get("opp_id"));
		opportunity.setName((String) resultMap.get("name"));
		opportunity.setStatus((String) resultMap.get("status"));
		opportunity.setIndustry((String) resultMap.get("industry"));
		opportunity.setCity((String) resultMap.get("city"));
		opportunity.setState((String) resultMap.get("state"));
    	
    	return opportunity;
    }
}
