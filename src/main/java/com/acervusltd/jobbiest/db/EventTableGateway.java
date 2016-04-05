package com.acervusltd.jobbiest.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.Event;

public class EventTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String eventListQuery = "select * from event where seeker_id = :seeker_id and opportunity_id = :opportunity_id";

    public List<Event> getEventList(int seekerId, int opportunityId) {
        LOGGER.trace("Fetching event list for seeker %d, opportunity %d", seekerId, opportunityId);

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("seeker_id", seekerId);
        parameterMap.put("opportunity_id", opportunityId);

        List<Event> eventList = jobbiestNamedParamJDBCTemplate.query(eventListQuery,
                parameterMap, new BeanPropertyRowMapper<Event>(Event.class));

        return eventList;
    }

}
