package com.epam.training.sportsbetting.data.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OutcomeEntityTest {
    private OutcomeEntity underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OutcomeEntity();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("Method createRelationWithOdd should add outcomeodd to outcome's OutcomeOddEntity list and set the outcomeodd's reference to this outcome.")
    public void testCreateRelationWithOddWhenOneOddGiven() {
        //Given
        OutcomeOddEntity odd = Mockito.mock(OutcomeOddEntity.class);

        //When
        underTest.createRelationWithOdd(odd);

        //Then
        Mockito.verify(odd).setOutcome(underTest);
        assertTrue(underTest.getOdds().contains(odd));
    }
    
    @Test
    @DisplayName("Method createRelationWithOdd should add odds to outcome's OutcomeOddEntity list and set the all odd's reference to this outcome.")
    public void testCreateRelationWithOddWhenMultipileOddGiven() {
      //Given
        OutcomeOddEntity oddA = Mockito.mock(OutcomeOddEntity.class);
        OutcomeOddEntity oddB = Mockito.mock(OutcomeOddEntity.class);
        OutcomeOddEntity oddC = Mockito.mock(OutcomeOddEntity.class);
        OutcomeOddEntity oddD = Mockito.mock(OutcomeOddEntity.class);

        //When
        underTest.createRelationWithOdd(oddA);
        underTest.createRelationWithOdd(oddB);
        underTest.createRelationWithOdd(oddC);
        underTest.createRelationWithOdd(oddD);

        //Then
        Mockito.verify(oddA).setOutcome(underTest);
        Mockito.verify(oddB).setOutcome(underTest);
        Mockito.verify(oddC).setOutcome(underTest);
        Mockito.verify(oddD).setOutcome(underTest);
        assertTrue(underTest.getOdds().contains(oddA));
        assertTrue(underTest.getOdds().contains(oddB));
        assertTrue(underTest.getOdds().contains(oddC));
        assertTrue(underTest.getOdds().contains(oddD));
    }

}
