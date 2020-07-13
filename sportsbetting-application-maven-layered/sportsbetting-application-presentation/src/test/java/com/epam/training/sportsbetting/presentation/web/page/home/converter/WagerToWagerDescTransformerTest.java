package com.epam.training.sportsbetting.presentation.web.page.home.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.FootballSportEvent;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Type;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.presentation.web.page.home.model.WagerDescription;

public class WagerToWagerDescTransformerTest {

    private WagerToWagerDesctransformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new WagerToWagerDesctransformer();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("toWagerDescription() should return a wagerdescription when called with a wager and with the odd that belongs to it.")
    public void testToWagerDescriptionWithvalidParameters() {
        //Given
        underTest.setDeleteBase("/home/");
        Wager wager = mock(Wager.class);
        when(wager.getId()).thenReturn(8L);
        when(wager.getOddId()).thenReturn(10L);
        
        when(wager.getAmount()).thenReturn(BigDecimal.TEN);
        when(wager.isProcessed()).thenReturn(true);
        when(wager.isWin()).thenReturn(true);
        OutcomeOdd odd = mock(OutcomeOdd.class);
        when(odd.getId()).thenReturn(10L);
        
        when(odd.getValue()).thenReturn(BigDecimal.ONE);
        Outcome outcome = mock(Outcome.class);
        when(odd.getOutcome()).thenReturn(outcome);
        when(outcome.getDescription()).thenReturn("desc");
        Bet bet = mock(Bet.class);
        when(outcome.getBet()).thenReturn(bet);
        when(bet.getType()).thenReturn(Type.GOALS);
        SportEvent event = mock(FootballSportEvent.class);
        when(bet.getEvent()).thenReturn(event);
        when(event.getTitle()).thenReturn("AvsB");

        WagerDescription expected = new WagerDescription();
        expected.setBetType("GOALS");
        expected.setDeleteURL("/home/8");
        expected.setEventTitle("AvsB");
        expected.setEventType("asd");
        expected.setId(8);
        expected.setOutcomeOdd(BigDecimal.ONE.toString());
        expected.setOutcomeValue("desc");
        expected.setWagerAmount(BigDecimal.TEN.toString());
        expected.setWin("YES");
        expected.setProcessed("YES");

        //When
        WagerDescription actual = underTest.toWagerDescription(wager, odd);

        //Then
        assertEquals(expected.getBetType(), actual.getBetType());
        assertEquals(expected.getDeleteURL(), actual.getDeleteURL());
        assertEquals(expected.getEventTitle(), actual.getEventTitle());
        assertEquals(expected.getEventType(), actual.getEventType());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getOutcomeOdd(), actual.getOutcomeOdd());
        assertEquals(expected.getOutcomeValue(), actual.getOutcomeValue());
        assertEquals(expected.getProcessed(), actual.getProcessed());
        assertEquals(expected.getWagerAmount(), actual.getWagerAmount());
        assertEquals(expected.getWin(), actual.getWin());
    }

    @Test
    @DisplayName("toWagerDescription() should thow IllegalArgumentException when called with null as wager.")
    public void testToWagerDescriptionWithNullWager() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.toWagerDescription(null, new OutcomeOdd());
        });
    }

    @Test
    @DisplayName("toWagerDescription() should thow IllegalArgumentException when called with null as odd.")
    public void testToWagerDescriptionWithNullOdd() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.toWagerDescription(new Wager(), null);
        });
    }

    @Test
    @DisplayName("toWagerDescription() should thow IllegalArgumentException when called with different ids.")
    public void testToWagerDescriptionWithDifferentIds() {
        //Given
        Wager wager = mock(Wager.class);
        when(wager.getOddId()).thenReturn(15L);
        OutcomeOdd odd = mock(OutcomeOdd.class);
        when(odd.getId()).thenReturn(14L);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.toWagerDescription(wager, odd);
        });
        verify(wager).getOddId();
        verify(odd).getId();
    }
    
    @Test
    @DisplayName("setProcessedAndWin() should set proccessed and win field to unprocessed when called with aa unprocessed wager ")
    public void testSetProcessedAndWinWithUnprocessedWager() {
        //Given
        WagerDescription description = new WagerDescription();
        Wager wager = new Wager();
        String expected = "-";
        
        //When
        underTest.setProcessedAndWin(description, wager);
        
        //Then
        assertEquals(expected, description.getProcessed());
        assertEquals(expected, description.getWin());
    }
    
    @Test
    @DisplayName("setProcessedAndWin() should set proccessed and win field to YES when called with a winner wager.")
    public void testSetProcessedAndWinWithWinnerWager() {
        //Given
        WagerDescription description = new WagerDescription();
        Wager wager = new Wager();
        wager.setProcessed(true);
        wager.setWin(true);
        String expected = "YES";
        
        //When
        underTest.setProcessedAndWin(description, wager);
        
        //Then
        assertEquals(expected, description.getProcessed());
        assertEquals(expected, description.getWin());
        
    }
    @Test
    @DisplayName("setProcessedAndWin() should set proccessed to YES and win to NO when called with a loser wagger.")
    public void testSetProcessedAndWinWith() {
        WagerDescription description = new WagerDescription();
        Wager wager = new Wager();
        wager.setProcessed(true);
        wager.setWin(false);
        String expectedProcessed = "YES";
        String expectedWinner = "NO";
        
        //When
        underTest.setProcessedAndWin(description, wager);
        
        //Then
        assertEquals(expectedProcessed, description.getProcessed());
        assertEquals(expectedWinner, description.getWin());
    }

}
