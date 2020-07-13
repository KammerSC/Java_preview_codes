package com.epam.training.sportsbetting.business.dao;

import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;
import com.epam.training.sportsbetting.data.util.CurrencyType;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.User;
import com.epam.training.sportsbetting.domain.Wager;

/** Transformer that transforms player related Entity objects such as {@link PlayerEntity}, {@link UserEntity},
 *  {@link OutcomeEntity}, {@link WagerEntity} to domain objects.
 * */
public class PlayerRelatedTransformer {

    /** Transforms the given {@link WagerEntity} to an {@link Wager}.
     * @param entity 
     * @return {@link Wager} 
     * */
    protected Wager wagerEntityToWager(WagerEntity entity) {
        Wager wager = new Wager();
        wager.setId(entity.getId());
        wager.setAmount(entity.getAmount());
        Currency currency = getCurrency(entity.getPlayer().getCurrency());
        wager.setCurrency(currency);
        wager.setTimestampCreated(entity.getTimeStampCreated());
        wager.setOddId(entity.getOdd().getId());
        wager.setProcessed(entity.isProcesed());
        wager.setWin(entity.isWin());
        return wager;
    }

    /** Transforms the given {@link PlayerEntity} to an {@link Player} and also sets it's email which is an inherited field from {@link User}.
     * @param entity 
     * @return {@link Player} 
     * */
    protected Player playerEntityToPlayer(PlayerEntity entity) {
        Player player = new Player();
        player.setAccountNumber(entity.getAccountNumber());
        player.setBalance(entity.getBalance());
        player.setBirth(entity.getBirth());
        player.setCurrency(getCurrency(entity.getCurrency()));
        player.setEmail(entity.getUser().getEmail());
        player.setPassword(entity.getUser().getPassword());
        player.setId(entity.getId());
        player.setName(entity.getName());
        player.setVersion(entity.getVersion());
        entity.getWagers().forEach(wager -> player.setReferencesWithWager(wagerEntityToWager(wager)));
        return player;
    }

    private Currency getCurrency(CurrencyType currencyType) {
        Currency cur = Currency.USD;
        for(Currency currency: Currency.values()) {
            if(currency.toString().equals(currencyType.toString())) {
                cur = currency;
                break;
            }
        }
        return cur;
    }

}
