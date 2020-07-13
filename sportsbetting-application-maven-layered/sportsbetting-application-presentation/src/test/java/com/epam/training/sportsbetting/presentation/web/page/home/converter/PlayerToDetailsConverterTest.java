package com.epam.training.sportsbetting.presentation.web.page.home.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;

public class PlayerToDetailsConverterTest {
    private PlayerToDetailsConverter underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerToDetailsConverter();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("convert() should return a new PlayerDetailts instance when called with a Player object.")
    public void testConvertWithAPlayer() {
        //Given
        Player player = mock(Player.class);
        when(player.getId()).thenReturn(1L);
        when(player.getAccountNumber()).thenReturn(Integer.valueOf(55555));
        when(player.getBalance()).thenReturn(BigDecimal.valueOf(12000));
        when(player.getBirth()).thenReturn(LocalDateTime.of(1987, 12, 25, 3, 58));
        when(player.getCurrency()).thenReturn(Currency.HUF);
        when(player.getName()).thenReturn("Test Elek");
        when(player.getVersion()).thenReturn(125L);
        
        PlayerDetails expected = new PlayerDetails();
        expected.setId(1);
        expected.setAccountNumber(Integer.valueOf(55555));
        expected.setBalance(BigDecimal.valueOf(12000));
        expected.setCurrency(Currency.HUF);
        expected.setDate(LocalDate.of(1987, 12, 25));
        expected.setName("Test Elek");
        expected.setVersion(125L);
        
        //When
        PlayerDetails actual = underTest.convert(player);
        
        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAccountNumber(), actual.getAccountNumber());
        assertEquals(expected.getBalance(), actual.getBalance());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getDate(), actual.getDate());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getVersion(), actual.getVersion());
        verify(player).getId();
        verify(player).getAccountNumber();
        verify(player).getBalance();
        verify(player).getCurrency();
        verify(player).getName();
        verify(player).getVersion();
    }
    
    @Test
    @DisplayName("convert() should return null when called with null.")
    public void testConvertWithNull() {
        //Given
        PlayerDetails expected = null;
        
        //When
        PlayerDetails actual = underTest.convert(null);
        
        //Then
        assertEquals(expected, actual);
    }

}
