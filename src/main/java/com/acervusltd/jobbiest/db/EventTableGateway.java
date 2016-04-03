package com.acervusltd.jobbiest.db;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.acervusltd.jobbiest.model.Event;

public class EventTableGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventTableGateway.class);

    @Autowired
    private NamedParameterJdbcTemplate jobbiestNamedParamJDBCTemplate;

    private static final String eventListQuery = "select * from event where seeker_id = :seeker_id and opp_id = :opp_id";

    public List<Event> getEventList(int seekerId, int opportunityId) {
        LOGGER.trace("Fetching event list for seeker %d, opportunity %d", seekerId, opportunityId);
        List<Event> eventList = new LinkedList<>();

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("seeker_id", seekerId);
        parameterMap.put("opp_id", opportunityId);
        List<Map<String, Object>> resultList = jobbiestNamedParamJDBCTemplate.queryForList(eventListQuery,
                parameterMap);

        for (Map<String, Object> result : resultList) {
            Event event = new Event();
            event.setEventId((int) result.get("event_id"));
            event.setDate((Date) result.get("date"));
            event.setType((String) result.get("type"));
            event.setText((String) result.get("text"));

            eventList.add(event);
        }

        return eventList;
    }

}
