package com.epam.training.sportsbetting.data.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/** Test class of SportEvent class.
 * */
public class SportEventEntityTest {

    private SportEventEntity underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SportEventEntity();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("Method createRelationWithBet should add bet to event's BetEntity list and set the bet's reference to this event.")
    public void testCreateRelationWithBetWhenOneBetGiven() {
        //Given
        BetEntity bet = Mockito.mock(BetEntity.class);

        //When
        underTest.createRelationWithBet(bet);

        //Then
        Mockito.verify(bet).setEvent(underTest);
        assertTrue(underTest.getBets().contains(bet));
    }
    @Test
    @DisplayName("Method createRelationWithBet should add bets to event's BetEntity list and set the all bet's reference to this event.")
    public void testCreateRelationWithBetWhenMultipileBetGiven() {
        //Given
        BetEntity betA = Mockito.mock(BetEntity.class);
        BetEntity betB = Mockito.mock(BetEntity.class);
        BetEntity betC = Mockito.mock(BetEntity.class);
        BetEntity betD = Mockito.mock(BetEntity.class);

        //When
        underTest.createRelationWithBet(betA);
        underTest.createRelationWithBet(betB);
        underTest.createRelationWithBet(betC);
        underTest.createRelationWithBet(betD);

        //Then
        Mockito.verify(betA).setEvent(underTest);
        Mockito.verify(betB).setEvent(underTest);
        Mockito.verify(betC).setEvent(underTest);
        Mockito.verify(betD).setEvent(underTest);
        assertTrue(underTest.getBets().contains(betA));
        assertTrue(underTest.getBets().contains(betB));
        assertTrue(underTest.getBets().contains(betC));
        assertTrue(underTest.getBets().contains(betD));
    }

}
