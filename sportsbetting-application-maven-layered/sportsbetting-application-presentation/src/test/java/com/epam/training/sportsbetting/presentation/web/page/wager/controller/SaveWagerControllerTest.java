package com.epam.training.sportsbetting.presentation.web.page.wager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerForm;

public class SaveWagerControllerTest {

    private SaveWagerController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new SaveWagerController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("saveWager() should save the new wager and return the redirection path as string when called")
    public void testSaveWager() {
        //Given
        WagerForm form = mock(WagerForm.class);
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("test@mail");
        PlayerAccessInterface access = mock(PlayerAccessInterface.class);
        underTest.setAccess(access);
        underTest.setRedirectHome("redirectHome");
        
        String expected = "redirectHome";
        
        //When
        String actual = underTest.saveWager(form, principal);
        
        //Then
        assertEquals(expected, actual);
        verify(principal).getName();
        verify(access).saveWager(form, "test@mail");
    }
}
