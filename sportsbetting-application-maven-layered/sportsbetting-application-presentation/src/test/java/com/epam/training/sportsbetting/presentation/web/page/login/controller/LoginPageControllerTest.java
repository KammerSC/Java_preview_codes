package com.epam.training.sportsbetting.presentation.web.page.login.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.presentation.web.page.login.controller.LoginPageController;
import com.epam.training.sportsbetting.presentation.web.page.login.modell.LoginPageText;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

public class LoginPageControllerTest {

    private LoginPageController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new LoginPageController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("loginPage() should return the name of the login page as a string when called")
    public void testLoginPage() {
        //Given
        underTest.setLoginView("login");
        String expected = "login";

        //When
        String actual = underTest.loginPage();

        //Then
        assertEquals(expected, actual);
    }
    
    @Test
    @DisplayName("providePageText() should return a new LoginPageText when called.")
    public void testProvidePageText() {
        //Given
        PageTextProvider provider = mock(PageTextProvider.class);
        LoginPageText expected  = new LoginPageText(); 
        when(provider.createLoginText()).thenReturn(expected);
        underTest.setTextProvider(provider);
        
        //When
        LoginPageText actual = underTest.providePageText();
        
        //Then
        assertEquals(expected, actual);
        verify(provider).createLoginText();
    }

}
