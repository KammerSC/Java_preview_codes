package com.epam.training.sportsbetting.presentation.web.page.home.controller;

import java.security.Principal;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;

/** This controllers responsibility is to delete a wager with the given id if it exists and not processed yet.
 * */
@Controller
public class DeleteWagerController {
    private static final String ERROR = "error";

    @Value("${mapping.redirectHome}")
    private String redirectPath;
    private PlayerAccessInterface access;

    //test
    /** Handle the delete request of the given wager and refund the wager's amount.
     * @param wagerId the id of wager that should be deleted.
     * @param principal is provided by the Spring.
     * @return the path of redirection, should be the home page.
     * @throws java.util.NoSuchElementException if no wager with this id exists.*/
    @GetMapping("/home/"+"{wagerId}")
    public String deleteWager(@PathVariable("wagerId") long wagerId, Principal principal) {
        access.deleteWager(wagerId, principal.getName());
        return redirectPath;
    }

    //test
    /** This method handlers the NoSuchElementExceptions thrown by this controller.
     * It will notify the user about that the given wager cannot be deleted. 
     * @param attributes is a RedirectAttribute provided by the Spring. 
     * @param exception is an NoSuchElementException thrown by the controller.
     * @return the path of redirection, should be the home page.*/
    @ExceptionHandler(NoSuchElementException.class)
    public String exceptionHandler(RedirectAttributes attributes, IllegalArgumentException exception) {
        attributes.addFlashAttribute(ERROR, exception.getMessage());
        return redirectPath;
    }

    @Autowired
    public void setAccess(PlayerAccessInterface access) {
        this.access = access;
    }

    protected void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }

}
