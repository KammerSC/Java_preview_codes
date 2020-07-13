package com.epam.training.sportsbetting.presentation.web.page.fallback.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Controller for all unhandled path.
 * */
@Controller
public class FallbackController {

    @Value("${mapping.redirectHome}")
    private String redirectPath;

    //test needed
    /** Mapping for the root path.
     * @return the homepage's redirection path.*/
    @GetMapping("${mapping.fallbackPath}")
    public String redirectToHome() {
        return redirectPath;
    }

    protected void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

}
