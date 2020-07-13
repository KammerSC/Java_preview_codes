package com.epam.training.sportsbetting.presentation.web.page.home.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;

/** This controller's responsibility is to update the users details on the home page.*/
@Controller
public class SavePlayerDetailsController {
    private static final String SAVE = "save";
    private static final String ERROR = "error";

    @Value("${mapping.redirectHome}")
    private String redirectPath;
    private PlayerAccessInterface playerAccess;

    /** This method handles the user details update request. If the inputs are valid,
     *  the updated user's data will be saved in the database.
     * @param details is the PlayerDeatils provided by the Spring.
     * @param principal is the credentials provided by the Spring.
     * @return the path of redirection, should be the home page.
     * @throws IllegalArgumentException when the name is empty, blank or the balance is negative.*/
    @PostMapping(value = "${mapping.homePath}", params = SAVE)
    public String savePlayer(PlayerDetails details, Principal principal) {
        playerAccess.savePlayerChanges(principal.getName(), details);
        return redirectPath;
    }

    /** This method handlers the IllegalArgumentExceptions thrown by this controller.
     * It will notify the user about the invalid input.
     * @param attributes is a RedirectAttribute provided by the Spring. 
     * @param exception is an IllegalArgumentException thrown by the controller.
     * @return the path of redirection, should be the home page.*/
    @ExceptionHandler(IllegalArgumentException.class)
    public String exceptionHandler(RedirectAttributes attributes, IllegalArgumentException exception) {
        attributes.addFlashAttribute(ERROR, exception.getMessage());
        return redirectPath;
    }

    @Autowired
    public void setPlayerAccess(PlayerAccessInterface playerAccess) {
        this.playerAccess = playerAccess;
    }

    protected void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

}
