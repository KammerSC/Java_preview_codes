package com.epam.training.sportsbetting.presentation.web.page.event.model;

import com.epam.training.sportsbetting.presentation.web.text.HeaderText;

/** Provides texts to the events page.
 * */
public class EventPageText {
    private HeaderText header;
    private String events;
    private String eventTitle;
    private String eventType;
    private String eventDate;
    private String bet;
    public HeaderText getHeader() {
        return header;
    }
    public void setHeader(HeaderText header) {
        this.header = header;
    }
    public String getEvents() {
        return events;
    }
    public void setEvents(String events) {
        this.events = events;
    }
    public String getEventTitle() {
        return eventTitle;
    }
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public String getBet() {
        return bet;
    }
    public void setBet(String bet) {
        this.bet = bet;
    }
}
