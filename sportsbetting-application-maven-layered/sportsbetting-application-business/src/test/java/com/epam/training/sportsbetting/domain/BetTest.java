package com.epam.training.sportsbetting.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BetTest {

    private Bet underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Bet();
        underTest.setId(1);
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("setReferenceswithOutcome() should add the given Outcome to the bet's outcome list and set the outcome's bet field to the bet when called with a outcome.")
    public void testSetReferenceswithOutcomeWithOutcome() {
        //Given
        Outcome outcome = Mockito.mock(Outcome.class);

        //When
        underTest.setReferenceswithOutcome(outcome);

        //Then
        assertTrue(underTest.getOutcomes().contains(outcome));
        verify(outcome).setBet(underTest);
    }

    @Test
    @DisplayName("setReferenceswithOutcome() should throw IllegalArgumentException when called with null.")
    public void testSetReferenceswithOutcomeWithNull() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.setReferenceswithOutcome(null);
        });
    }

}
