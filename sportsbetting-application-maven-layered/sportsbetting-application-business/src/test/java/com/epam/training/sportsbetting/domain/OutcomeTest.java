package com.epam.training.sportsbetting.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OutcomeTest {

    private Outcome underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Outcome();
        underTest.setId(4);
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("setReferences() should add the given odd to the SUT's oddlist and set the odd's outcome reference to SUT when called with an odd.")
    public void testSetReferencesWithAnOdd() {
        //Give
        OutcomeOdd odd = Mockito.mock(OutcomeOdd.class);

        //When
        underTest.setReferences(odd);

        //Then
        assertTrue(underTest.getOdds().contains(odd));
        verify(odd).setOutcome(underTest);
    }

    @Test
    @DisplayName("setReferences() should throw IllegalArgumentException when called with null.")
    public void testSetReferencesWithNull() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.setReferences(null);
        });
    }

}
