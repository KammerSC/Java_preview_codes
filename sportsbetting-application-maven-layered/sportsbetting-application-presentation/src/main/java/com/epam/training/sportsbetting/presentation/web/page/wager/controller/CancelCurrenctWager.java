package com.epam.training.sportsbetting.presentation.web.page.wager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CancelCurrenctWager {

    @Value("${mapping.redirectWager}")
    private String redirectPath;

    //test
    @PostMapping(value = "${mapping.wagerPath}", params = "save=false")
    public String cancelWager(int id) {
        return redirectPath + id;
    }

    protected void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

}
