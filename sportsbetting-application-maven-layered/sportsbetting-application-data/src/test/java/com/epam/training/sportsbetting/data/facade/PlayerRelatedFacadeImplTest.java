package com.epam.training.sportsbetting.data.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.training.sportsbetting.data.crud.PlayerRepository;
import com.epam.training.sportsbetting.data.crud.UserRepository;
import com.epam.training.sportsbetting.data.crud.WagerRepository;
import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;

public class PlayerRelatedFacadeImplTest {

    private PlayerRelatedFacadeImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerRelatedFacadeImpl();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("findUserByEmail() should return a UserEntity with the given email.")
    public void testFindUserByEmailWithTestEmail() {
        //Given
        UserRepository repo = Mockito.mock(UserRepository.class);
        UserEntity user = new UserEntity();
        user.setId(5);
        user.setEmail("testEmail");
        when(repo.findByEmail("testEmail")).thenReturn(user);
        underTest.setUserRepo(repo);
        UserEntity expected = new UserEntity();
        expected.setId(5);
        expected.setEmail("testEmail");

        //When
        UserEntity actual = underTest.findUserByEmail("testEmail");

        //Then
        assertEquals(expected, actual);
        verify(repo).findByEmail("testEmail");
    }

    @Test
    @DisplayName("findUserByEmail() should throw IllegalArgumentException when called with null parameter.")
    public void testFindUserByEmailWithParameterNull() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findUserByEmail(null);
        });
    }

    @Test
    @DisplayName("findUserByEmail() should throw NoSuchElementException when called with email address that doesn't belong to any user.")
    public void testFindUserByEmailWithNonExistingUserEmail() {
        //Give
        UserRepository repo = Mockito.mock(UserRepository.class);
        when(repo.findByEmail("RandomEmailAddress")).thenReturn(null);
        underTest.setUserRepo(repo);
        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findUserByEmail("RandomEmailAddress");
        });
    }

    @Test
    @DisplayName("findPlayerById() should return a PlayerEntity when called with an existing id.")
    public void testFindPlayerByIdWithParameterTen() {
        //Give
        PlayerRepository repo = Mockito.mock(PlayerRepository.class);
        PlayerEntity player = new PlayerEntity();
        player.setId(10);
        when(repo.findById(10)).thenReturn(player);
        underTest.setPlayerRepo(repo);
        PlayerEntity expected = new PlayerEntity();
        expected.setId(10);

        //When
        PlayerEntity actual = underTest.findPlayerById(10);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(10);
    }

    @Test
    @DisplayName("findPlayerById() should throw NoSuchElementException when called with a non-existing id.")
    public void testFindPlayerByIdWithParameterFiftyTwo() {
        //Give
        PlayerRepository repo = Mockito.mock(PlayerRepository.class);
        when(repo.findById(52)).thenReturn(null);
        underTest.setPlayerRepo(repo);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findPlayerById(52);
        });
        verify(repo).findById(52);
    }

    @Test
    @DisplayName("findPlayerById() should throw IllegalArgumentException when called with an invalid id.")
    public void testFindPlayerByIdWithParameterZero() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findPlayerById(0);
        });
    }

    @Test
    @DisplayName("findPlayerByUsermail() should return a player when called with an email address belongs the a existing user.")
    public void testFindPlayerByUsermailWithExistingEmail() {
        //Give
        PlayerRepository repo = Mockito.mock(PlayerRepository.class);
        PlayerEntity player = new PlayerEntity();
        player.setId(10);
        when(repo.findByUserEmail("TestElekEmail")).thenReturn(player);
        underTest.setPlayerRepo(repo);
        PlayerEntity expected = new PlayerEntity();
        expected.setId(10);

        //When
        PlayerEntity actual = underTest.findPlayerByUsermail("TestElekEmail");

        //Then
        assertEquals(expected, actual);
        verify(repo).findByUserEmail("TestElekEmail");
    }

    @Test
    @DisplayName("findPlayerByUsermail() should throw NoSuchElementException when the given email doesn't registered to any user.")
    public void testFindPlayerByUsermailWithNonExistingEmail() {
        //Give
        PlayerRepository repo = Mockito.mock(PlayerRepository.class);
        when(repo.findByUserEmail("TestElekEmail")).thenReturn(null);
        underTest.setPlayerRepo(repo);
        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findPlayerByUsermail("TestElekEmail");
        });
    }

    @Test
    @DisplayName("findPlayerByUsermail() should throw IllegalArgumentException when called with null.")
    public void testFindPlayerByUsermailWithParameterNull() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findPlayerByUsermail(null);
        });
    }

    @Test
    @DisplayName("savePlayer() should persist the given PlayerEntity when it doesn't exist.")
    public void testSavePlayerWithNonExistingPlayer() {
        //Give
        PlayerRepository repo = mock(PlayerRepository.class);
        when(repo.findById(0)).thenReturn(null);
        underTest.setPlayerRepo(repo);
        PlayerEntity player = new PlayerEntity();
        player.setId(0);

        //When
        underTest.savePlayer(player);

        //Then
        verify(repo).save(player);
    }

    @Test
    @DisplayName("savePlayer() should save changes when called with an existing player that has the same version number.")
    public void testSavePlayerWithExistingValidVersion() {
        PlayerRepository repo = mock(PlayerRepository.class);
        PlayerEntity previous = new PlayerEntity();
        previous.setId(10);
        previous.setVersion(100);
        when(repo.findById(0)).thenReturn(previous);
        underTest.setPlayerRepo(repo);
        PlayerEntity player = new PlayerEntity();
        player.setId(0);
        player.setVersion(100);

        //When
        underTest.savePlayer(player);

        //Then
        verify(repo).save(player);
    }

    @Test
    @DisplayName("savePlayer() should throw ConcurrentModificationException when called with an existing player that has has the different version number.")
    public void testSavePlayerWithExistingInvalidVersion() {
        PlayerRepository repo = mock(PlayerRepository.class);
        PlayerEntity previous = new PlayerEntity();
        previous.setId(10);
        previous.setVersion(101);
        when(repo.findById(0)).thenReturn(previous);
        underTest.setPlayerRepo(repo);
        PlayerEntity player = new PlayerEntity();
        player.setId(0);
        player.setVersion(80);

        //When
        //Then
        assertThrows(ConcurrentModificationException.class, () -> {
            underTest.savePlayer(player);
        });
    }

    @Test
    @DisplayName("savePlayer() should throw IllegalArgumentException when called with null.")
    public void testSavePlayerWith() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.savePlayer(null);
        });
    }

    @Test
    @DisplayName("findWagerById() should return a wager when called with an existing id.")
    public void testFindWagerByIdWithValidExistingId() {
        //Give
        WagerRepository repo = Mockito.mock(WagerRepository.class);
        WagerEntity wager = new WagerEntity();
        wager.setId(10);
        when(repo.findById(10)).thenReturn(wager);
        underTest.setWagerRepo(repo);
        WagerEntity expected = new WagerEntity();
        expected.setId(10);

        //When
        WagerEntity actual = underTest.findWagerById(10);

        //Then
        assertEquals(expected, actual);
        verify(repo).findById(10);
    }

    @Test
    @DisplayName("findWagerById() should throw NoSuchElementException when called with an non-existing id.")
    public void testFindWagerByIdWithValidNonExistingId() {
        WagerRepository repo = Mockito.mock(WagerRepository.class);
        when(repo.findById(10)).thenReturn(null);
        underTest.setWagerRepo(repo);

        //When
        //Then
        assertThrows(NoSuchElementException.class, () -> {
            underTest.findWagerById(10);
        });
        verify(repo).findById(10);
    }

    @Test
    @DisplayName("findWagerById() should throw IllegalArgumentException when called with an invalid id.")
    public void testFindWagerByIdWithInvalidId() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findWagerById(0);
        });
    }

    @Test
    @DisplayName("findAllWagerOfPlayer() should return a list of wager when called with an exist player id and the player has wagers.")
    public void testFindAllWagerOfPlayerWithExistingIdAndHasWagers() {
        //Give
        WagerRepository repo = Mockito.mock(WagerRepository.class);
        WagerEntity wagerOne = new WagerEntity();
        wagerOne.setId(10);
        WagerEntity wagerTwo = new WagerEntity();
        wagerTwo.setId(11);
        List<WagerEntity> list = new ArrayList<WagerEntity>();
        list.add(wagerTwo);
        list.add(wagerOne);
        when(repo.findByPlayerId(5)).thenReturn(list);
        underTest.setWagerRepo(repo);
        WagerEntity expectedOne = new WagerEntity();
        expectedOne.setId(10);
        WagerEntity expectedTwo = new WagerEntity();
        expectedTwo.setId(11);
        List<WagerEntity> expected = new ArrayList<WagerEntity>();
        expected.add(expectedTwo);
        expected.add(expectedOne);

        //When
        List<WagerEntity> actual = underTest.findAllWagerOfPlayer(5);

        //Then
        assertEquals(expected, actual);
        verify(repo).findByPlayerId(5);

    }

    @Test
    @DisplayName("findAllWagerOfPlayer() should return an empty list when called with an exist player id and the player has no wagers.")
    public void testFindAllWagerOfPlayerWithExistingIdAndHasNoWagers() {
        //Give
        WagerRepository repo = Mockito.mock(WagerRepository.class);
        List<WagerEntity> list = new ArrayList<WagerEntity>();
        when(repo.findByPlayerId(5)).thenReturn(list);
        underTest.setWagerRepo(repo);
        List<WagerEntity> expected = new ArrayList<WagerEntity>();

        //When
        List<WagerEntity> actual = underTest.findAllWagerOfPlayer(5);

        //Then
        assertEquals(expected, actual);
        verify(repo).findByPlayerId(5);
    }

    @Test
    @DisplayName("findAllWagerOfPlayer() should throw IllegalArgumentException when called with invalid id.")
    public void testFindAllWagerOfPlayerWithInvalidId() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.findWagerById(0);
        });
    }

    @Test
    @DisplayName("saveWager() should persist the given entity when called.")
    public void testSaveWagerWithEntity() {
        //Give
        WagerRepository repo = Mockito.mock(WagerRepository.class);
        WagerEntity wager = new WagerEntity();
        underTest.setWagerRepo(repo);

        //When
        underTest.saveWager(wager);

        //Then
        verify(repo).save(wager);
    }

    @Test
    @DisplayName("saveWager() should throw IllegalArgumentException when called null.")
    public void testSaveWagerWithNull() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.saveWager(null);
        });
    }

    @Test
    @DisplayName("deleteWager() should delete a wager when the given id is valid")
    public void testDeleteWagerWithValidId() {
        //Give
        WagerRepository repo = Mockito.mock(WagerRepository.class);
        underTest.setWagerRepo(repo);

        //When
        underTest.deleteWager(10L);

        //Then
        verify(repo).deleteById(10L);
    }

    @Test
    @DisplayName("deleteWager() should throw IllegalArgumentException when the given id is invalid")
    public void testDeleteWagerWithInvalidId() {
        //Give
        //When
        //Then
        assertThrows(IllegalArgumentException.class, () -> {
            underTest.deleteWager(0);
        });
    }

}
