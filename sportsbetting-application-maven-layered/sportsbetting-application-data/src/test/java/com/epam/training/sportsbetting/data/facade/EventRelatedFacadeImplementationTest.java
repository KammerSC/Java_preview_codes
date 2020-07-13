package com.epam.training.sportsbetting.data.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.training.sportsbetting.data.crud.BetRepository;
import com.epam.training.sportsbetting.data.crud.OutcomeOddRepository;
import com.epam.training.sportsbetting.data.crud.OutcomeRepository;
import com.epam.training.sportsbetting.data.crud.SportEventEntityRepository;
import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;

public class EventRelatedFacadeImplementationTest {

    private EventRelatedFacadeImplementation underTest;

    @BeforeEach
    public void setUp() {
        underTest = new EventRelatedFacadeImplementation();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("isValidId should return true when called with 6.")
    public void testIsValidIdWithParamater6() {
        //Given
        //When
        //Then
        assertTrue(underTest.isValidId(6));
    }

    @Test
    @DisplayName("isValidId should throw IllegalArgumentException when called with -2.")
    public void testIsValidIdWithParamaterMinus2() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.isValidId(-2);
        });
    }

    @Test
    @DisplayName("isNotNull should throw NoSuchElementException if the given Object is null")
    public void test() {
        //Given
        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.isNotNull(null);
        });
    }

    @Test
    @DisplayName("findSportEventById should return a SportEventEntity with the id of 5.")
    public void testFindSportEventByIdWithIdOfFive() {
        //Given
        SportEventEntity event = new SportEventEntity();
        event.setId(5);
        SportEventEntityRepository repo = Mockito.mock(SportEventEntityRepository.class);
        when(repo.findById(5)).thenReturn(event);
        underTest.setEventRepo(repo);
        SportEventEntity expected = new SportEventEntity();
        expected.setId(5);

        //When
        SportEventEntity actual = underTest.findSportEventById(5);

        //Then
        verify(repo).findById(5);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("findSportEventById should throw NoSuchElementException when called with a non-existing id")
    public void testFindSportEventByIdWithNonExistingId() {
        //Given
        SportEventEntityRepository repo = Mockito.mock(SportEventEntityRepository.class);
        when(repo.findById(35)).thenReturn(null);
        underTest.setEventRepo(repo);

        //When

        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findSportEventById(35);
        });
        verify(repo).findById(35);
    }

    @Test
    @DisplayName("findSportEventById should throw IllegalArgumentException when called with an invalid id")
    public void testFindSportEventByIdWithInvalidId() {
        //Given
        SportEventEntityRepository repo = Mockito.mock(SportEventEntityRepository.class);
        underTest.setEventRepo(repo);

        //When

        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findSportEventById(-9);
        });
    }

    @Test
    @DisplayName("findAllSportEventThatEndsAfter should return an empty list when there is no event to return")
    public void testFindAllSportEventThatEndsAfterWithNoEventToReturn() {
        //Given
        LocalDateTime time = LocalDateTime.now();
        SportEventEntityRepository repo = Mockito.mock(SportEventEntityRepository.class);
        when(repo.findAllWithEndAfter(time)).thenReturn(new ArrayList<SportEventEntity>());
        underTest.setEventRepo(repo);
        List<SportEventEntity> expected = new ArrayList<SportEventEntity>();

        //When
        List<SportEventEntity> actual = underTest.findAllSportEventThatEndsAfter(time);

        //Then
        assertEquals(expected, actual);
        verify(repo).findAllWithEndAfter(time);
    }

    @Test
    @DisplayName("findAllSportEventThatEndsAfter should return a list when there is two event to return")
    public void testFindAllSportEventThatEndsAfterWithTwoEventToReturn() {
        //Given
        LocalDateTime time = LocalDateTime.now();
        SportEventEntityRepository repo = Mockito.mock(SportEventEntityRepository.class);
        List<SportEventEntity> list = new ArrayList<SportEventEntity>();
        SportEventEntity eventOne = new SportEventEntity();
        eventOne.setId(1);
        SportEventEntity eventTwo = new SportEventEntity();
        eventTwo.setId(2);
        list.add(eventOne);
        list.add(eventTwo);
        when(repo.findAllWithEndAfter(time)).thenReturn(list);
        underTest.setEventRepo(repo);
        List<SportEventEntity> expected = new ArrayList<SportEventEntity>();
        expected.add(eventOne);
        expected.add(eventTwo);

        //When
        List<SportEventEntity> actual = underTest.findAllSportEventThatEndsAfter(time);

        //Then
        assertEquals(expected, actual);
        verify(repo).findAllWithEndAfter(time);
    }

    @Test
    @DisplayName("findAllSportEventThatEndsAfter should throw IllegalArgumentException when the given time is null")
    public void testFindAllSportEventThatEndsAfterWithNullParameter() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findAllSportEventThatEndsAfter(null);
        });
    }

    @Test
    @DisplayName("findBetById should return a bet when the given id exist.")
    public void testFindBetByIdWithParameterEight() {
        //Given
        BetRepository repo = Mockito.mock(BetRepository.class);
        BetEntity bet = new BetEntity();
        bet.setId(8);
        when(repo.findById(8)).thenReturn(bet);
        underTest.setBetRepo(repo);
        BetEntity expected = new BetEntity();
        expected.setId(8);

        //When
        BetEntity actual = underTest.findBetById(8);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(8);
    }

    @Test
    @DisplayName("findBetById should throw NoSuchElementException when the given id does not exist.")
    public void testFindBetByIdWithParameterNinetyNine() {
        //Given
        BetRepository repo = Mockito.mock(BetRepository.class);
        when(repo.findById(99)).thenReturn(null);
        underTest.setBetRepo(repo);

        //When

        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findBetById(99);
        });
        verify(repo).findById(99);
    }

    @Test
    @DisplayName("findBetById should throw IllegalArgumentException when called with negative id.")
    public void testFindBetByIdWithParameterMinusTwelve() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findBetById(-12);
        });
    }

    @Test
    @DisplayName("findBetsByEventId should return a list containing bets belong to an event with the given id.")
    public void testFindBetsByEventIdWithParameter5() {
        //Given
        BetRepository repo = Mockito.mock(BetRepository.class);
        List<BetEntity> bets = new ArrayList<BetEntity>();
        BetEntity betOne = new BetEntity();
        betOne.setId(1);
        BetEntity betTwo = new BetEntity();
        betTwo.setId(2);
        bets.add(betOne);
        bets.add(betTwo);
        when(repo.findByEventId(5)).thenReturn(bets);
        underTest.setBetRepo(repo);

        List<BetEntity> expected = new ArrayList<BetEntity>();
        BetEntity expectedOne = new BetEntity();
        expectedOne.setId(1);
        BetEntity expectedTwo = new BetEntity();
        expectedTwo.setId(2);
        expected.add(betOne);
        expected.add(betTwo);

        //When
        List<BetEntity> actual = underTest.findBetsByEventId(5);

        //Then
        assertEquals(expected, actual);
        verify(repo).findByEventId(5);
    }

    @Test
    @DisplayName("findBetsByEventId should return an empty list if there is no event with the given id.")
    public void testFindBetsByEventIdWithParameterEleven() {
        //Given
        BetRepository repo = Mockito.mock(BetRepository.class);
        when(repo.findByEventId(11)).thenReturn(new ArrayList<BetEntity>());
        underTest.setBetRepo(repo);
        List<BetEntity> expected = new ArrayList<BetEntity>();

        //When
        List<BetEntity> actual = underTest.findBetsByEventId(11);

        //Then
        assertEquals(expected, actual);
        verify(repo).findByEventId(11);
    }

    @Test
    @DisplayName("findBetsByEventId should throw IllegalArgumentException when called with invalid id.")
    public void testFindBetsByEventIdWithParameterMinusTen() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findBetById(-10);
        });
    }

    @Test
    @DisplayName("findOutcomeById should return an Outcome when called with an existing id")
    public void testFindOutcomeByIdWithParameterFive() {
        //Given
        OutcomeRepository repo = Mockito.mock(OutcomeRepository.class);
        OutcomeEntity outcome = new OutcomeEntity();
        outcome.setId(5);
        when(repo.findById(5)).thenReturn(outcome);
        underTest.setOutcomeRepo(repo);
        OutcomeEntity expected = new OutcomeEntity();
        expected.setId(5);

        //When
        OutcomeEntity actual = underTest.findOutcomeById(5);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(5);
    }

    @Test
    @DisplayName("findOutcomeById should throw NoSuchElementException when called with non-existing id.")
    public void testFindOutcomeByIdWithParameterFortyTwo() {
        //Given
        OutcomeRepository repo = Mockito.mock(OutcomeRepository.class);
        when(repo.findById(42)).thenReturn(null);
        underTest.setOutcomeRepo(repo);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findOutcomeById(42);
        });
    }

    @Test
    @DisplayName("findOutcomeById should throw IllegalArgumentException when called with zero")
    public void testFindOutcomeByIdWithParameterZero() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findOutcomeById(0);
        });
    }

    @Test
    @DisplayName("findOutcomesByBetId should return a list containing outcomes belong to a bet with the given id.")
    public void testFindOutcomesByBetIdwithParameterThree() {
        //Given
        OutcomeRepository repo = Mockito.mock(OutcomeRepository.class);
        List<OutcomeEntity> list = new ArrayList<OutcomeEntity>();
        OutcomeEntity outcomeOne = new OutcomeEntity();
        outcomeOne.setId(5);
        OutcomeEntity outcomeTwo = new OutcomeEntity();
        outcomeTwo.setId(6);
        list.add(outcomeOne);
        list.add(outcomeTwo);
        when(repo.findByBetId(3)).thenReturn(list);
        underTest.setOutcomeRepo(repo);
        List<OutcomeEntity> expected = new ArrayList<OutcomeEntity>();
        OutcomeEntity expectedOne = new OutcomeEntity();
        expectedOne.setId(5);
        OutcomeEntity expectedTwo = new OutcomeEntity();
        expectedTwo.setId(6);
        expected.add(expectedOne);
        expected.add(expectedTwo);

        //When
        List<OutcomeEntity> actual = underTest.findOutcomesByBetId(3);

        //Then
        assertEquals(expected, actual);
        verify(repo).findByBetId(3);
    }

    @Test
    @DisplayName("findOutcomesByBetId should return an empty list when there is no outcome belong to the given bet")
    public void findOutcomesByBetIdWithParameterOne() {
        //Given
        OutcomeRepository repo = Mockito.mock(OutcomeRepository.class);
        List<OutcomeEntity> list = new ArrayList<OutcomeEntity>();
        when(repo.findByBetId(1)).thenReturn(list);
        underTest.setOutcomeRepo(repo);
        List<OutcomeEntity> expected = new ArrayList<OutcomeEntity>();

        //When
        List<OutcomeEntity> actual = underTest.findOutcomesByBetId(1);

        //Then
        assertEquals(expected, actual);
        verify(repo).findByBetId(1);
    }

    @Test
    @DisplayName("findOutcomesByBetId should throw IllegalArgumentException when called with invalid id.")
    public void findOutcomesByBetIdWithParameterMinusTwentyTwo() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findOutcomeById(-22);
        });
    }

    @Test
    @DisplayName("findOutcomeOddById should return an outcomeOdd that belongs the given id.")
    public void testFindOutcomeOddByIdWithParameterSeventyFour() {
        //Given
        OutcomeOddRepository repo = Mockito.mock(OutcomeOddRepository.class);
        OutcomeOddEntity odd = new OutcomeOddEntity();
        odd.setId(74);
        when(repo.findById(74)).thenReturn(odd);
        underTest.setOddRepo(repo);
        OutcomeOddEntity expected = new OutcomeOddEntity();
        expected.setId(74);

        //When
        OutcomeOddEntity actual = underTest.findOutcomeOddById(74);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(74);
    }

    @Test
    @DisplayName("findOutcomeOddById should throw NoSuchElementException when there is no odd with the given id.")
    public void testFindOutcomeOddByIdWithParameterSixtySix() {
        //Given
        OutcomeOddRepository repo = Mockito.mock(OutcomeOddRepository.class);
        when(repo.findById(66)).thenReturn(null);
        underTest.setOddRepo(repo);
        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findOutcomeOddById(66);
        });
    }

    @Test
    @DisplayName("findOutcomeOddById should throw IllegalArgumentException when called with invalid id.")
    public void testFindOutcomeOddByIdWithParameterMinusSixteen() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findOutcomeOddById(-16);
        });
    }

    @Test
    @DisplayName("findValidOddOfOutcome should return the valid of an outcome at the current time.")
    public void testFindValidOddOfOutcomeWithParameterTen() {
        //Given
        OutcomeOddRepository repo = Mockito.mock(OutcomeOddRepository.class);
        LocalDateTime time = LocalDateTime.now();
        OutcomeOddEntity odd = new OutcomeOddEntity();
        odd.setId(33);
        when(repo.findValidOddOfOutcome(10, time)).thenReturn(odd);
        underTest.setOddRepo(repo);
        OutcomeOddEntity expected = new OutcomeOddEntity();
        expected.setId(33);

        //When
        OutcomeOddEntity actual = underTest.findValidOddOfOutcome(10, time);

        //Then
        assertEquals(expected, actual);
        verify(repo).findValidOddOfOutcome(10, time);
    }

    @Test
    @DisplayName("findValidOddOfOutcome should throw NoSuchElementException when there is no valid odd at the given time.")
    public void testFindValidOddOfOutcomeWithParameterNine() {
        //Given
        OutcomeOddRepository repo = Mockito.mock(OutcomeOddRepository.class);
        LocalDateTime time = LocalDateTime.now();
        when(repo.findValidOddOfOutcome(9, time)).thenReturn(null);
        underTest.setOddRepo(repo);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findValidOddOfOutcome(9, time);
        });
        verify(repo).findValidOddOfOutcome(9, time);
    }

    @Test
    @DisplayName("findValidOddOfOutcome should throw NoSuchElementException when there is outcome with the given id.")
    public void testFindValidOddOfOutcomeWithParameterEight() {
        //Given
        OutcomeOddRepository repo = Mockito.mock(OutcomeOddRepository.class);
        LocalDateTime time = LocalDateTime.now();
        when(repo.findValidOddOfOutcome(8, time)).thenReturn(null);
        underTest.setOddRepo(repo);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findValidOddOfOutcome(8, time);
        });
        verify(repo).findValidOddOfOutcome(8, time);
    }

    @Test
    @DisplayName("findValidOddOfOutcome should throw IllegalArgumentException when the given id is invalid")
    public void testFindValidOddOfOutcomeWithParameterZero() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> { underTest.findValidOddOfOutcome(0, LocalDateTime.now());});
    }

}
