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

public class OpportunityTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpportunityTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String OPPORTUNITY_SELECT_LIST_QUERY = "select * from opportunity where seeker_id = :seeker_id";
    private static final String OPPORTUNITY_SELECT_QUERY = "select * from opportunity where opportunity_id = :opportunity_id";
    private static final String OPPORTUNITY_INSERT_QUERY = "insert into opportunity (name, industry, address, city, state, zip, url, status, seeker_id) values (:name, :industry, :address, :city, :state, :zip, :url, :status, :seekerId) returning opportunity_id";

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
}
