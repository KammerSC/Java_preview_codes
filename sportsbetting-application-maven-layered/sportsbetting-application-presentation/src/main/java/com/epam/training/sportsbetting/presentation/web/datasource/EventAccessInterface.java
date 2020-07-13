package com.epam.training.sportsbetting.presentation.web.datasource;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetDescription;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.OutcomeDescription;

/** Provides access to Event related domain objects.
 * */
public interface EventAccessInterface {

    /** Returns a list of {@link EventDescription}.
     * @param time 
     * @return list of description or an empty list if there is no betable event.
     * @throws IllegalArgumentException if the given time is null.
     * */
    List<EventDescription> getBetableEvents(LocalDateTime time);

    /** Returns the {@link EventDescription} of the event with the given bet.
     * @param id of the event
     * @return {@link EventDescription}
     * */
    EventDescription getEventDescriptionById(long id);

    /** Returns a list of {@link BetDescription} belongs to an event.
     * @param id of the event
     * @return list of {@link BetDescription}
     * */
    List<BetDescription> getBetsOfEvent(long id);

    /** Returns a list of {@link OutcomeDescription} belongs to an bet.
     * @param id of the bet
     * @return list of {@link OutcomeDescription}
     * */
    List<OutcomeDescription> getOutcomeDescs(long id);

}
