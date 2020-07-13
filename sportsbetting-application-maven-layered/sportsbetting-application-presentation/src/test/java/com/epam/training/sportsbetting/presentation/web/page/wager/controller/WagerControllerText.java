package com.epam.training.sportsbetting.presentation.web.page.wager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.presentation.web.datasource.EventAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.OutcomeDescription;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerForm;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerPageText;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

public class WagerControllerText {

    private WagerController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new WagerController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("wagerView() should return the name of the wager page's view as a string when called.")
    public void testWagerView() {
        //Given
        underTest.setWagerView("wagerView");
        
        String expected = underTest.wagerView();
        
        //When
        String actual = underTest.wagerView();
        
        //Then
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("provideWagerPageText() should return a WagerPageText when called.")
    public void testProvideWagerPageText() {
        //Given
        PageTextProvider provider = mock(PageTextProvider.class);
        WagerPageText text = mock(WagerPageText.class);
        when(provider.createWagerPageText(10)).thenReturn(text);
        underTest.setProvider(provider);
        
        WagerPageText expected = text;
        
        //When
        WagerPageText actual = underTest.provideWagerPageText(10);
        
        //Then
        assertEquals(expected, actual);
        verify(provider).createWagerPageText(10);
        
    }
    
    @Test
    @DisplayName("provideWagerForm() should return a WagerForm when called")
    public void testProvideWagerForm() {
        //Given
        EventAccessInterface eventAccess = mock(EventAccessInterface.class);
        List<OutcomeDescription> list = new ArrayList<OutcomeDescription>();
        when(eventAccess.getOutcomeDescs(10)).thenReturn(list);
        underTest.setEventAccess(eventAccess);
        
        WagerForm expected = new WagerForm();
        expected.setOutcomes(list);
        
        //When
        WagerForm actual = underTest.provideWagerForm(10);
        
        //Then
        assertEquals(expected.getOutcomes(), actual.getOutcomes());
        verify(eventAccess).getOutcomeDescs(10);
    }
    
    
}
