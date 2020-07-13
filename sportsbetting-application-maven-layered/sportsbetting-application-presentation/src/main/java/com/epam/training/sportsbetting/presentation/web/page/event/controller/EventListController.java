package com.epam.training.sportsbetting.presentation.web.page.event.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.epam.training.sportsbetting.presentation.web.datasource.EventAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventPageText;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

/** Controller for event page.
 * */
@Controller
public class EventListController {
    private static final String TEXT_MODEL_NAME = "text";
    private static final String EVENTS_MODEL_NAME = "events";

    @Value("${mapping.eventView}")
    private String eventViewName;
    private EventAccessInterface access;
    private PageTextProvider provider;

    /** Mapper method of the controller.
     * @return the name of view.
     * */
    @GetMapping("${mapping.eventsPath}")
    public String listEvents() {
        return eventViewName;
    }

    /** Provides a list of event description of valid event to the view.
     * @return a list of {@link EventDescription}
     * */
    @ModelAttribute(EVENTS_MODEL_NAME)
    public List<EventDescription> provideEventList() {
        return access.getBetableEvents(LocalDateTime.now());
    }
    /** Provides {@link EventPageText} to the view.
     * @return {@link EventPageText}
     * */
    @ModelAttribute(TEXT_MODEL_NAME)
    public EventPageText provideEventPageText() {
        return provider.createEventPageText();
    }

    @Autowired
    public void setAccess(EventAccessInterface access) {
        this.access = access;
    }

    @Autowired
    public void setProvider(PageTextProvider provider) {
        this.provider = provider;
    }

    protected void setEventViewName(String eventViewName) {
        this.eventViewName = eventViewName;
    }
}
