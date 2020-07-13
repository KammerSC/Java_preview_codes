package com.epam.training.sportsbetting.presentation.web.page.bet.model;

import com.epam.training.sportsbetting.presentation.web.text.HeaderText;

/** Provides texts to the bets page.
 * */
public class BetPageText {

    private HeaderText header;
    private String selectedEvent;
    private String bets;
    private String betType;
    private String description;
    private String newWager;

    public HeaderText getHeader() {
        return header;
    }

    public void setHeader(HeaderText header) {
        this.header = header;
    }

    public String getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(String selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public String getBets() {
        return bets;
    }

    public void setBets(String bets) {
        this.bets = bets;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewWager() {
        return newWager;
    }

    public void setNewWager(String newWager) {
        this.newWager = newWager;
    }

}
