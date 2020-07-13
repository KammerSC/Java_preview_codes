package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

/** Representation of an possible outcome of a bet.
 * */
public class Outcome {
    private static final int SHIFT = 32;
    private long id;
    private String description;
    private Bet bet;
    private List<OutcomeOdd> odds = new ArrayList<OutcomeOdd>();

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

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public List<OutcomeOdd> getOdds() {
        return odds;
    }

    public void setOdds(List<OutcomeOdd> odds) {
        this.odds = odds;
    }

    /** Add the given odd to this outcome's odd list and sets the odd's outcome reference to this.
     * @param odd id an {@link OutcomeOdd}.
     * @throws IllegalArgumentException if the give odd is null.
     * */
    public void setReferences(OutcomeOdd odd) {
        if (odd != null) {
            odds.add(odd);
            odd.setOutcome(this);
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
        Outcome other = (Outcome) obj;
        return this.id == other.id;
    }

}
