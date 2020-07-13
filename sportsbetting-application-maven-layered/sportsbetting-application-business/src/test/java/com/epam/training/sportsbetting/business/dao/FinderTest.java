package com.epam.training.sportsbetting.business.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;

public class FinderTest {

    private Finder underTest;

    @BeforeEach
    public void setUp() {
        underTest = new Finder();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("findBet() should return a bet with the given id when when called ")
    public void testFindBetWithMultipileBets() {
        //Given
        SportEvent event = mock(SportEvent.class);
        List<Bet> bets = new ArrayList<Bet>();
        Bet bet1 = new Bet();
        bet1.setId(1);
        Bet bet2 = new Bet();
        bet2.setId(2);
        Bet bet3 = new Bet();
        bet3.setId(3);
        when(event.getBets()).thenReturn(bets);
        Bet expected = new Bet();
        expected.setId(2);
        bets.add(bet1);
        bets.add(bet2);
        bets.add(bet3);

        //When
        Bet actual = underTest.findBet(event, 2);

        //Then
        assertEquals(expected, actual);
        verify(event).getBets();
    }

    @Test
    @DisplayName("findBet() should throw NullpointerException when called with null")
    public void testFindBetWithNullEvent() {
        //Given
        //When
        //Then
        assertThrows(NullPointerException.class, () -> {
            underTest.findBet(null, 5);
        });
    }

    @Test
    @DisplayName("findBet() should throw NoSuchElementException when called with an event that has no bets")
    public void testFindBetWithEventWithoutBets() {
        SportEvent event = mock(SportEvent.class);
        List<Bet> bets = new ArrayList<Bet>();
        when(event.getBets()).thenReturn(bets);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findBet(event, 3);
        });
        verify(event).getBets();
    }

    @Test
    @DisplayName("findOutcome() should return an outcome when an event that has multipile outcome in it's bets.")
    public void testFindOutcomeWithEventWithOutcomes() {
        //Given
        SportEvent event = mock(SportEvent.class);
        List<Bet> bets = new ArrayList<Bet>();
        Bet bet = mock(Bet.class);
        bets.add(bet);
        when(event.getBets()).thenReturn(bets);
        List<Outcome> outcomes = new ArrayList<Outcome>();
        Outcome outcome1 = new Outcome();
        outcome1.setId(1);
        outcomes.add(outcome1);
        Outcome outcome2 = new Outcome();
        outcome2.setId(2);
        outcomes.add(outcome2);
        Outcome outcome3 = new Outcome();
        outcome3.setId(3);
        outcomes.add(outcome3);
        when(bet.getOutcomes()).thenReturn(outcomes);

        Outcome expected = new Outcome();
        expected.setId(2);

        //When
        Outcome actual = underTest.findOutcome(event, 2);

        //Then
        assertEquals(expected, actual);
        verify(event).getBets();
        verify(bet).getOutcomes();
    }

    @Test
    @DisplayName("findOutcome() should throw NoSuchElementException when there is no outcome to return.")
    public void testFindOutcomeWithNoOutcome() {
        //Given
        SportEvent event = mock(SportEvent.class);
        List<Bet> bets = new ArrayList<Bet>();
        Bet bet = mock(Bet.class);
        bets.add(bet);
        when(event.getBets()).thenReturn(bets);
        List<Outcome> outcomes = new ArrayList<Outcome>();
        when(bet.getOutcomes()).thenReturn(outcomes);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findOutcome(event, 2);
        });
    }

    @Test
    @DisplayName("findOutcome() should throw NullPointerException when called with null as event.")
    public void testFindOutcomeWithNullEvent() {
        //Given
        //When
        //Then
        assertThrows(NullPointerException.class, () -> {
            underTest.findOutcome(null, 2);
        });
    }

    @Test
    @DisplayName("findOutcomeOdd() should return an odd with the given odd when called with event with multipile odds.")
    public void testFindOutcomeOddWithEventWithOdds() {
        //Given
        SportEvent event = mock(SportEvent.class);
        List<Bet> bets = new ArrayList<Bet>();
        Bet bet = mock(Bet.class);
        bets.add(bet);
        when(event.getBets()).thenReturn(bets);
        List<Outcome> outcomes = new ArrayList<Outcome>();
        Outcome outcome = mock(Outcome.class);
        outcomes.add(outcome);
        when(bet.getOutcomes()).thenReturn(outcomes);
        List<OutcomeOdd> odds = new ArrayList<OutcomeOdd>();
        OutcomeOdd odd1 = new OutcomeOdd();
        odd1.setId(1);
        odds.add(odd1);
        OutcomeOdd odd2 = new OutcomeOdd();
        odd2.setId(2);
        odds.add(odd2);
        OutcomeOdd odd3 = new OutcomeOdd();
        odd3.setId(3);
        odds.add(odd3);
        when(outcome.getOdds()).thenReturn(odds);

        OutcomeOdd expected = new OutcomeOdd();
        expected.setId(2);

        //When
        OutcomeOdd actual = underTest.findOutcomeOdd(event, 2);

        //Then
        assertEquals(expected, actual);
        verify(event).getBets();
        verify(bet).getOutcomes();
        verify(outcome).getOdds();
    }

    @Test
    @DisplayName("findOutcomeOdd() should thow NoSuchElementException when callaed with an event that has no odds")
    public void testFindOutcomeOddWithNoOdds() {
        //Given
        SportEvent event = mock(SportEvent.class);
        List<Bet> bets = new ArrayList<Bet>();
        Bet bet = mock(Bet.class);
        bets.add(bet);
        when(event.getBets()).thenReturn(bets);

        List<Outcome> outcomes = new ArrayList<Outcome>();
        Outcome outcome = mock(Outcome.class);
        outcomes.add(outcome);
        when(bet.getOutcomes()).thenReturn(outcomes);

        List<OutcomeOdd> odds = new ArrayList<OutcomeOdd>();
        when(outcome.getOdds()).thenReturn(odds);

        //When

        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findOutcomeOdd(event, 2);
        });
        verify(event).getBets();
        verify(bet).getOutcomes();
        verify(outcome).getOdds();
    }

    @Test
    @DisplayName("findOutcomeOdd() should throw NullPointerException when called with null as event.")
    public void testFindOutcomeOddWith() {
        //Given
        //When
        //Then
        assertThrows(NullPointerException.class, () -> {
            underTest.findOutcomeOdd(null, 2);
        });
    }

}
