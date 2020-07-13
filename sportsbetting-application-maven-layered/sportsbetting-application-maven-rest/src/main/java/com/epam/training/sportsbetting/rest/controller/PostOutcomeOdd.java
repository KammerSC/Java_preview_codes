package com.epam.training.sportsbetting.rest.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.sportsbetting.data.crud.OutcomeRepository;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;

/** Rest controller to add a new odd to an existing outcome.
 * */
@RestController
public class PostOutcomeOdd {

    private OutcomeRepository repo;

    /** Controller method.
     * @param id of the outcome
     * @param odd the parsed JSON 
     * @throws NoSuchElementException if there is no outcome with the given id
     * @return a message as string
     * */
    @PostMapping(value = "/rest/outcomeodd/{id}", consumes = "application/json")
    public @ResponseBody String postOdd(@PathVariable(name = "id") long id, @RequestBody OutcomeOddEntity odd) {
        OutcomeEntity outcome = repo.findById(id);
        if (outcome == null) {
            throw new NoSuchElementException();
        }
        outcome.createRelationWithOdd(odd);
        repo.save(outcome);
        return "Odd saved.";
    }

    /** Exception handler method of the controller.
     * @return a string response
     * */
    @ExceptionHandler(NoSuchElementException.class)
    public @ResponseBody String noSuchElementHandler() {
        return "There is no Ouctome with this ID.";
    }

    @Autowired
    public void setRepo(OutcomeRepository repo) {
        this.repo = repo;
    }
}
