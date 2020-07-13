package com.epam.training.sportsbetting.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.data.crud.PlayerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetPlayerInfoControllerTest {

    private GetPlayerInfoController underTest;
    private ObjectMapper mapper;
    private PlayerRepository repo;

    @BeforeEach
    public void setUp() {
        underTest = new GetPlayerInfoController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("playerInfo() should return player as a stringified JSON when called")
    public void testPlayerInfoWhenTheGivenIdIsValid() throws JsonProcessingException {
        //Given
        mapper = mock(ObjectMapper.class);
        repo = mock(PlayerRepository.class);
        when(repo.findById(5L)).thenReturn(null);
        when(mapper.writeValueAsString(null)).thenReturn("FakeJSON");
        underTest.setMapper(mapper);
        underTest.setRepo(repo);

        String expected = "FakeJSON";

        //When
        String actual = underTest.playerInfo(5L);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(5L);
        verify(mapper).writeValueAsString(null);
    }

    @Test
    @DisplayName("jsonErrorHandler() should return a string when JsonProcessingException catched")
    public void testJsonErrorHandler() {
        //Given
        JsonProcessingException error = mock(JsonProcessingException.class);
        String expected = "Json Processing error";

        //When
        String actual = underTest.jsonErrorHandler(error);

        //Then
        assertEquals(expected, actual);
    }

}
