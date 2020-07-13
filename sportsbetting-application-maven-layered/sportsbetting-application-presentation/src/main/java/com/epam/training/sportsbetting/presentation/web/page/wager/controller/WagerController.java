package com.epam.training.sportsbetting.presentation.web.page.wager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.epam.training.sportsbetting.presentation.web.datasource.EventAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerForm;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerPageText;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

/** Controller of the wager page.
 * */
@Controller
public class WagerController {

    private static final String MODEL_ATTRIBUTE_FORM = "form";
    private static final String MODEL_ATTRIBUTE_TEXT = "text";
    @Value("${mapping.wagerView}")
    private String wagerView;
    private PageTextProvider provider;
    private EventAccessInterface eventAccess;

    /** Mapper method of the wager page.
     * @return the wager page's view's name as string
     * */
    @GetMapping("${mapping.wagerPath}")
    public String wagerView() {
        return wagerView;
    }

    /** Provides a {@link WagerPageText} to the view.
     * @param id of the bet.
     * @return {@link WagerPageText}
     * */
    @ModelAttribute(MODEL_ATTRIBUTE_TEXT)
    public WagerPageText provideWagerPageText(long id) {
        return provider.createWagerPageText(id);
    }

    /** Provides a {@link WagerForm} to the view.
     * @param id of the bet.
     * @return {@link WagerForm}
     * */
    @ModelAttribute(MODEL_ATTRIBUTE_FORM)
    public WagerForm provideWagerForm(long id) {
        WagerForm form = new WagerForm();
        form.setOutcomes(eventAccess.getOutcomeDescs(id));
        return form;
    }

    protected void setWagerView(String wagerView) {
        this.wagerView = wagerView;
    }

    @Autowired
    public void setProvider(PageTextProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public void setEventAccess(EventAccessInterface eventAccess) {
        this.eventAccess = eventAccess;
    }

}
