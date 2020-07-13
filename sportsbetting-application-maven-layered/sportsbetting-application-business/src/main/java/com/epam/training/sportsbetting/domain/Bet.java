package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

/** Representation of a bet that belongs to a {@link SportEvent}.
 * */
public class Bet {
    private static final int SHIFT = 32;
    private long id;
    private String description;
    private SportEvent event;
    private Type type;
    private List<Outcome> outcomes = new ArrayList<Outcome>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportEvent getEvent() {
        return event;
    }

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    /** Add the give bet to this events list and sets the bets event reference to this.
     * @param outcome is a {@link Outcome}
     * @throws IllegalArgumentException if the given {@link Outcome} is null.
     * */
    public void setReferenceswithOutcome(Outcome outcome) {
        if (outcome != null) {
            outcomes.add(outcome);
            outcome.setBet(this);
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
        Bet other = (Bet) obj;
        return this.id == other.id;
    }
}
