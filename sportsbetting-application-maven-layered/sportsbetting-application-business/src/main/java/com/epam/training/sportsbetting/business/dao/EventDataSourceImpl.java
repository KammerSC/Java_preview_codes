package com.epam.training.sportsbetting.business.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.data.facade.EventRelatedFacade;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;

/** Implementation of the {@link EventDataSource} interface.
 * */
public class EventDataSourceImpl implements EventDataSource {

    private EventRelatedFacade facade;
    private EventRelatedTransformer transformer;
    private Finder finder;

    //test missing
    @Override
    public List<SportEvent> eventsEndAfter(LocalDateTime time) {
        List<SportEventEntity> entities = facade.findAllSportEventThatEndsAfter(time);
        List<SportEvent> events = entities.parallelStream().map(entity -> transformer.transformToEvent(entity)).collect(Collectors.toList());
        return events;
    }

    //test missing
    @Override
    public SportEvent getEventById(long id) {
        SportEventEntity entity = facade.findSportEventById(id);
        return transformer.transformToEvent(entity);
    }

    //test missing
    @Override
    public Bet getBetById(long id) {
        BetEntity entity = facade.findBetById(id);
        SportEvent event = transformer.transformToEvent(entity);
        return finder.findBet(event, id);
    }

    //test missing
    @Override
    public Outcome getOutcomeById(long id) {
        OutcomeEntity entity = facade.findOutcomeById(id);
        SportEvent event = transformer.transformToEvent(entity);
        return finder.findOutcome(event, id);
    }

    //test needed
    @Override
    public OutcomeOdd getOutcomeOddById(long id) {
        OutcomeOddEntity entity = facade.findOutcomeOddById(id);
        SportEvent event = transformer.transformToEvent(entity);
        return finder.findOutcomeOdd(event, id);
    }

    //test missing
    @Override
    public OutcomeOdd getValidOddOfOutcome(Outcome outcome, LocalDateTime time) {
        if (outcome == null || time == null) {
            throw new IllegalArgumentException();
        }
        return outcome.getOdds().parallelStream().filter(odd -> isTimeBetween(odd.getValidTo(), odd.getValidFrom(), time)).findAny().get();
    }

    @Autowired
    public void setFacade(EventRelatedFacade facade) {
        this.facade = facade;
    }

    @Autowired
    public void setTransformer(EventRelatedTransformer transformer) {
        this.transformer = transformer;
    }

    @Autowired
    public void setFinder(Finder finder) {
        this.finder = finder;
    }

    /** Determinate if the given time is between to and from.
     * @param upper upper boundary (inclusive)
     * @param lower lower boundary (inclusive)
     * @param time is the given time
     * @return true if the given time is between the the boundaries.
     * */
    protected boolean isTimeBetween(LocalDateTime upper, LocalDateTime lower, LocalDateTime time) {
        return lower.compareTo(time) <= 0 && upper.compareTo(time) >= 0;
    }

}
