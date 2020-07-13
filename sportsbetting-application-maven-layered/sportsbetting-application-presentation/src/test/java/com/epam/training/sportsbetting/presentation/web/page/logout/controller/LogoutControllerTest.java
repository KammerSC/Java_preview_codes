package com.epam.training.sportsbetting.presentation.web.page.logout.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogoutControllerTest {

    private LogoutController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new LogoutController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("logout() should return the redirection path when called")
    public void testLogout() {
        //Given
        underTest.setRedirectPath("redirect:/login");
        String expected = "redirect:/login";

        //When
        String actual = underTest.logout();

        //Then
        assertEquals(expected, actual);
    }

}
