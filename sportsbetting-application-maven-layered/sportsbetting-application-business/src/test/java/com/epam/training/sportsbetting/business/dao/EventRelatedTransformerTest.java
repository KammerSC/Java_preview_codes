package com.epam.training.sportsbetting.business.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.data.util.BetType;
import com.epam.training.sportsbetting.data.util.SportEventType;
import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Type;

public class EventRelatedTransformerTest {
    
    private EventRelatedTransformer underTest;
    
    @BeforeEach
    public void setUp() {
        underTest = new EventRelatedTransformer();
    }
    
    @AfterEach
    public void tearDown() {
        underTest = null;
    }
    
    @Test
    @DisplayName("oddEntityToOdd() should return a new odd when called with a OddEntity.")
    public void testOddEntityToOddWithAnOdd() {
        //Given
        OutcomeOddEntity entity = new OutcomeOddEntity();
        entity.setId(1);
        entity.setValue(BigDecimal.TEN);
        LocalDateTime date = LocalDateTime.now();
        entity.setValidFrom(date);
        entity.setValidUntil(date);
        OutcomeOdd expected = new OutcomeOdd();
        expected.setId(1);
        expected.setValidFrom(date);
        expected.setValidTo(date);
        expected.setValue(BigDecimal.TEN);
        
        //When
        OutcomeOdd actual = underTest.oddEntityToOdd(entity);
        
        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getValidFrom(), actual.getValidFrom());
        assertEquals(expected.getValidTo(), actual.getValidTo());
        assertEquals(expected.getValue(), actual.getValue());
    }
    
    @Test
    @DisplayName("outcomeEntityToOutcome() should return a new outcome when called with an outcomeEntity")
    public void testOutcomeEntityToOutcomeWithAnOutcomeEntity() {
        //Given
        OutcomeEntity entity = new OutcomeEntity();
        Outcome expected = new Outcome();
        entity.setDescription("test");
        expected.setDescription("test");
        entity.setId(1);
        expected.setId(1);
        entity.setOdds(new ArrayList<OutcomeOddEntity>());
        expected.setOdds(new ArrayList<OutcomeOdd>());
        
        //When
        Outcome actual = underTest.outcomeEntityToOutcome(entity);
        
        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getOdds(), actual.getOdds());
    }
    
    @Test
    @DisplayName("betEntityToBet() should return a new bet when called with a betEntity")
    public void testBetEntityToBetWithABetEntity() {
        //Given
        BetEntity entity = new BetEntity();
        Bet expected = new Bet();
        entity.setId(1);
        expected.setId(1);
        entity.setDescription("test");
        expected.setDescription("test");
        entity.setType(BetType.WINNER);
        expected.setType(Type.WINNER);
        entity.setOutcomes(new ArrayList<OutcomeEntity>());
        expected.setOutcomes(new ArrayList<Outcome>());
        
        //When
        Bet actual = underTest.betEntityToBet(entity);
        
        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getOutcomes(), actual.getOutcomes());
    }
    
    @Test
    @DisplayName("sportEventEntityToSportEvent() should return a new sportEvent when called with an eventEntity")
    public void testSportEventEntityToSportEventwithASportEventEntity() {
        //Given
        SportEventEntity entity = new SportEventEntity();
        SportEvent expected = new FootballSportEvent();
        entity.setId(1);
        expected.setId(1);
        entity.setTitle("test");
        expected.setTitle("test");
        LocalDateTime date = LocalDateTime.now();
        entity.setStart(date);
        expected.setStartDate(date);
        entity.setEnd(date);
        expected.setEndDate(date);
        entity.setType(SportEventType.FOOTTBALL);
        
        //When
        SportEvent actual = underTest.sportEventEntityToSportEvent(entity);
        
        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getStartDate(), actual.getStartDate());
        assertEquals(expected.getEndDate(), actual.getEndDate());
    }
    

}
