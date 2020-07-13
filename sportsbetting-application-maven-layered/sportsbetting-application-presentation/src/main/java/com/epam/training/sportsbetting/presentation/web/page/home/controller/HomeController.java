package com.epam.training.sportsbetting.presentation.web.page.home.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.home.model.HomePageText;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;
import com.epam.training.sportsbetting.presentation.web.page.home.model.WagerDescription;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

/** Get Controller of the home page.
 * */
@Controller
public class HomeController {
    private PlayerAccessInterface access;
    private PageTextProvider textProvider;

    @Value("${mapping.homeView}")
    private String homeView;

    /** Mapper method of the controller.
     * @return the name of the home view.
     * */
    @GetMapping("${mapping.homePath}")
    public String home() {
        return homeView;
    }

    /** This method provides a PlayerDetails model object to pre-fill the fields of homepage's form.
     * @param principal is provided by the Spring.
     * @return a playerDetails object based on the logged in user's data.*/
    @ModelAttribute("playerDetailsForm")
    public PlayerDetails createPlayerDetails(Principal principal) {
        return access.getPlayerDetailsByEmail(principal.getName());
    }

    /** This method provides a list of {@link WagerDescription} to the home page.
     * @param principal is provided by the Spring.
     * @return a List<WagerDescription> 
     * */
    @ModelAttribute("wagers")
    public List<WagerDescription> createWagerDescriptions(Principal principal) {
        return access.getWagerDescriptionsOfPlayer(principal.getName());
    }

    //test
    /** This method provides localized text to the home page.
     * @return {@link HomePageText}
     * */
    @ModelAttribute("text")
    public HomePageText createHomePageModell() {
        return textProvider.createHomepageText();
    }

    @Autowired
    public void setAccess(PlayerAccessInterface access) {
        this.access = access;
    }

    @Autowired
    public void setTextProvider(PageTextProvider textProvider) {
        this.textProvider = textProvider;
    }

    protected void setHomeView(String homeView) {
        this.homeView = homeView;
    }

}
