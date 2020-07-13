package com.epam.training.sportsbetting.presentation.web.page.bet.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Type;
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetDescription;

public class BetToDescriptionConverterTest {
    private BetToDescriptionConverter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BetToDescriptionConverter();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("convert() should return null when called with null.")
    public void testConvertWithNull() {
        //Given
        //When
        BetDescription actual = underTest.convert(null);
        
        //Then
        assertNull(actual);
    }
    
    @Test
    @DisplayName("convert() should return a BetDescription when a Bet")
    public void testConvertWithBet() {
        //Given
        Bet source = mock(Bet.class);
        when(source.getDescription()).thenReturn("description");
        when(source.getType()).thenReturn(Type.GOALS);
        when(source.getId()).thenReturn(15L);
        underTest.setWagerBasePath("wagerBasePath");
        
        BetDescription expected = new BetDescription();
        expected.setDescription("description");
        expected.setType("GOALS");
        expected.setUrl("wagerBasePath15");
        
        //When
        BetDescription actual = underTest.convert(source);
        
        //Then
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getUrl(), actual.getUrl());
    }
    

}
