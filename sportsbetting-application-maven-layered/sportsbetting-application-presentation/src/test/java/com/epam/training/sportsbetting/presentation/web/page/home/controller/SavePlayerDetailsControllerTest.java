package com.epam.training.sportsbetting.presentation.web.page.home.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;

public class SavePlayerDetailsControllerTest {

    private SavePlayerDetailsController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SavePlayerDetailsController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("savePlayer() should return a redirection path after saving when called")
    public void testSavePlayer() {
        //Given
        underTest.setRedirectPath("redirectPath");
        PlayerAccessInterface access = mock(PlayerAccessInterface.class);
        underTest.setPlayerAccess(access);
        PlayerDetails details = mock(PlayerDetails.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("test@email");

        String expected = "redirectPath";

        //When
        String actual = underTest.savePlayer(details, principal);

        //Then

        assertEquals(expected, actual);
        verify(principal).getName();
        verify(access).savePlayerChanges("test@email", details);
    }
    
    @Test
    @DisplayName("exceptionHandler() should return the redirection path and set a message in redirect attributes when called (exception happens).")
    public void testExceptionHandler() {
        //Given
        RedirectAttributes attributes = mock(RedirectAttributes.class);
        IllegalArgumentException exception = mock(IllegalArgumentException.class);
        when(exception.getMessage()).thenReturn("error msg");
        underTest.setRedirectPath("redirectPath");
        String expected = "redirectPath";
        
        //When
        String actual= underTest.exceptionHandler(attributes, exception);
        
        //Then
        assertEquals(expected, actual);
        verify(exception).getMessage();
        verify(attributes).addFlashAttribute("error", "error msg");
    }

}
