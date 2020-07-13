package com.epam.training.sportsbetting.presentation.web.page.logout.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** This controller redirects the user when logged out.
 * */
@Controller
public class LogoutController {

    @Value("${mapping.redirectLogin}")
    private String redirectPath;

    /** Returns the path to redirect when logged out.
     * @return the redirection path as String.
     * */
    @GetMapping("${mapping.logoutPath}")
    public String logout() {
        return redirectPath;
    }

    protected void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

}
