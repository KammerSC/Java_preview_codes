package com.epam.training.sportsbetting.presentation.web.page.login.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginProcessingControllerTest {
    private LoginProcessingController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new LoginProcessingController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("loginRedirect() should return the redirection path to the homepage as string when called.")
    public void testLoginRedirect() {
        //Given
        underTest.setRedirectPath("redirect:/home");
        String expected = "redirect:/home";
        
        //When
        String actual = underTest.loginRedirect();
        
        //Then
        assertEquals(expected, actual);
    }

}
