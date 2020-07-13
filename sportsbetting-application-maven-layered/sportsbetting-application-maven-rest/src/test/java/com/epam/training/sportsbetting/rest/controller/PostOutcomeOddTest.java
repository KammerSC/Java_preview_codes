package com.epam.training.sportsbetting.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.data.crud.OutcomeRepository;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;

public class PostOutcomeOddTest {

    private PostOutcomeOdd underTest;
    private OutcomeRepository repo;

    @BeforeEach
    public void setUp() {
        underTest = new PostOutcomeOdd();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("postOdd() should save the given odd when supplied with a valid id and an odd")
    public void testPostOddWithValidIdAndOdd() {
        //Given
        repo = mock(OutcomeRepository.class);
        OutcomeOddEntity odd = mock(OutcomeOddEntity.class);
        OutcomeEntity outcome = mock(OutcomeEntity.class);
        when(repo.findById(5L)).thenReturn(outcome);
        underTest.setRepo(repo);

        String expected = "Odd saved.";

        //When
        String actual = underTest.postOdd(5L, odd);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(5L);
        verify(outcome).createRelationWithOdd(odd);
        verify(repo).save(outcome);
    }

    @Test
    @DisplayName("postOdd() should throw NoSuchElementException when supplied with a invalid")
    public void testPostOddWithInvalidId() {
        //Given
        repo = mock(OutcomeRepository.class);
        when(repo.findById(5L)).thenReturn(null);
        underTest.setRepo(repo);

        //When

        //Then
        assertThrows(NoSuchElementException.class, () -> underTest.postOdd(5L, new OutcomeOddEntity()));
        verify(repo).findById(5L);
    }

    @Test
    @DisplayName("noSuchElementHandler() should return a string when caught an exception")
    public void testNoSuchElementHandler() {
        //Given
        String expected = "There is no Ouctome with this ID.";

        //When
        String actual = underTest.noSuchElementHandler();

        //Then
        assertEquals(expected, actual);
    }

}
