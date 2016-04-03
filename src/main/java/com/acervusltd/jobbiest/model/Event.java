package com.acervusltd.jobbiest.model;

import java.util.Date;

public class Event {

    private int eventId;
    private int seekerId;
    private int opportunityId;
    private Date date;
    private String type;
    private String text;
    
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public int getSeekerId() {
        return seekerId;
    }
    public void setSeekerId(int seekerId) {
        this.seekerId = seekerId;
    }
    public int getOpportunityId() {
        return opportunityId;
    }
    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
}
