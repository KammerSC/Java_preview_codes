package com.epam.training.sportsbetting.business.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;

/** Interface that give back event related domain objects like {@link SportEvent},
 * {@link Bet}, {@link Outcome}, {@link OutcomeOdd}.
 * */
public interface EventDataSource {

    /** Returns all {@link SportEvent} that ends after the given time.
     * @param time 
     * @return List<SportEvent> 
     * @throws IllegalArgumentException if the given time is null
     * */
    List<SportEvent> eventsEndAfter(LocalDateTime time);

    /** Return a {@link SportEvent} with the given id.
     * @param id 
     * @return {@link SportEvent} with the given id.
     * @throws IllegalArgumentException if the given id is 0 or less.
     * @throws NoSuchElementException if there is no {@link SportEvent} with the given id.
     * */
    SportEvent getEventById(long id);

    /** Return a {@link Bet} with the given id.
     * @param id 
     * @return {@link Bet} with the given id.
     * @throws IllegalArgumentException if the given id is 0 or less.
     * @throws NoSuchElementException if there is no {@link Bet} with the given id.
     * */
    Bet getBetById(long id);

    /** Return a {@link Outcome} with the given id.
     * @param id 
     * @return {@link Outcome} with the given id.
     * @throws IllegalArgumentException if the given id is 0 or less.
     * @throws NoSuchElementException if there is no {@link Outcome} with the given id.
     * */
    Outcome getOutcomeById(long id);

    /** Return a {@link OutcomeOdd} with the given id.
     * @param id 
     * @return {@link OutcomeOdd} with the given id.
     * @throws IllegalArgumentException if the given id is 0 or less.
     * @throws NoSuchElementException if there is no {@link OutcomeOdd} with the given id.
     * */
    OutcomeOdd getOutcomeOddById(long id);

    /** Return the valid {@link OutcomeOdd} of the given {@link Outcome} ant given time.
     * @param outcome 
     * @param time {@link LocalDateTime}
     * @return {@link OutcomeOdd} 
     * @throws IllegalArgumentException if the given outcome or the given time is null.
     * @throws NoSuchElementException if there is no {@link OutcomeOdd} to return.
     * */
    OutcomeOdd getValidOddOfOutcome(Outcome outcome, LocalDateTime time);
}
