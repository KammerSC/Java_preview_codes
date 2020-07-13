package com.epam.training.sportsbetting.presentation.web.datasource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.business.dao.EventDataSource;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.presentation.web.page.bet.converter.BetToDescriptionConverter;
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetDescription;
import com.epam.training.sportsbetting.presentation.web.page.event.converter.EventToDescriptionConverter;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;
import com.epam.training.sportsbetting.presentation.web.page.wager.converter.OutcomeToDescriptionConverter;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.OutcomeDescription;

/** Implementation of {@link EventAccessInterface}.
 * */
public class EventAccessInterfaceImp implements EventAccessInterface {

    private EventDataSource dao;
    private EventToDescriptionConverter eventConverter;
    private BetToDescriptionConverter betConverter;
    private OutcomeToDescriptionConverter outcomeConverter;

    //test
    @Override
    public List<EventDescription> getBetableEvents(LocalDateTime time) {
        List<SportEvent> events = dao.eventsEndAfter(time);
        return events.parallelStream().map(event -> eventConverter.convert(event)).collect(Collectors.toList());
    }

    @Override
    public List<BetDescription> getBetsOfEvent(long id) {
        SportEvent event = dao.getEventById(id);
        return event.getBets().parallelStream().map(bet -> betConverter.convert(bet)).collect(Collectors.toList());
    }

    @Override
    public EventDescription getEventDescriptionById(long id) {
        SportEvent event = dao.getEventById(id);
        return eventConverter.convert(event);
    }

    @Override
    public List<OutcomeDescription> getOutcomeDescs(long id) {
        Bet bet = dao.getBetById(id);
        List<OutcomeDescription> descs = new ArrayList<OutcomeDescription>();
        for (Outcome  outcome : bet.getOutcomes()) {
            OutcomeOdd odd = dao.getValidOddOfOutcome(outcome, LocalDateTime.now());
            descs.add(outcomeConverter.convert(outcome, odd));
        }
        return descs;
    }

    @Autowired
    public void setDao(EventDataSource dao) {
        this.dao = dao;
    }

    @Autowired
    public void setEventConverter(EventToDescriptionConverter eventConverter) {
        this.eventConverter = eventConverter;
    }

    @Autowired
    public void setBetConverter(BetToDescriptionConverter betConverter) {
        this.betConverter = betConverter;
    }

    @Autowired
    public void setOutcomeConverter(OutcomeToDescriptionConverter outcomeConverter) {
        this.outcomeConverter = outcomeConverter;
    }

}
