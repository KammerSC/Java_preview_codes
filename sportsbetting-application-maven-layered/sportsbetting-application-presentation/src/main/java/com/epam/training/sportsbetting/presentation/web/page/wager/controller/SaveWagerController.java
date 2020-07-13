package com.epam.training.sportsbetting.presentation.web.page.wager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerForm;

/** Controller to save a new wager.
 * */
@Controller
public class SaveWagerController {
    private static final String PARAMETER = "save=true";

    @Value("${mapping.redirectHome}")
    private String redirectHome;
    private PlayerAccessInterface access;

    /** Handler method for saving the given wager.
     * @param form {@link WagerForm} provided by the spring.
     * @param principal {@link Principal} 
     * @return a redirection path as string
     * */
    @PostMapping(value = "${mapping.wagerPath}", params = PARAMETER)
    public String saveWager(WagerForm form, Principal principal) {
        access.saveWager(form, principal.getName());
        return redirectHome;
    }

    @Autowired
    public void setAccess(PlayerAccessInterface access) {
        this.access = access;
    }

    protected void setRedirectHome(String redirectHome) {
        this.redirectHome = redirectHome;
    }

}
