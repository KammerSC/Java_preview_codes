package com.epam.training.sportsbetting.presentation.web.page.event.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventDescription;

public class EventToDescriptionConverterTest {

    private EventToDescriptionConverter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new EventToDescriptionConverter();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("convert() should return a null when called with a null.")
    public void testConvertWithNull() {
        //Given
        //When
        EventDescription actual = underTest.convert(null);
        //Then
        assertNull(actual);
    }
    
    @Test
    @DisplayName("convert() should return an event description when called with an event.")
    public void testConvertWithEvent() {
        //Given
        underTest.setBetBasePath("betBasePath");
        SportEvent source = mock(SportEvent.class);
        LocalDateTime time = LocalDateTime.of(2020, 03, 05, 10, 10);
        when(source.getStartDate()).thenReturn(time);
        when(source.getEndDate()).thenReturn(time);
        when(source.getTitle()).thenReturn("title");
        when(source.getId()).thenReturn(15L);
        
        EventDescription expected = new EventDescription();
        expected.setDate("2020-03-05T10:10-2020-03-05T10:10");
        expected.setTitle("title");
        expected.setType("asd");
        expected.setUrl("betBasePath15");
        
        //When
        EventDescription actual = underTest.convert(source);
        
        //Then
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getUrl(), actual.getUrl());
    }

}
