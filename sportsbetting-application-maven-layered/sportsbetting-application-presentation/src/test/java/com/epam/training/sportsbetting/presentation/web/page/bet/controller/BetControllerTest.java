package com.epam.training.sportsbetting.presentation.web.page.bet.controller;

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
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetDescription;
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetPageText;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

public class BetControllerTest {
    private BetController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BetController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("listBets() should return the name of the view as a String when called.")
    public void testListBets() {
        //Given
        underTest.setBetPageView("betPageView");

        String expected = "betPageView";

        //When
        String actual = underTest.listBets();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("provideBetPageText() should return a BetPageText when called")
    public void testProvideBetPageText() {
        //Given
        BetPageText text = mock(BetPageText.class);
        PageTextProvider provider = mock(PageTextProvider.class);
        when(provider.createBetPageText(20)).thenReturn(text);
        underTest.setProvider(provider);
        
        BetPageText expected = text;

        //When
        BetPageText actual = underTest.provideBetPageText(20);

        //Then
        assertEquals(expected, actual);
        verify(provider).createBetPageText(20);
    }

    @Test
    @DisplayName("provideEventDescription() should return an EventDescription when called")
    public void testprovideEventDescription() {
        //Given
        EventAccessInterface access = mock(EventAccessInterface.class);
        EventDescription description = mock(EventDescription.class);
        when(access.getEventDescriptionById(10)).thenReturn(description);
        underTest.setAccess(access);
        
        EventDescription expected = description;
        
        //When
        EventDescription actual = underTest.provideEventDescription(10);
        
        //Then
        assertEquals(expected, actual);
        verify(access).getEventDescriptionById(10);
    }

    @Test
    @DisplayName("provideBetDescriptions() should return a list of BetDescription when called")
    public void testProvideBetDescriptions() {
        //Given
        EventAccessInterface access = mock(EventAccessInterface.class);
        List<BetDescription> list = new ArrayList<BetDescription>();
        when(access.getBetsOfEvent(10)).thenReturn(list);
        underTest.setAccess(access);
        
        List<BetDescription> expected = new ArrayList<BetDescription>();
        
        //When
        List<BetDescription> actual = underTest.provideBetDescriptions(10);
        
        //Then
        assertEquals(expected, actual);
        verify(access).getBetsOfEvent(10);
        
    }

}
