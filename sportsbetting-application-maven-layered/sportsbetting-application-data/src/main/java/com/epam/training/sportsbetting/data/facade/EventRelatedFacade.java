package com.epam.training.sportsbetting.data.facade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;

/** Facade for {@link SportEventEntity}, {@link BetEntity}, {@link OutcomeEntity}, {@link OutcomeOddEntity} repositories.
 * */
public interface EventRelatedFacade {

    /** Gives back a {@link SportEventEntity} with the given id.
     * @param id of the SportEventEntity.
     * @return SportEventEntity
     * @throws NoSuchElementException if there is no SportEventEntity with the give id.
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    SportEventEntity findSportEventById(long id);

    /** Gives back a {@link List<SportEventEntity>} that ends after the given time.
     * @param time 
     * @return List<SportEventEntity> 
     * @throws IllegalArgumentException if the given time is null.
     * */
    List<SportEventEntity> findAllSportEventThatEndsAfter(LocalDateTime time);

    /** Gives back a {@link BetEntity} with the given id.
     * @param id of the bet.
     * @return Bet with the given ID.
     * @throws NoSuchElementException if there is no BetEntity with the give id.
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    BetEntity findBetById(long id);

    /** Gives back a SportEventEntity's all bet as a {@link List<BetEntity>}.
     * @param id of the event.
     * @return List<BetEntity> 
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    List<BetEntity> findBetsByEventId(long id);

    /** Gives back a {@link OutcomeEntity} with the given id.
     * @param id of the Outcome.
     * @return OutcomeEntity with the given ID.
     * @throws NoSuchElementException if there is no OutcomeEntity with the give id.
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    OutcomeEntity findOutcomeById(long id);

    /** Gives back an Bet's all Outcome as a {@link List<OutcomeEntity>}.
     * @param id of the bet.
     * @return List<OutcomeEntity>
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    List<OutcomeEntity> findOutcomesByBetId(long id);

    /** Gives back a {@link OutcomeOddEntity} with the given id.
     * @param id of the OutcomeOdd.
     * @return OutcomeOddEntity with the given ID.
     * @throws NoSuchElementException if there is no OutcomeOddEntity with the give id.
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    OutcomeOddEntity findOutcomeOddById(long id);

    /** Gives back an Outcome's all Odd as a {@link List<OutcomeOddEntity>}.
     * @param id of the Outcome.
     * @param time 
     * @return List<OutcomeOddEntity>
     * @throws NoSuchElementException if there is no valid Odd at the given time.
     * @throws IllegalArgumentException if the given id is equal or less then 0.
     * */
    OutcomeOddEntity findValidOddOfOutcome(long id, LocalDateTime time);

}
