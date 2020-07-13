package com.epam.training.sportsbetting.presentation.web.page.home.model;

/** Class that holds the description of a wager.
 * */
public class WagerDescription {
    private String eventTitle;
    private String eventType;
    private String betType;
    private String outcomeValue;
    private String outcomeOdd;
    private String wagerAmount;
    private String win;
    private String processed;
    private String deleteURL;
    private long id;

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

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getOutcomeValue() {
        return outcomeValue;
    }

    public void setOutcomeValue(String outcomeValue) {
        this.outcomeValue = outcomeValue;
    }

    public String getOutcomeOdd() {
        return outcomeOdd;
    }

    public void setOutcomeOdd(String outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }

    public String getWagerAmount() {
        return wagerAmount;
    }

    public void setWagerAmount(String wagerAmount) {
        this.wagerAmount = wagerAmount;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public String getDeleteURL() {
        return deleteURL;
    }

    public void setDeleteURL(String deleteURL) {
        this.deleteURL = deleteURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
