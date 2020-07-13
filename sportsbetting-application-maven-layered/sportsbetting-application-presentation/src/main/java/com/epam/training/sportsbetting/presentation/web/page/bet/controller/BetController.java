package com.epam.training.sportsbetting.presentation.web.page.bet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.epam.training.sportsbetting.presentation.web.datasource.EventAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetDescription;
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetPageText;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

/** Controller of the bet page.
 * */
@Controller
public class BetController {
    private static final String MODEL_ATTRIBUTE_BETS = "bets";
    private static final String MODEL_ATTRIBUTE_EVENT = "event";
    private static final String MODEL_ATTRIBUTE_TEXT = "text";
    @Value("${mapping.betView}")
    private String betPageView;
    private EventAccessInterface access;
    private PageTextProvider provider;

    /** Mapper method that returns the name of the view.
     * @return the name of the view  
     * */
    @GetMapping("${mapping.betPath}")
    public String listBets() {
        return betPageView;
    }

    /** Provides a {@link BetPageText} to the view.
     * @param id of the event
     * @return {@link BetPageText}
     * */
    @ModelAttribute(MODEL_ATTRIBUTE_TEXT)
    public BetPageText provideBetPageText(long id) {
        return provider.createBetPageText(id);
    }

    /** Provides an {@link EventDescription} to the view.
     * @param id of the event
     * @return {@link EventDescription}
     * */
    @ModelAttribute(MODEL_ATTRIBUTE_EVENT)
    public EventDescription provideEventDescription(long id) {
        return access.getEventDescriptionById(id);
    }

    /** Provides a list of {@link BetDescription} to the view.
     * @param id of the event
     * @return {@link BetDescription}
     * */
    @ModelAttribute(MODEL_ATTRIBUTE_BETS)
    public List<BetDescription> provideBetDescriptions(long id) {
        return access.getBetsOfEvent(id);
    }

    @Autowired
    public void setAccess(EventAccessInterface access) {
        this.access = access;
    }

    @Autowired
    public void setProvider(PageTextProvider provider) {
        this.provider = provider;
    }

    protected void setBetPageView(String betPageView) {
        this.betPageView = betPageView;
    }

}
