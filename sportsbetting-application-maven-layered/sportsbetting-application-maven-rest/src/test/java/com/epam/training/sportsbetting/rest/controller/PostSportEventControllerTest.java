package com.epam.training.sportsbetting.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.data.crud.SportEventEntityRepository;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.rest.util.ReferenceSetter;

public class PostSportEventControllerTest {

    private PostSportEventController underTest;
    private SportEventEntityRepository repo;
    private ReferenceSetter setter;

    @BeforeEach
    public void setUp() {
        underTest = new PostSportEventController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("postEvent() should save the given event and return with a string when the event not null")
    public void testPostEventWithEvent() {
        //Given
        repo = mock(SportEventEntityRepository.class);
        setter = mock(ReferenceSetter.class);
        SportEventEntity event = mock(SportEventEntity.class);
        underTest.setRepo(repo);
        underTest.setSetter(setter);

        String expected = "Event Saved";
        
        //When
        String actual = underTest.postEvent(event);
        
        //Then
        assertEquals(expected, actual);
        verify(setter).setReferences(event);
        verify(repo).save(event);
    }
    @Test
    @DisplayName("postEvent() should throw NullPointerException when the event is null")
    public void testPostEventWithNull() {
        //Given
        setter = mock(ReferenceSetter.class);
        underTest.setSetter(setter);
        doThrow(new NullPointerException()).when(setter).setReferences(null);
        
        //When
        //Then
        assertThrows(NullPointerException.class, () -> underTest.postEvent(null));
    }
    
    @Test
    @DisplayName("nullPointerHandler() should return an error message when called")
    public void testNullPointerHandler() {
        //Given
        String expected = "Error";
        
        //When
        String actual = underTest.nullPointerHandler();
        
        //Then
        assertEquals(expected, actual);
    }

}
