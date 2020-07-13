package com.epam.training.sportsbetting.rest.util;

import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;

public class ReferenceSetter {
    
    public void setReferences(SportEventEntity event) {
        setBetReferences(event);
    }

    protected void setBetReferences(SportEventEntity event) {
        for (BetEntity bet : event.getBets()) {
            bet.setEvent(event);
            setOutcomeRefenrences(bet);
        }
        
    }

    protected void setOutcomeRefenrences(BetEntity bet) {
        for(OutcomeEntity outcome : bet.getOutcomes()) {
            outcome.setBet(bet);
            setOutcomeOddReference(outcome);
        }
    }

    protected void setOutcomeOddReference(OutcomeEntity outcome) {
        for(OutcomeOddEntity odd : outcome.getOdds()) {
            odd.setOutcome(outcome);
        }
    }
}
