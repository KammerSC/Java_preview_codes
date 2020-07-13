package com.epam.training.sportsbetting.business.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.data.util.BetType;
import com.epam.training.sportsbetting.data.util.SportEventType;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Result;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.TennisSportEvent;
import com.epam.training.sportsbetting.domain.Type;

/** Transformer that transforms Entity objects such as {@link SportEventEntity}, {@link BetEntity},
 *  {@link OutcomeEntity}, {@link OutcomeOddEntity} to domain objects.
 * */
public class EventRelatedTransformer {

    /** Transforms the given {@link OutcomeOddEntity} to an {@link OutcomeOdd}.
     * @param entity 
     * @return {@link OutcomeOdd} 
     * */
    protected OutcomeOdd oddEntityToOdd(OutcomeOddEntity entity) {
        OutcomeOdd odd = new OutcomeOdd();
        odd.setId(entity.getId());
        odd.setValidFrom(entity.getValidFrom());
        odd.setValidTo(entity.getValidUntil());
        odd.setValue(entity.getValue());
        return odd;
    }

    /** Transforms the given {@link OutcomeEntity} to an {@link Outcome}.
     * @param entity 
     * @return {@link Outcome} 
     * */
    protected Outcome outcomeEntityToOutcome(OutcomeEntity entity) {
        Outcome outcome = new Outcome();
        outcome.setId(entity.getId());
        outcome.setDescription(entity.getDescription());
        entity.getOdds().forEach(odd -> outcome.setReferences(oddEntityToOdd(odd)));
        return outcome;
    }

    /** Transforms the given {@link BetEntity} to a {@link Bet}.
     * @param entity 
     * @return {@link Bet} 
     * */
    protected Bet betEntityToBet(BetEntity entity) {
        Bet bet = new Bet();
        bet.setId(entity.getId());
        bet.setDescription(entity.getDescription());
        bet.setType(selectType(entity.getType()));
        entity.getOutcomes().forEach(outcome -> bet.setReferenceswithOutcome(outcomeEntityToOutcome(outcome)));
        return bet;
    }

    /** Transforms the given {@link SportEventEntity} to a {@link SportEvent}.
     * This method supposed to transform Events that doesn't have result yet.
     * If the event already finished use {@link #sportEventEntityToSportEventWithResult(SportEventEntity)}
     * @param entity 
     * @return {@link SportEvent}
     * */
    protected SportEvent sportEventEntityToSportEvent(SportEventEntity entity) {
        SportEvent event = getEvent(entity.getType());
        event.setId(entity.getId());
        event.setTitle(entity.getTitle());
        event.setStartDate(entity.getStart());
        event.setEndDate(entity.getEnd());
        entity.getBets().forEach(bet -> event.setReferencesWithBet(betEntityToBet(bet)));
        return event;
    }

    /** Transforms the given {@link SportEventEntity} to a {@link SportEvent}.
     * This method set the result field.
     * @param entity 
     * @return {@link SportEvent}
     * */
    //test missing
    protected SportEvent sportEventEntityToSportEventWithResult(SportEventEntity entity) {
        SportEvent event = sportEventEntityToSportEvent(entity);
        Result result = getResult(event.getBets(), entity.getBets());
        event.setResult(result);
        return event;
    }

    /** Transforms the given entity to a {@link SportEvent}.
     * @param entity {@link SportEventEntity}
     * @return A {@link SportEvent} with all its bet, outcome and odd
     * */
    //test missing
    protected SportEvent transformToEvent(SportEventEntity entity) {
        return sportEventEntityToSportEventWithResult(entity);
    }

    /** Transforms the given entity to a {@link SportEvent}.
     * @param entity {@link BetEntity}
     * @return A {@link SportEvent} with all its bet, outcome and odd
     * */
    //test missing
    protected SportEvent transformToEvent(BetEntity entity) {
        return transformToEvent(entity.getEvent());
    }

    /** Transforms the given entity to a {@link SportEvent}.
     * @param entity {@link OutcomeEntity}
     * @return A {@link SportEvent} with all its bet, outcome and odd
     * */
    //test missing
    protected SportEvent transformToEvent(OutcomeEntity entity) {
        return transformToEvent(entity.getBet());
    }

    /** Transforms the given entity to a {@link SportEvent}.
     * @param entity {@link OutcomeOddEntity}
     * @return A {@link SportEvent} with all its bet, outcome and odd
     * */
    //test missing
    protected SportEvent transformToEvent(OutcomeOddEntity entity) {
        return transformToEvent(entity.getOutcome());
    }

    private Result getResult(List<Bet> bets, List<BetEntity> betentities) {
        Result result = new Result();
        List<Long> winningIds = findWinningOutcomeIds(betentities);
        List<Outcome> winningOutcomes = findWinningOutcomes(bets, winningIds);
        result.setWinningOutcomes(winningOutcomes);
        return result;
    }

    private List<Outcome> findWinningOutcomes(List<Bet> bets, List<Long> winningIds) {
        List<Outcome> winningOutcomes = new ArrayList<Outcome>();
        Map<Long, Outcome> outcomeMap = new HashMap<Long, Outcome>();
        bets.forEach(bet -> bet.getOutcomes().forEach(outcome -> outcomeMap.put(outcome.getId(), outcome)));
        winningIds.forEach(id -> winningOutcomes.add(outcomeMap.get(id)));
        return winningOutcomes;
    }

    private List<Long> findWinningOutcomeIds(List<BetEntity> betEntities) {
        List<Long> winningIds = new ArrayList<Long>();
        for (BetEntity bet : betEntities) {
            OutcomeEntity current = bet.getOutcomes().parallelStream().filter(outcome -> outcome.isWinner()).findFirst().orElse(null);
            if(current != null) {
                winningIds.add(current.getId());
            }
        }
        return winningIds;
    }

    private Type selectType(BetType betType) {
        Type result = Type.NO_TYPE;
        for (Type type : Type.values()) {
            if (betType.toString().equals(type.toString())) {
                result = type;
                break;
            }
        }
        return result;
    }

    private SportEvent getEvent(SportEventType type) {
        SportEvent result;
        switch (type) {
        case FOOTTBALL:
            result = new FootballSportEvent();
            break;
        case TENNIS:
            result = new TennisSportEvent();
            break;
        default:
            result = new SportEvent();
        }
        return result;
    }

}
