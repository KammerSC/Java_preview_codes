package com.epam.training.sportsbetting.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SportEventTest {
    
    private SportEvent underTest;
    
    @BeforeEach
    public void setUp() {
        underTest = new SportEvent();
        underTest.setId(1);
    }
    
    @AfterEach
    public void tearDown() {
        underTest = null;
    }
    
    @Test
    @DisplayName("setReferenceswithBet() should add the the given bet to event's bet list and set the bet's event field to the event when called with a bet.")
    public void testSetReferenceswithBetWithBet() {
        //Given
        Bet bet = Mockito.mock(Bet.class);
        
        //When
        underTest.setReferencesWithBet(bet);
        
        //Then
        assertTrue(underTest.getBets().contains(bet));
        verify(bet).setEvent(underTest);
    }
    
    @Test
    @DisplayName("setReferenceswithBet() should throw IllegalArgumentException  when called with null.")
    public void testSetReferenceswithBetWithNull() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, ()-> {underTest.setReferencesWithBet(null);}  );
    }

}
