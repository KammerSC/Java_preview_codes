package com.epam.training.sportsbetting.presentation.web.page.wager.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.OutcomeDescription;

public class OutcomeToDescriptionConverterTest {

    private OutcomeToDescriptionConverter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OutcomeToDescriptionConverter();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }
    
    @Test
    @DisplayName("convert() should return an outcome Description when called")
    public void testConvertWithValidParameters() {
        //Given
        Outcome outcome = mock(Outcome.class);
        when(outcome.getDescription()).thenReturn("desc");
        when(outcome.getId()).thenReturn(15L);
        OutcomeOdd odd = mock(OutcomeOdd.class);
        when(odd.getValue()).thenReturn(BigDecimal.TEN);
        
        OutcomeDescription expected = new OutcomeDescription();
        expected.setDescription("desc - odd: 10");
        expected.setId(15L);
        
        //When
        OutcomeDescription actual = underTest.convert(outcome, odd);
        
        //Then
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getId(), actual.getId());
        verify(outcome).getDescription();
        verify(outcome).getId();
        verify(odd).getValue();
    }
    

    @Test
    @DisplayName("convert() should return null when called with null as outcome")
    public void testConvertWithNullOutcome() {
        //Given
        //When
        OutcomeDescription actual = underTest.convert(null, new OutcomeOdd());
        
        //Then
        assertNull(actual);
    }
    
    @Test
    @DisplayName("convert() should return null when called with null as odd")
    public void testConvertWithNullOdd() {
        //Given
        //When
        OutcomeDescription actual = underTest.convert(new Outcome(), null);
        
        //Then
        assertNull(actual);
    }

}
