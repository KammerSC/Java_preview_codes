package com.epam.training.sportsbetting.business.dao;

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

import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.data.facade.EventRelatedFacade;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;

public class EventDataSourceImplTest {
    private EventDataSourceImpl underTest;
    private EventRelatedFacade facadeMock;
    private EventRelatedTransformer transMock;

    @BeforeEach
    public void setUp() {
        underTest = new EventDataSourceImpl();
        facadeMock = Mockito.mock(EventRelatedFacade.class);
        transMock = Mockito.mock(EventRelatedTransformer.class);
        underTest.setFacade(facadeMock);
        underTest.setTransformer(transMock);
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
        facadeMock = null;
        transMock = null;
    }

    //@Test
    @DisplayName("eventsEndAfter() should a list of sport events when called with a LocalDateTime")
    public void testEventsEndAfterWithValidTime() {
        //Given
        LocalDateTime time = LocalDateTime.now();
        SportEventEntity entityOne = new SportEventEntity();
        entityOne.setId(1);
        SportEventEntity entityTwo = new SportEventEntity();
        entityTwo.setId(2);
        List<SportEventEntity> entities = new ArrayList<SportEventEntity>();
        entities.add(entityOne);
        entities.add(entityTwo);
        when(facadeMock.findAllSportEventThatEndsAfter(time)).thenReturn(entities);
        SportEvent eventOne = new SportEvent();
        eventOne.setId(1);
        SportEvent eventTwo = new SportEvent();
        eventTwo.setId(2);
        when(transMock.sportEventEntityToSportEvent(entityOne)).thenReturn(eventOne);
        when(transMock.sportEventEntityToSportEvent(entityTwo)).thenReturn(eventTwo);
        List<SportEvent> expected = new ArrayList<SportEvent>();
        expected.add(eventOne);
        expected.add(eventTwo);

        //When
        List<SportEvent> actual = underTest.eventsEndAfter(time);

        //Then
        assertEquals(expected, actual);
        verify(facadeMock).findAllSportEventThatEndsAfter(time);
        verify(transMock).sportEventEntityToSportEvent(entityOne);
        verify(transMock).sportEventEntityToSportEvent(entityTwo);
    }

    //@Test
    @DisplayName("eventsEndAfter() should a list of sport events when called with a LocalDateTime and there is no event to return")
    public void testEventsEndAfterWithValidTimeNoEvent() {
        //Given
        LocalDateTime time = LocalDateTime.now();
        List<SportEventEntity> entities = new ArrayList<SportEventEntity>();
        when(facadeMock.findAllSportEventThatEndsAfter(time)).thenReturn(entities);
        List<SportEvent> expected = new ArrayList<SportEvent>();

        //When
        List<SportEvent> actual = underTest.eventsEndAfter(time);

        //Then
        assertEquals(expected, actual);
        verify(facadeMock).findAllSportEventThatEndsAfter(time);
    }

