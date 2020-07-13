package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Representation of and odd that belong to a given {@link Outcome}.
 * */
public class OutcomeOdd {
    private static final int SHIFT = 32;
    private long id;
    private Outcome outcome;
    private BigDecimal value;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
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
        OutcomeOdd other = (OutcomeOdd) obj;
        return this.id == other.id;
    }

}
