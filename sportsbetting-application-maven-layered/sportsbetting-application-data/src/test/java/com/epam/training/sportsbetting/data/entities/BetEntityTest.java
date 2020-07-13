package com.epam.training.sportsbetting.data.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.training.sportsbetting.data.util.BetType;

public class BetEntityTest {
    
    private BetEntity underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BetEntity();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("Method createRelationWithOutcome should add outcome to bet's OutcomeEntity list and set the outcome's reference to this bet.")
    public void testCreateRelationWithOutcomeWhenOneOutcomeGiven() {
        //Given
        OutcomeEntity outcome = Mockito.mock(OutcomeEntity.class);

        //When
        underTest.createRelationWithOutcome(outcome);

        //Then
        Mockito.verify(outcome).setBet(underTest);
        assertTrue(underTest.getOutcomes().contains(outcome));
    }
    
    @Test
    @DisplayName("Method createRelationWithOutcome should add outcomes to bet's OutcomeEntity list and set the all outcome's reference to this bet.")
    public void testCreateRelationWithBetWhenMultipileBetGiven() {
        //Given
        OutcomeEntity outcomeA = Mockito.mock(OutcomeEntity.class);
        OutcomeEntity outcomeB = Mockito.mock(OutcomeEntity.class);
        OutcomeEntity outcomeC = Mockito.mock(OutcomeEntity.class);
        OutcomeEntity outcomeD = Mockito.mock(OutcomeEntity.class);

        //When
        underTest.createRelationWithOutcome(outcomeA);
        underTest.createRelationWithOutcome(outcomeB);
        underTest.createRelationWithOutcome(outcomeC);
        underTest.createRelationWithOutcome(outcomeD);

        //Then
        Mockito.verify(outcomeA).setBet(underTest);
        Mockito.verify(outcomeB).setBet(underTest);
        Mockito.verify(outcomeC).setBet(underTest);
        Mockito.verify(outcomeD).setBet(underTest);
        assertTrue(underTest.getOutcomes().contains(outcomeA));
        assertTrue(underTest.getOutcomes().contains(outcomeB));
        assertTrue(underTest.getOutcomes().contains(outcomeC));
        assertTrue(underTest.getOutcomes().contains(outcomeD));
    }
    
    @Test
    @DisplayName("hashCode() should be same as an other instance of this class with same fields.")
    public void testHashCodeWithRelevantFieldFilled() {
        //Given
        underTest.setDescription("desc");
        underTest.setId(5);
        underTest.setType(BetType.GOALS);
        BetEntity other = new BetEntity();
        other.setDescription("desc");
        other.setId(5);
        other.setType(BetType.GOALS);
        int expected = other.hashCode();
        
        //When
        int actual = underTest.hashCode();
        
        //Then
        assertNotSame(other, underTest);
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("equals() should return true when compared with the same object.")
    public void testEqualsWithTheSameObject() {
        //Given
        //When
        //Then
        assertTrue(underTest.equals(underTest));
    }
    @Test
    @DisplayName("equals() should return false when compared with null.")
    public void testEqualsWithNull() {
        //Given
        //When
        //Then
        assertFalse(underTest.equals(null));
    }
    @Test
    @DisplayName("equals() should return false when compared with an object from a different class.")
    public void testEqualsWithDiffrentClass() {
        //Given
        //When
        //Then
        assertFalse(underTest.equals(BigDecimal.ZERO));
    }
    
    @Test
    @DisplayName("equals() should return false when compared with bet with different id.")
    public void testEqualsWithDiffrentBet() {
        //Given
        underTest.setId(9);
        BetEntity other = new BetEntity();
        other.setId(10);
        //When
        //Then
        assertFalse(underTest.equals(other));
    }
    
    @Test
    @DisplayName("equals() should return true when compared with bet with the same id.")
    public void testEqualsWithSameId() {
        //Given
        underTest.setId(800);
        BetEntity other = new BetEntity();
        other.setId(800);
        //When
        //Then
        assertTrue(underTest.equals(other));
    }

}
