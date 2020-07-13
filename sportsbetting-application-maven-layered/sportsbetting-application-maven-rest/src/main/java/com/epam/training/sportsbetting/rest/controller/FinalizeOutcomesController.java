package com.epam.training.sportsbetting.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.sportsbetting.rest.util.ListWrapper;
import com.epam.training.sportsbetting.rest.util.OutcomeFinalizer;

/** A REST controller to finalize the the given outcomes, odds and related to wagers.
 * */
@RestController
public class FinalizeOutcomesController {
    
    private OutcomeFinalizer finalizer;
    
    /** Finalize the 
     * */
    @PostMapping(value = "/rest/finalize", consumes = "application/json")
    public void finalizeOutcomes(@RequestBody ListWrapper wrapper) {
        finalizer.finalizeOutcomes(wrapper);
    }

    @Autowired
    public void setFinalizer(OutcomeFinalizer finalizer) {
        this.finalizer = finalizer;
    }
}
