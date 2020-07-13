package com.epam.training.sportsbetting.presentation.web.page.event.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventPageText;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

public class EventListControllerTest {

    private EventListController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new EventListController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("listEvents() should returns the name of the view when called")
    public void testListEvents() {
        //Given
        underTest.setEventViewName("events");

        String expected = "events";

        //When
        String actual = underTest.listEvents();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("provideEventList() should return a list of eventDescription when called")
    public void testProvideEventList() {
        //Given
        EventAccessInterface access = mock(EventAccessInterface.class);
        List<EventDescription> list = new ArrayList<EventDescription>();
        when(access.getBetableEvents(any())).thenReturn(list);
        underTest.setAccess(access);
        
        List<EventDescription> expected = new ArrayList<EventDescription>();
        
        //When
        List<EventDescription> actual = underTest.provideEventList();
        
        //Then
        assertEquals(expected, actual);
        verify(access).getBetableEvents(any());
    }
    
    @Test
    @DisplayName("provideEventPageText() should return a EventPageText when called")
    public void testProvideEventPageText() {
        //Given
        EventPageText text = mock(EventPageText.class);
        PageTextProvider provider = mock(PageTextProvider.class);
        when(provider.createEventPageText()).thenReturn(text);
        underTest.setProvider(provider);
        
        EventPageText expected = text;
        
        //When
        EventPageText actual = underTest.provideEventPageText();
        
        //Then
        assertEquals(expected, actual);
        verify(provider).createEventPageText();
    }

}
