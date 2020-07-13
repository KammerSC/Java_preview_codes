package com.epam.training.sportsbetting.business.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;
import com.epam.training.sportsbetting.data.util.CurrencyType;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;

public class PlayerRelatedTransformerTest {

    private PlayerRelatedTransformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerRelatedTransformer();
    }

    @AfterEach
    public void tearDown() {
        underTest = null;
    }

    @Test
    @DisplayName("wagerEntityToWager() should return a wager when called with a wager entity.")
    public void testWagerEntityToWager() {
        //Given
        WagerEntity entity = new WagerEntity();
        entity.setId(1);
        entity.setAmount(BigDecimal.TEN);
        OutcomeOddEntity odd = new OutcomeOddEntity();
        odd.setId(1);
        entity.setOdd(odd);
        LocalDateTime time = LocalDateTime.now();
        entity.setTimeStampCreated(time);
        PlayerEntity player = new PlayerEntity();
        player.setCurrency(CurrencyType.USD);
        entity.setPlayer(player);

        Wager expected = new Wager();
        expected.setAmount(BigDecimal.TEN);
        expected.setCurrency(Currency.USD);
        expected.setId(1);
        expected.setTimestampCreated(time);
        expected.setOddId(1);

        //When
        Wager actual = underTest.wagerEntityToWager(entity);

        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTimestampCreated(), actual.getTimestampCreated());
        assertEquals(expected.getOddId(), actual.getOddId());
    }

    @Test
    @DisplayName("playerEntityToPlayer() should return a new player when called with a player entity")
    public void testPlayerEntityToPlayerWithPlayerEntity() {
        //Given
        PlayerEntity entity = new PlayerEntity();
        entity.setAccountNumber(Integer.valueOf(10));
        entity.setBalance(BigDecimal.TEN);
        LocalDateTime time = LocalDateTime.now();
        entity.setBirth(time);
        entity.setCurrency(CurrencyType.HUF);
        entity.setId(1);
        entity.setName("Test Name");
        entity.setVersion(10);
        WagerEntity wagerEntity1 = new WagerEntity();
        wagerEntity1.setId(1);
        OutcomeOddEntity odd1 = new OutcomeOddEntity();
        odd1.setId(1);
        wagerEntity1.setOdd(odd1);
        WagerEntity wagerEntity2 = new WagerEntity();
        wagerEntity2.setId(2);
        OutcomeOddEntity odd2 = new OutcomeOddEntity();
        odd2.setId(2);
        wagerEntity2.setOdd(odd2);
        entity.setRelationWithWager(wagerEntity1);
        entity.setRelationWithWager(wagerEntity2);
        UserEntity user = new UserEntity();
        user.setEmail("test@mail");
        entity.setUser(user);

        Player expected = new Player();
        expected.setAccountNumber(Integer.valueOf(10));
        expected.setBalance(BigDecimal.TEN);
        expected.setBirth(time);
        expected.setCurrency(Currency.HUF);
        expected.setEmail("test@mail");
        expected.setId(1);
        expected.setName("Test Name");
        expected.setVersion(10);
        Wager wager1 = new Wager();
        wager1.setId(1);
        Wager wager2 = new Wager();
        wager2.setId(2);
        expected.setReferencesWithWager(wager1);
        expected.setReferencesWithWager(wager2);

        //When
        Player actual = underTest.playerEntityToPlayer(entity);

        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getAccountNumber(), actual.getAccountNumber());
        assertEquals(expected.getBalance(), actual.getBalance());
        assertEquals(expected.getBirth(), actual.getBirth());
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getVersion(), actual.getVersion());
        assertEquals(expected.getWagers(), actual.getWagers());

    }

}
