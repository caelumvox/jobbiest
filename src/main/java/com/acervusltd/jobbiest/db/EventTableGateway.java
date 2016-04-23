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

import com.acervusltd.jobbiest.model.Event;

public class EventTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String EVENT_LIST_QUERY = "select * from event where seeker_id = :seeker_id and opportunity_id = :opportunity_id";
    
    private static final String EVENT_INSERT_QUERY = "insert into event (seeker_id, opportunity_id, date, type, text) values (:seekerId, :opportunityId, :date, :type, :text) returning event_id";

    public List<Event> getEventList(int seekerId, int opportunityId) {
        LOGGER.trace("Fetching event list for seeker %d, opportunity %d", seekerId, opportunityId);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("seeker_id", seekerId);
        parameterMap.put("opportunity_id", opportunityId);

        List<Event> eventList = jobbiestNamedParamJDBCTemplate.query(EVENT_LIST_QUERY,
                parameterMap, new BeanPropertyRowMapper<Event>(Event.class));

        return eventList;
    }

    public Integer addEvent(Event event) {
        LOGGER.trace("Creating event %s", event);
        
        Integer eventId = null;

        try {
            eventId = jobbiestNamedParamJDBCTemplate.queryForObject(EVENT_INSERT_QUERY, new BeanPropertySqlParameterSource(event), Integer.class);
        } catch (DataAccessException dae) {
            LOGGER.warn("Issue in data access of the event", dae);
        } catch (Exception e) {
            LOGGER.warn("Issue occurred during creation of event", e);
        }
        return eventId;
    }

}
