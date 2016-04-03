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

import com.acervusltd.jobbiest.db.EventTableGateway;
import com.acervusltd.jobbiest.model.Event;

@Component
@Path("/events/{seekerId}/{opportunityId}")
public class EventsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsService.class);

    @Autowired
    EventTableGateway eventTableGateway;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getEvents(@PathParam("seekerId") Integer seekerId,
            @PathParam("opportunityId") Integer opportunityId) {
        LOGGER.trace("Event list for seeker %d and opportunity %d requested.", seekerId, opportunityId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }
        if (opportunityId == null) {
            throw new RuntimeException("No opportunityId supplied.  Not returning result.");
        }

        List<Event> eventList = eventTableGateway.getEventList(seekerId, opportunityId);

        List<Map<String, Object>> eventMapList = new LinkedList<>();
        for (Event event : eventList) {
            Map<String, Object> eventMap = new TreeMap<>();
            eventMap.put("event_id", event.getEventId());
            eventMap.put("date", event.getDate());
            eventMap.put("type", event.getType());
            eventMap.put("text", event.getText());
            
            eventMapList.add(eventMap);
        }

        return eventMapList;
    }
}
