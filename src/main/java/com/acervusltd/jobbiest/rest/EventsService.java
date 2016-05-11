package com.acervusltd.jobbiest.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.acervusltd.jobbiest.db.EventsTableGateway;
import com.acervusltd.jobbiest.model.Event;

@Component
@Path("/events")
public class EventsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsService.class);
    private static final String DATE_KEY = "date";
    private static final String TYPE_KEY = "type";
    private static final String TEXT_KEY = "text";
    private static final String FORM_DATE_STRING = "yyyy-MM-dd'T'HH:mm";
    
    @Autowired
    EventsTableGateway eventTableGateway;

    @GET
    @Path("/{seekerId}/{opportunityId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents(@PathParam("seekerId") Integer seekerId,
            @PathParam("opportunityId") Integer opportunityId) {
        LOGGER.trace("Event list for seeker {} and opportunity {} requested.", seekerId, opportunityId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }
        if (opportunityId == null) {
            throw new RuntimeException("No opportunityId supplied.  Not returning result.");
        }

        List<Event> eventList = eventTableGateway.getEventList(seekerId, opportunityId);

        return eventList;
    }
    
    private static Date extractDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat(FORM_DATE_STRING);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse date string.", e);
        }
        return date;
    }
    
    @POST
    @Path("/{seekerId}/{opportunityId}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Event addEvent(@PathParam("seekerId") Integer seekerId,
            @PathParam("opportunityId") Integer opportunityId,
            MultivaluedMap<String, String> formParams) {
        LOGGER.trace("Request received for adding event to seeker {} and opportunity {}.", seekerId, opportunityId);

        if (seekerId == null) {
            throw new RuntimeException("No seekerId supplied.  Not returning result.");
        }
        if (opportunityId == null) {
            throw new RuntimeException("No opportunityId supplied.  Not returning result.");
        }

        Event event = new Event();
        event.setSeekerId(seekerId);
        event.setOpportunityId(opportunityId);
        
        for (String key : formParams.keySet()) {
            String value = formParams.getFirst(key);

            if (value != null) {
                switch (key) {
                    case DATE_KEY:
                        Date date = extractDate(value);
                        event.setDate(date);
                        break;
                    case TYPE_KEY:
                        event.setType(value);
                        break;
                    case TEXT_KEY:
                        event.setText(value);
                        break;
                    default:
                        LOGGER.warn("Unidentified parameter {}, ignoring.", key);
                }
            }
        }
        
        int eventId = eventTableGateway.addEvent(event);
        event.setEventId(eventId);

        return event;
    }    
}
