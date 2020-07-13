package com.epam.training.sportsbetting.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.sportsbetting.data.crud.SportEventEntityRepository;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.rest.util.ReferenceSetter;

/** Rest controller to add a new {@link SportEventEntity} to the database.
 * */
@RestController
public class PostSportEventController {
    
    private SportEventEntityRepository repo;
    private ReferenceSetter setter;
    
    /** Handler method of the controller.
     * @param event is the parsed JSON from the request body
     * @return a string in the response body
     * */
    @PostMapping(value = "/rest/event", consumes = "application/json")
    public @ResponseBody String postEvent(@RequestBody SportEventEntity event) {
        setter.setReferences(event);
        repo.save(event);
        return "Event Saved";
    }

    /** Handles {@link NullPointerException} thrown by {@link PostMapping} the controllers
     * {@link PostMapping} method.
     * @return a string to the response body
     * */
    @ExceptionHandler(NullPointerException.class)
    public @ResponseBody String nullPointerHandler() {
        return "Error";
    }
    
    
    @Autowired
    public void setRepo(SportEventEntityRepository repo) {
        this.repo = repo;
    }
    
    @Autowired
    public void setSetter(ReferenceSetter setter) {
        this.setter = setter;
    }
}
