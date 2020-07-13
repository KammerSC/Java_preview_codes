package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Representation of a sport event.
 * */
public class SportEvent {
    private static final int SHIFT = 32;
    private long id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Bet> bets = new ArrayList<Bet>();
    private Result result;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    /** Add the given bet to this event's list and sets the bet's event reference to this.
     * @param bet is a{@link Bet}.
     * @throws IllegalArgumentException if the given bet is null.
     * */
    public void setReferencesWithBet(Bet bet) {
        if (bet != null) {
            bets.add(bet);
            bet.setEvent(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> SHIFT));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SportEvent other = (SportEvent) obj;
        return this.id == other.id;
    }

}