   // @Test
    @DisplayName("eventsEndAfter() should throw IllegalArgumenException when called with null.")
    public void testEventsEndAfterWithNull() {
        //Given
        when(facadeMock.findAllSportEventThatEndsAfter(null)).thenThrow(IllegalArgumentException.class);
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.eventsEndAfter(null);
        });
    }

  //  @Test
    @DisplayName("getEventById() should return a sportevent when called with an existing id.")
    public void testGetEventByIdWithExistingId() {
        //Given
        SportEventEntity entity = new SportEventEntity();
        entity.setId(1);
        SportEvent event = new SportEvent();
        event.setId(1);
        when(facadeMock.findSportEventById(1)).thenReturn(entity);
        when(transMock.sportEventEntityToSportEvent(entity)).thenReturn(event);
        SportEvent expected = new SportEvent();
        expected.setId(1);

        //When
        SportEvent actual = underTest.getEventById(1);

        //Then
        assertEquals(expected, actual);
        verify(facadeMock).findSportEventById(1);
        verify(transMock).sportEventEntityToSportEvent(entity);
    }

  //  @Test
    @DisplayName("getEventById() should throw NoSuchElementException when called with an non-existing id.")
    public void testGetEventByIdWithNonExistingId() {
        //Given
        when(facadeMock.findSportEventById(1)).thenThrow(NoSuchElementException.class);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.getEventById(1);
        });
        verify(facadeMock).findSportEventById(1);
    }

   // @Test
    @DisplayName("getEventById() should throw IllegalArgumentException when called with an invalid id.")
    public void testGetEventByIdWithInvalidId() {
        //Given
        when(facadeMock.findSportEventById(0)).thenThrow(IllegalArgumentException.class);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getEventById(0);
        });
        verify(facadeMock).findSportEventById(0);
    }

    
    //@Test
    //@DisplayName("getBetById() should return a bet when called with an existing id.")
    public void testGetBetByIdWithExistingId() {
        //Given
        BetEntity entity = new BetEntity();
        entity.setId(1);
        when(facadeMock.findBetById(1)).thenReturn(entity);
        Bet bet = new Bet();
        bet.setId(1);
        when(transMock.betEntityToBet(entity)).thenReturn(bet);
        Bet expected = new Bet();
        expected.setId(1);

        //When
        Bet actual = underTest.getBetById(1);

        //Then
        assertEquals(expected, actual);
        verify(facadeMock).findBetById(1);
        verify(transMock).betEntityToBet(entity);
    }

 //   @Test
    @DisplayName("getBetById() should throw NoSuchElementException when called with a non-existing id.")
    public void testGetBetByIdWithNonExistingId() {
        //Given
        when(facadeMock.findBetById(1)).thenThrow(NoSuchElementException.class);
        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.getBetById(1);
        });
        verify(facadeMock).findBetById(1);
    }

  //  @Test
    @DisplayName("getBetById() should throw IllegalArgumentException when called with a invalid id.")
    public void testGetBetByIdWithInvalidId() {
        //Given
        when(facadeMock.findBetById(0)).thenThrow(IllegalArgumentException.class);
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getBetById(0);
        });
        verify(facadeMock).findBetById(0);
    }

    //@Test
    //@DisplayName("getOutcomeById() should  when called with an existing id")
    public void testGetOutcomeByIdWithExistingId() {
        //Given
        OutcomeEntity entity = new OutcomeEntity();
        entity.setId(1);
        when(facadeMock.findOutcomeById(1)).thenReturn(entity);
        Outcome outcome = new Outcome();
        outcome.setId(1);
        when(transMock.outcomeEntityToOutcome(entity)).thenReturn(outcome);
        Outcome expected = new Outcome();
        expected.setId(1);

        //When
        Outcome actual = underTest.getOutcomeById(1);

        //Then
        assertEquals(expected, actual);
        verify(facadeMock).findOutcomeById(1);
        verify(transMock).outcomeEntityToOutcome(entity);

    }

 //   @Test
    @DisplayName("getOutcomeById() should throw NoSuchElementException when called with a non-existing id.")
    public void testGetOutcomeByIdWithNonExistingId() {
        //Given
        when(facadeMock.findOutcomeById(1)).thenThrow(NoSuchElementException.class);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.getOutcomeById(1);
        });
        verify(facadeMock).findOutcomeById(1);
    }

  //  @Test
    @DisplayName("getOutcomeById() should throw IllegalArgumentException when called with an invalid id")
    public void testGetOutcomeByIdWithInvalidId() {
        //Given
        when(facadeMock.findOutcomeById(0)).thenThrow(IllegalArgumentException.class);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getOutcomeById(0);
        });
        verify(facadeMock).findOutcomeById(0);
    }

 //   @Test
    @DisplayName("getValidOddOfOutcome() should return the valid odd when called with an outcome that has multipile odd.")
    public void testGetValidOddOfOutcomeWithOutcomeWithMultipileOdd() {
        //Given
        LocalDateTime time1 = LocalDateTime.of(2020, 1, 20, 10, 10);
        LocalDateTime time2 = LocalDateTime.of(2020, 2, 25, 10, 10);
        LocalDateTime time3 = LocalDateTime.of(2020, 3, 28, 10, 10);
        LocalDateTime time4 = LocalDateTime.of(2020, 5, 26, 10, 10);
        Outcome outcome = new Outcome();

        OutcomeOdd odd1 = new OutcomeOdd();
        odd1.setId(1);
        odd1.setValidFrom(time1);
        odd1.setValidTo(time2);
        outcome.setReferences(odd1);

        OutcomeOdd odd2 = new OutcomeOdd();
        odd2.setId(2);
        odd2.setValidFrom(time2);
        odd2.setValidTo(time3);
        outcome.setReferences(odd2);

        OutcomeOdd odd3 = new OutcomeOdd();
        odd3.setId(3);
        odd3.setValidFrom(time3);
        odd3.setValidTo(time4);
        outcome.setReferences(odd3);

        LocalDateTime wagerTime = LocalDateTime.of(2020, 3, 1, 10, 10);
        OutcomeOdd expected = new OutcomeOdd();
        expected.setId(2);

        //When
        OutcomeOdd actual = underTest.getValidOddOfOutcome(outcome, wagerTime);

        //Then
        assertEquals(expected, actual);
    }

  //  @Test
    @DisplayName("getValidOddOfOutcome() should return throw NoSuchElementException when there is odd to return.")
    public void testGetValidOddOfOutcomeWithNoOdd() {
        //Given
        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.getValidOddOfOutcome(new Outcome(), LocalDateTime.now());
        });
    }

  //  @Test
    @DisplayName("getValidOddOfOutcome() should return throw IllegalArgumentException when called with null as outcome.")
    public void testGetValidOddOfOutcomeWithNullOutcome() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getValidOddOfOutcome(null, LocalDateTime.now());
        });
    }

   // @Test
    @DisplayName("getValidOddOfOutcome() should return throw IllegalArgumentException when called with null as outcome.")
    public void testGetValidOddOfOutcomeWithNullTime() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getValidOddOfOutcome(new Outcome(), null);
        });
    }
    
   // @Test
    @DisplayName("isTimeBetween() should return true when the given time is between the boundaries.")
    public void testIsTimeBetweenWithTimeBetweenBounderies() {
        //Given
        LocalDateTime lower = LocalDateTime.of(2020, 1, 1, 1, 1);
        LocalDateTime upper = LocalDateTime.of(2020, 3, 3, 3, 3);
        LocalDateTime time = LocalDateTime.of(2020, 2, 2, 2, 2);
        
        //When
        boolean condition = underTest.isTimeBetween(upper, lower, time);
        
        //Then
        assertTrue(condition);
    }
 //   @Test
    @DisplayName("isTimeBetween() should return true when the given time is equals with lower the boundary.")
    public void testIsTimeBetweenWithOnTheLowerBoundary() {
        //Given
        LocalDateTime lower = LocalDateTime.of(2020, 1, 1, 1, 1);
        LocalDateTime upper = LocalDateTime.of(2020, 3, 3, 3, 3);
        LocalDateTime time = LocalDateTime.of(2020, 1, 1, 1, 1);
        
        //When
        boolean condition = underTest.isTimeBetween(upper, lower, time);
        
        //Then
        assertTrue(condition);
    }
    
  //  @Test
    @DisplayName("isTimeBetween() should return true when the given time is equals with upper the boundary.")
    public void testIsTimeBetweenWithOnTheUpperBoundary() {
        //Given
        LocalDateTime lower = LocalDateTime.of(2020, 1, 1, 1, 1);
        LocalDateTime upper = LocalDateTime.of(2020, 3, 3, 3, 3);
        LocalDateTime time = LocalDateTime.of(2020, 3, 3, 3, 3);
        
        //When
        boolean condition = underTest.isTimeBetween(upper, lower, time);
        
        //Then
        assertTrue(condition);
    }
    
  //  @Test
    @DisplayName("isTimeBetween() should return false when the given time is not between the boundaries.")
    public void testIsTimeBetweenWith() {
        //Given
        LocalDateTime lower = LocalDateTime.of(2020, 1, 1, 1, 1);
        LocalDateTime upper = LocalDateTime.of(2020, 3, 3, 3, 3);
        LocalDateTime time = LocalDateTime.of(2020, 3, 3, 3, 3);
        
        //When
        boolean condition = underTest.isTimeBetween(upper, lower, time);
        
        //Then
        assertTrue(condition);
    }
    

}
