package com.acervusltd.jobbiest.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.Opportunity;

public class OpportunitiesTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpportunitiesTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String OPPORTUNITY_SELECT_LIST_QUERY = 
            "select * from opportunities where seeker_id = :seeker_id";
    private static final String OPPORTUNITY_SELECT_QUERY = 
            "select * from opportunities where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_INSERT_QUERY = 
            "insert into opportunities (name, industry, address, city, state, zip, url, status, seeker_id) values (:name, :industry, :address, :city, :state, :zip, :url, :status, :seekerId) returning opportunity_id";

    private static final String OPPORTUNITY_UPDATE_NAME_QUERY =
            "update opportunities set name = :name where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_INDUSTRY_QUERY =
            "update opportunities set industry = :industry where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_ADDRESS_QUERY =
            "update opportunities set address = :address where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_CITY_QUERY = 
            "update opportunities set city = :city where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_STATE_QUERY = 
            "update opportunities set state = :state where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_ZIP_QUERY = 
            "update opportunities set zip = :zip where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_URL_QUERY =
            "update opportunities set url = :url where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_UPDATE_STATUS_QUERY =
            "update opportunities set status = :status where opportunity_id = :opportunity_id";

    public List<Opportunity> getOpportunityList(int seekerId) {
        LOGGER.trace("Fetching opportunity list for seeker %d", seekerId);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("seeker_id", seekerId);
        List<Opportunity> opportunityList = null;
        try {
            opportunityList = jobbiestNamedParamJDBCTemplate.query(OPPORTUNITY_SELECT_LIST_QUERY,
                parameterMap, new BeanPropertyRowMapper<Opportunity>(Opportunity.class));
        } catch (DataAccessException dae) {
            LOGGER.warn("Error attempting to fetch all opportunities for seeker %d", seekerId);
        }

        return opportunityList;
    }

    public Opportunity getOpportunity(int opportunityId) {
        LOGGER.trace("Fetching opportunity with id %d", opportunityId);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);

        Opportunity opportunity = (Opportunity) jobbiestNamedParamJDBCTemplate.queryForObject(OPPORTUNITY_SELECT_QUERY,
                parameterMap, new BeanPropertyRowMapper<Opportunity>(Opportunity.class));

        return opportunity;
    }

    public Integer createOpportunity(Opportunity opportunity) {
        LOGGER.trace("Creating opportunity %s", opportunity);
        
        Integer opportunityId = null;

        try {
            opportunityId = jobbiestNamedParamJDBCTemplate.queryForObject(OPPORTUNITY_INSERT_QUERY, new BeanPropertySqlParameterSource(opportunity), Integer.class);
        } catch (DataAccessException dae) {
            LOGGER.warn("Issue in data access of the opportunity", dae);
        } catch (Exception e) {
            LOGGER.warn("Issue occurred during creation of opportunity", e);
        }
        return opportunityId;
    }
    
    public boolean updateOpportunityName(int opportunityId, String name) {
        LOGGER.trace("Updating opportunity with id %d name to %s", opportunityId, name);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("name", name);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_NAME_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }
    
    public boolean updateOpportunityIndustry(int opportunityId, String industry) {
        LOGGER.trace("Updating opportunity with id %d industry to %s", opportunityId, industry);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("industry", industry);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_INDUSTRY_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }
    
    public boolean updateOpportunityAddress(int opportunityId, String address) {
        LOGGER.trace("Updating opportunity with id %d address to %s", opportunityId, address);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("address", address);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_ADDRESS_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }
    
    public boolean updateOpportunityCity(int opportunityId, String city) {
        LOGGER.trace("Updating opportunity with id %d city to %s", opportunityId, city);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("city", city);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_CITY_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }
    
    public boolean updateOpportunityState(int opportunityId, String state) {
        LOGGER.trace("Updating opportunity with id %d state to %s", opportunityId, state);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("state", state);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_STATE_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }
    
    public boolean updateOpportunityZip(int opportunityId, String zip) {
        LOGGER.trace("Updating opportunity with id %d zip to %s", opportunityId, zip);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("zip", zip);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_ZIP_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }
    
    public boolean updateOpportunityUrl(int opportunityId, String url) {
        LOGGER.trace("Updating opportunity with id %d url to %s", opportunityId, url);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("url", url);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_URL_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }

    public boolean updateOpportunityStatus(int opportunityId, String status) {
        LOGGER.trace("Updating opportunity with id %d status to %s", opportunityId, status);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("opportunity_id", opportunityId);
        parameterMap.put("status", status);

        int result = jobbiestNamedParamJDBCTemplate.update(OPPORTUNITY_UPDATE_STATUS_QUERY,
                parameterMap);

        return result == 1 ? true : false;
    }

}
