package com.epam.training.sportsbetting.presentation.web.page.home.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.page.home.model.HomePageText;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;
import com.epam.training.sportsbetting.presentation.web.page.home.model.WagerDescription;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

public class HomeControllerTest {

    private HomeController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new HomeController();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("home() should return the homeview's name when called")
    public void testHome() {
        //Given
        underTest.setHomeView("homeView");
        String expected = "homeView";

        //When
        String actual = underTest.home();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("createPlayerDetails() should return a playerDetails when called with the give principal.")
    public void testCreatePlayerDetailsWithPrinciple() {
        //Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("test@mail");
        PlayerAccessInterface access = mock(PlayerAccessInterface.class);
        PlayerDetails details = new PlayerDetails();
        details.setId(100);
        when(access.getPlayerDetailsByEmail("test@mail")).thenReturn(details);
        underTest.setAccess(access);
        PlayerDetails expected = new PlayerDetails();
        expected.setId(100);

        //When
        PlayerDetails actual = underTest.createPlayerDetails(principal);

        //Then
        assertEquals(expected.getId(), actual.getId());
        verify(principal).getName();
        verify(access).getPlayerDetailsByEmail("test@mail");
    }
    
    @Test
    @DisplayName("createWagerDescriptions() should return a list of wagerDescription when with a principal.")
    public void testCreateWagerDescriptionsWithPrincipal() {
        //Given
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("test@mail");
        PlayerAccessInterface access = mock(PlayerAccessInterface.class);
        when(access.getWagerDescriptionsOfPlayer("test@mail")).thenReturn(new ArrayList<WagerDescription>());
        underTest.setAccess(access);
        
        List<WagerDescription> expected = new ArrayList<WagerDescription>();
        
        //When
        List<WagerDescription> actual = underTest.createWagerDescriptions(principal);
        
        //Then
        assertEquals(expected, actual);
        verify(principal).getName();
        verify(access).getWagerDescriptionsOfPlayer("test@mail");
    }
    
    @Test
    @DisplayName("createHomePageModell() should return a HomePageText when called ")
    public void testCreateHomePageModell() {
        //Given
        PageTextProvider provider = mock(PageTextProvider.class);
        HomePageText text = mock(HomePageText.class);
        when(provider.createHomepageText()).thenReturn(text);
        underTest.setTextProvider(provider);
        
        HomePageText expected = text;
        
        //When
        HomePageText actual = underTest.createHomePageModell();
        
        //Then
        assertEquals(expected, actual);
        verify(provider).createHomepageText();
    }

}
