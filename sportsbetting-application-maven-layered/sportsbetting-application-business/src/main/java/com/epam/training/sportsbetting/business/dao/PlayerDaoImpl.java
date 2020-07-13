package com.epam.training.sportsbetting.business.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;
import com.epam.training.sportsbetting.data.facade.EventRelatedFacade;
import com.epam.training.sportsbetting.data.facade.PlayerRelatedFacade;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;

/** Implementation of {@link PlayerDao} interface.
 * */
public class PlayerDaoImpl implements PlayerDao {
    private PlayerRelatedTransformer transformer;
    private PlayerRelatedFacade playerfacade;
    private EventRelatedFacade eventfacade;

    @Override
    public Player getPlayerById(long id) {
        PlayerEntity player = playerfacade.findPlayerById(id);
        return transformer.playerEntityToPlayer(player);
    }

    @Override
    public Player getPlayerByEmail(String email) {
        PlayerEntity player = playerfacade.findPlayerByUsermail(email);
        return transformer.playerEntityToPlayer(player);
    }

    @Override
    public void savePlayerChanges(Player player) {
        checkNull(player);
        PlayerEntity entity = playerfacade.findPlayerById(player.getId());
        if (isVersionMatching(player, entity)) {
            entity.setAccountNumber(player.getAccountNumber());
            entity.setBalance(player.getBalance());
            entity.setBirth(player.getBirth());
            entity.setName(player.getName());
            playerfacade.savePlayer(entity);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteWager(long id, String email) {
        WagerEntity wager = playerfacade.findWagerById(id);
        checkNull(wager);
        PlayerEntity player = wager.getPlayer();
        if (isDeletable(email, wager, player.getUser())) {
            player.setBalance(calculateNewBalance(wager, player));
            playerfacade.savePlayer(player);
            playerfacade.deleteWager(id);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void saveNewWager(Wager wager) {
        checkNull(wager);
        Player player = wager.getPlayer();
        checkNull(player);
        PlayerEntity playerEntity = playerfacade.findPlayerById(player.getId());
        if (isVersionMatching(player, playerEntity) && hasEnoughBalance(wager, player)) {
            persistChanges(wager, player, playerEntity);
        } else {
            throw new IllegalStateException();
        }
    }

    @Autowired
    public void setPlayerfacade(PlayerRelatedFacade playerfacade) {
        this.playerfacade = playerfacade;
    }

    @Autowired
    public void setEventfacade(EventRelatedFacade eventfacade) {
        this.eventfacade = eventfacade;
    }

    @Autowired
    public void setTransformer(PlayerRelatedTransformer transformer) {
        this.transformer = transformer;
    }

    private void checkNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasEnoughBalance(Wager wager, Player player) {
        return player.getBalance().compareTo(wager.getAmount()) >= 0;
    }

    private boolean isVersionMatching(Player player, PlayerEntity entity) {
        return entity.getVersion() == player.getVersion();
    }

    private boolean isDeletable(String email, WagerEntity wager, UserEntity user) {
        boolean isDeleteable = email != null && user.getEmail().equals(email) && !wager.isProcesed();
        return isDeleteable;
    }

    private BigDecimal calculateNewBalance(WagerEntity wager, PlayerEntity player) {
        BigDecimal newBalance = player.getBalance().add(wager.getAmount());
        return newBalance;
    }

    private WagerEntity createNewWagerEntity(Wager wager, PlayerEntity entity) {
        WagerEntity wagerEntity = new WagerEntity();
        wagerEntity.setAmount(wager.getAmount());
        wagerEntity.setOdd(eventfacade.findOutcomeOddById(wager.getOddId()));
        wagerEntity.setPlayer(entity);
        wagerEntity.setTimeStampCreated(wager.getTimestampCreated());
        return wagerEntity;
    }

    private void persistChanges(Wager wager, Player player, PlayerEntity playerEntity) {
        BigDecimal newBalance = player.getBalance().subtract(wager.getAmount());
        playerEntity.setBalance(newBalance);
        playerfacade.savePlayer(playerEntity);
        WagerEntity wagerEntity = createNewWagerEntity(wager, playerEntity);
        playerfacade.saveWager(wagerEntity);
    }

}
