package com.epam.training.sportsbetting.business.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;
import com.epam.training.sportsbetting.data.facade.EventRelatedFacade;
import com.epam.training.sportsbetting.data.facade.PlayerRelatedFacade;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;

public class PlayerDaoImplTest {
    private PlayerDaoImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerDaoImpl();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("getPlayerById() should return a player when called with an existing id.")
    public void testGetPlayerByIdWithExistingId() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = new PlayerEntity();
        entity.setId(1);
        when(facade.findPlayerById(1)).thenReturn(entity);
        underTest.setPlayerfacade(facade);
        Player player = new Player();
        player.setId(1);
        PlayerRelatedTransformer transformer = Mockito.mock(PlayerRelatedTransformer.class);
        when(transformer.playerEntityToPlayer(entity)).thenReturn(player);
        underTest.setTransformer(transformer);
        Player expected = new Player();
        expected.setId(1);

        //When
        Player actual = underTest.getPlayerById(1);

        //Then
        assertEquals(expected, actual);
        verify(facade).findPlayerById(1);
        verify(transformer).playerEntityToPlayer(entity);
    }

    @Test
    @DisplayName("getPlayerById() should throw NoSuchElementException when called with a non-existing id.")
    public void testGetPlayerByIdWithNonExistingId() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerById(15)).thenThrow(NoSuchElementException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.getPlayerById(15);
        });
        verify(facade).findPlayerById(15);
    }

    @Test
    @DisplayName("getPlayerById() should throw IllegalArgumentException when called with an invalid id.")
    public void testGetPlayerByIdWithInvalidId() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerById(-15)).thenThrow(IllegalArgumentException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getPlayerById(-15);
        });
        verify(facade).findPlayerById(-15);
    }

    @Test
    @DisplayName("getPlayerByEmail() should return a player when called with an existing email.")
    public void testGetPlayerByEmailWithExistingEmail() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = new PlayerEntity();
        entity.setId(1);
        when(facade.findPlayerByUsermail("test@mail")).thenReturn(entity);
        underTest.setPlayerfacade(facade);
        Player player = new Player();
        player.setId(1);
        PlayerRelatedTransformer transformer = Mockito.mock(PlayerRelatedTransformer.class);
        when(transformer.playerEntityToPlayer(entity)).thenReturn(player);
        underTest.setTransformer(transformer);

        Player expected = new Player();
        expected.setId(1);

        //When
        Player actual = underTest.getPlayerByEmail("test@mail");

        //Then
        assertEquals(expected, actual);
        verify(facade).findPlayerByUsermail("test@mail");
        verify(transformer).playerEntityToPlayer(entity);
    }

    @Test
    @DisplayName("getPlayerByEmail() should throw NoSuchElementException when called with a non-existing email.")
    public void testGetPlayerByEmailWithNonExistingEmail() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerByUsermail("test@mail")).thenThrow(NoSuchElementException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.getPlayerByEmail("test@mail");
        });
        verify(facade).findPlayerByUsermail("test@mail");
    }

    @Test
    @DisplayName("getPlayerByEmail() should throw IllegalArgumentException when called with a null.")
    public void testGetPlayerByEmailWithNull() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerByUsermail("test@mail")).thenThrow(IllegalArgumentException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.getPlayerByEmail("test@mail");
        });
        verify(facade).findPlayerByUsermail("test@mail");
    }

    @Test
    @DisplayName("savePlayerChanges() should save the given player when it is a modified existing player.")
    public void testSavePlayerChangesWithAnExistingPlayer() {
        //Given
        Player player = new Player();
        player.setId(1);
        player.setAccountNumber(Integer.valueOf(5));
        player.setBalance(BigDecimal.TEN);
        player.setBirth(LocalDateTime.of(2020, 3, 3, 0, 2));
        player.setName("Test Name");
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = Mockito.mock(PlayerEntity.class);
        when(facade.findPlayerById(1)).thenReturn(entity);
        underTest.setPlayerfacade(facade);

        //When
        underTest.savePlayerChanges(player);

        //Then
        verify(facade).findPlayerById(1);
        verify(entity).setAccountNumber(Integer.valueOf(5));
        verify(entity).setBalance(BigDecimal.TEN);
        verify(entity).setBirth(LocalDateTime.of(2020, 3, 3, 0, 2));
        verify(entity).setName("Test Name");
        verify(facade).savePlayer(entity);
    }

    @Test
    @DisplayName("savePlayerChanges() should throw IllegalArgumentException when called with null.")
    public void testSavePlayerChangesWithNull() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.savePlayerChanges(null);
        });
    }

    @Test
    @DisplayName("savePlayerChanges() should throw IllegalArgumentException when the given player has invalid id.")
    public void testSavePlayerChangesWithInvalidId() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerById(0)).thenThrow(IllegalArgumentException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.savePlayerChanges(new Player());
        });
        verify(facade).findPlayerById(0);
    }

    @Test
    @DisplayName("savePlayerChanges() should throw IllegalArgumentException when the given player has diffrent version then the database.")
    public void testSavePlayerChangesWithInvalidVersion() {
        //Given
        Player player = new Player();
        player.setId(1);
        player.setVersion(100);
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = Mockito.mock(PlayerEntity.class);
        when(entity.getId()).thenReturn(1L);
        when(entity.getVersion()).thenReturn(120L);
        when(facade.findPlayerById(1)).thenReturn(entity);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.savePlayerChanges(player);
        });
        verify(facade).findPlayerById(1);
        verify(entity).getVersion();
    }

    @Test
    @DisplayName("savePlayerChanges() should throw NoSuchElementException when the given player has non-existing id.")
    public void testSavePlayerChangesWithNonExistingId() {
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerById(100)).thenThrow(NoSuchElementException.class);
        underTest.setPlayerfacade(facade);
        Player player = new Player();
        player.setId(100);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.savePlayerChanges(player);
        });
        verify(facade).findPlayerById(100);
    }

    @Test
    @DisplayName("deleteWager() should delete the given wager and add it's amount to the player's balance when called with an existing wager.")
    public void testDeleteWagerWithValidWager() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        WagerEntity wager = Mockito.mock(WagerEntity.class);
        PlayerEntity player = Mockito.mock(PlayerEntity.class);
        when(facade.findWagerById(10)).thenReturn(wager);
        when(wager.getPlayer()).thenReturn(player);
        when(wager.isProcesed()).thenReturn(false);
        UserEntity user = mock(UserEntity.class);

        when(user.getEmail()).thenReturn("test@mail");

        when(player.getUser()).thenReturn(user);
        when(player.getBalance()).thenReturn(BigDecimal.TEN);
        when(wager.getAmount()).thenReturn(BigDecimal.TEN);
        underTest.setPlayerfacade(facade);

        //When
        underTest.deleteWager(10, "test@mail");

        //Then
        verify(facade).findWagerById(10);
        verify(wager).getPlayer();
        verify(wager).isProcesed();
        verify(user).getEmail();
        verify(player).getUser();
        verify(player).getBalance();
        verify(wager).getAmount();
        verify(facade).savePlayer(player);
        verify(facade).deleteWager(10);
    }

    @Test
    @DisplayName("deleteWager() should throw IllegalArgumentException when the given id is invalid.")
    public void testDeletaWagerwithInvalidId() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findWagerById(0)).thenThrow(IllegalArgumentException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.deleteWager(0, "test@email");
        });
        verify(facade).findWagerById(0);
    }

    @Test
    @DisplayName("deleteWager() should throw IllegalArgumentException when the wager is processed.")
    public void testDeletaWagerWithProcessedWager() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        WagerEntity wager = Mockito.mock(WagerEntity.class);
        PlayerEntity player = Mockito.mock(PlayerEntity.class);
        when(facade.findWagerById(10)).thenReturn(wager);
        when(wager.getPlayer()).thenReturn(player);
        when(wager.isProcesed()).thenReturn(true);
        UserEntity user = mock(UserEntity.class);
        when(user.getEmail()).thenReturn("test@mail");
        when(player.getUser()).thenReturn(user);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.deleteWager(10, "test@mail");
        });
        verify(facade).findWagerById(10);
        verify(wager).getPlayer();
        verify(wager).isProcesed();
        verify(user).getEmail();
        verify(player).getUser();
    }

    @Test
    @DisplayName("deleteWager() should throw IllegalArgumentException when the email is null.")
    public void testDeletaWagerWithNullEmail() {
        //Given
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        WagerEntity wager = Mockito.mock(WagerEntity.class);
        PlayerEntity player = Mockito.mock(PlayerEntity.class);
        when(facade.findWagerById(10)).thenReturn(wager);
        when(wager.getPlayer()).thenReturn(player);
        when(wager.isProcesed()).thenReturn(true);
        UserEntity user = mock(UserEntity.class);
        when(user.getEmail()).thenReturn("test@mail");
        when(player.getUser()).thenReturn(user);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.deleteWager(10, null);
        });
        verify(facade).findWagerById(10);
        verify(wager).getPlayer();
        verify(player).getUser();
    }

    @Test
    @DisplayName("deleteWager() should throw NoSuchElementException when called with a non-existing id.")
    public void testDeletaWagerwithNonExistingId() {
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findWagerById(10)).thenThrow(NoSuchElementException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.deleteWager(10, "test@email");
        });
        verify(facade).findWagerById(10);
        verify(facade).findWagerById(10);
    }

    @Test
    @DisplayName("saveNewWager() should persist the wager and subtract the wager's amount from the player's balance when with a valid wager.")
    public void testSaveWagerWithValidWager() {
        //Given
        Wager wager = mock(Wager.class);
        Player player = mock(Player.class);
        when(wager.getPlayer()).thenReturn(player);
        when(wager.getAmount()).thenReturn(BigDecimal.ONE);
        when(wager.getOddId()).thenReturn(10L);
        when(player.getId()).thenReturn(1L);
        when(player.getBalance()).thenReturn(BigDecimal.ONE);
        when(player.getVersion()).thenReturn(125L);
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = mock(PlayerEntity.class);
        when(entity.getVersion()).thenReturn(125L);
        when(facade.findPlayerById(1)).thenReturn(entity);
        OutcomeOddEntity odd = mock(OutcomeOddEntity.class);
        EventRelatedFacade eventfacade = mock(EventRelatedFacade.class);
        when(eventfacade.findOutcomeOddById(10L)).thenReturn(odd);
        underTest.setEventfacade(eventfacade);
        underTest.setPlayerfacade(facade);

        //When
        underTest.saveNewWager(wager);

        //Then
        verify(wager).getPlayer();
        verify(player).getId();
        verify(facade).findPlayerById(1);
        verify(player).getVersion();
        verify(entity).getVersion();
        verify(player, times(2)).getBalance();
        verify(wager, times(3)).getAmount();
        verify(entity).setBalance(BigDecimal.ZERO);
        verify(facade).savePlayer(entity);
        verify(eventfacade).findOutcomeOddById(10L);
    }

    @Test
    @DisplayName("saveNewWager() should throw IllegalArgumentException when with a null as wager.")
    public void testSaveWagerWith() {
        //Given
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.saveNewWager(null);
        });
    }

    @Test
    @DisplayName("saveNewWager() should throw IllegalArgumentException when called with a wager that has no player reference.")
    public void testSaveWagerWith2() {
        //Given
        Wager wager = mock(Wager.class);
        when(wager.getPlayer()).thenReturn(null);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.saveNewWager(wager);
        });
        verify(wager).getPlayer();
    }

    @Test
    @DisplayName("saveNewWager() should throw IllegalArgumentException when called with a wager that has a player with invalid id.")
    public void testSaveWagerWith3() {
        //Given
        Wager wager = mock(Wager.class);
        Player player = mock(Player.class);
        when(wager.getPlayer()).thenReturn(player);
        when(player.getId()).thenReturn(0L);
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        when(facade.findPlayerById(0)).thenThrow(IllegalArgumentException.class);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.saveNewWager(wager);
        });
        verify(wager).getPlayer();
        verify(player).getId();
        verify(facade).findPlayerById(0);
    }

    @Test
    @DisplayName("saveNewWager() should throw IllegalStateException when the player has version mismatch.")
    public void testSaveWagerWithPlayerVersionMismatch() {
        //Given
        Wager wager = mock(Wager.class);
        Player player = mock(Player.class);
        when(wager.getPlayer()).thenReturn(player);
        when(wager.getAmount()).thenReturn(BigDecimal.ONE);
        when(player.getId()).thenReturn(1L);
        when(player.getBalance()).thenReturn(BigDecimal.ONE);
        when(player.getVersion()).thenReturn(120L);
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = mock(PlayerEntity.class);
        when(entity.getVersion()).thenReturn(125L);
        when(facade.findPlayerById(1)).thenReturn(entity);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalStateException.class, () -> {
            underTest.saveNewWager(wager);
        });
        verify(wager).getPlayer();
        verify(player).getId();
        verify(facade).findPlayerById(1);
        verify(player).getVersion();
        verify(entity).getVersion();
    }

    @Test
    @DisplayName("saveNewWager() should throw IllegalStateException when the wager's amount is bigger than the player's balance.")
    public void testSaveWagerWith4() {
        //Given
        Wager wager = mock(Wager.class);
        Player player = mock(Player.class);
        when(wager.getPlayer()).thenReturn(player);
        when(wager.getAmount()).thenReturn(BigDecimal.ONE);
        when(player.getId()).thenReturn(1L);
        when(player.getBalance()).thenReturn(BigDecimal.ZERO);
        when(player.getVersion()).thenReturn(125L);
        PlayerRelatedFacade facade = Mockito.mock(PlayerRelatedFacade.class);
        PlayerEntity entity = mock(PlayerEntity.class);
        when(entity.getVersion()).thenReturn(125L);
        when(facade.findPlayerById(1)).thenReturn(entity);
        underTest.setPlayerfacade(facade);

        //When
        //Then
        assertThrows(IllegalStateException.class, () -> {
            underTest.saveNewWager(wager);
        });
        verify(wager).getPlayer();
        verify(player).getId();
        verify(facade).findPlayerById(1);
        verify(player).getVersion();
        verify(entity).getVersion();
        verify(player).getBalance();
        verify(wager).getAmount();
    }

}
