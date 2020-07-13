package com.epam.training.sportsbetting.presentation.web.page.login.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/** This controller redirect the user to the home page after a successful login.
 * */
@Controller
public class LoginProcessingController {

    @Value("${mapping.redirectHome}")
    private String redirectPath;

    /** Returns the redirection path to the home page after a successful login.
     * @return redirection path
     * */
    @PostMapping("${mapping.loginPath}")
    public String loginRedirect() {
        return redirectPath;
    }

    protected void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

}
