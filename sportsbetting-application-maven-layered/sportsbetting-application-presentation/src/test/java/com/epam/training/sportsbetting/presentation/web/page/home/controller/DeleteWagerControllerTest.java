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

public class DeleteWagerControllerTest {

    private DeleteWagerController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new DeleteWagerController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("deleteWager() should delete the a wager with a given id and return a redirection path when called")
    public void testDeleteWager() {
        //Given
        Principal principal = mock(Principal.class);
        PlayerAccessInterface accessInterface = mock(PlayerAccessInterface.class);
        underTest.setAccess(accessInterface);
        when(principal.getName()).thenReturn("test@mail");
        underTest.setRedirectPath("redirectionPath");
        String expected = "redirectionPath";

        //When
        String actual = underTest.deleteWager(10, principal);
        
        //Then
        assertEquals(expected, actual);
        verify(principal).getName();
    }
    
    @Test
    @DisplayName("exceptionHandler() should return a redirection path when it handles an exception")
    public void testExceptionHandler() {
        //Given
        RedirectAttributes attributes = mock(RedirectAttributes.class);
        IllegalArgumentException exception = mock(IllegalArgumentException.class);
        when(exception.getMessage()).thenReturn("error msg");
        underTest.setRedirectPath("redirectPath");
        
        String expected = "redirectPath";
        
        //When
        String actual = underTest.exceptionHandler(attributes, exception);
        
        //Then
        assertEquals(expected, actual);
        verify(exception).getMessage();
        verify(attributes).addFlashAttribute("error", "error msg");
    }

}
