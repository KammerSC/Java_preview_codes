package com.epam.training.sportsbetting.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.training.sportsbetting.data.crud.PlayerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Rest controller, that provide player information to clients.
 * */
@RestController
public class GetPlayerInfoController {
    private ObjectMapper mapper;
    private PlayerRepository repo;

    /** Mapping method of the controller.
     * @param id is taken from the URL.
     * @return the JSON representation of a player as string.
     * */
    @GetMapping(value = "/rest/player/{id}", produces = "application/json")
    public @ResponseBody String playerInfo(@PathVariable(name = "id") long id) throws JsonProcessingException {
        return mapper.writeValueAsString(repo.findById(id));
    }

    /** JSON related error handler of the Controller.
     * @param error threw by other controller
     * @return a message to the response body
     * */
    @ExceptionHandler(value = JsonProcessingException.class)
    public @ResponseBody String jsonErrorHandler(JsonProcessingException error) {
        return "Json Processing error";
    }

    @Autowired
    public void setRepo(PlayerRepository repo) {
        this.repo = repo;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

}
