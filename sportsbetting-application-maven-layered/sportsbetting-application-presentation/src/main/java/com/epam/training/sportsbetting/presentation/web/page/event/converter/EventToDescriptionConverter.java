package com.epam.training.sportsbetting.presentation.web.page.event.converter;

import org.springframework.beans.factory.annotation.Value;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;

/** Class that converts {@link SportEvent} to a {@link EventDescription}.
 * */
public class EventToDescriptionConverter {

    @Value("${mapping.betBasePath}")
    private String betBasePath;

    //test
    /** Transforms a {@link SportEvent} to a {@link EventDescription}.
     * @param source {@link SportEvent} to transform.
     * @return {@link EventDescription} 
     * */
    public EventDescription convert(SportEvent source) {
        EventDescription result = null;
        if (source != null) {
            result = new EventDescription();
            result.setDate(source.getStartDate().toString() + "-" + source.getEndDate().toString());
            result.setTitle(source.getTitle());
            result.setType("asd");
            result.setUrl(betBasePath + source.getId());
        }
        return result;
    }

    protected void setBetBasePath(String betBasePath) {
        this.betBasePath = betBasePath;
    }

}
