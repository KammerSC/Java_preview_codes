package com.epam.training.sportsbetting.business.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;

/** Provide methods to find the {@link Bet}, {@link Outcome} or {@link OutcomeOdd} of the given event.
 * */
public class Finder {

    /** Search for a bet with the given id in the event's bet list.
     * @param event {@link SportEvent}
     * @param betId id of the bet
     * @throws NullPointerException if the given event is null
     * @throws NoSuchElementException if there is no bet with the given id
     * @return {@link Bet}
     * */
    protected Bet findBet(SportEvent event, long betId) {
        return event.getBets().parallelStream().filter(bet -> bet.getId() == betId).findFirst().get();
    }

    /** Search for an outcome with the given id that belongs to one of the bet of the given event.
     * @param event {@link SportEvent}
     * @param outcomeId id of the outcome
     * @throws NullPointerException if the given event is null
     * @throws NoSuchElementException if there is no outcome with the given id
     * @return {@link Bet}
     * */
    protected Outcome findOutcome(SportEvent event, long outcomeId) {
        List<Outcome> outcomes = outcomesOfEvent(event);
        return outcomes.parallelStream().filter(outcome -> outcome.getId() == outcomeId).findAny().get();
    }

    /** Search for an odd with the given id that belongs to one of the outcome of the given event.
     * @param event {@link SportEvent}
     * @param oddId id of the odd
     * @throws NullPointerException if the given event is null
     * @throws NoSuchElementException if there is no odd with the given id
     * @return {@link Bet}
     * */
    protected OutcomeOdd findOutcomeOdd(SportEvent event, long oddId) {
        List<OutcomeOdd> odds = oddsOfEvent(event);
        return odds.parallelStream().filter(odd -> odd.getId() == oddId).findAny().get();
    }

    private List<Outcome> outcomesOfEvent(SportEvent event) {
        List<Outcome> outcomes = new ArrayList<Outcome>();
        event.getBets().forEach(bet -> outcomes.addAll(bet.getOutcomes()));
        return outcomes;
    }

    private List<OutcomeOdd> oddsOfEvent(SportEvent event) {
        List<OutcomeOdd> odds = new ArrayList<OutcomeOdd>();
        outcomesOfEvent(event).forEach(outcome -> odds.addAll(outcome.getOdds()));
        return odds;
    }

}
