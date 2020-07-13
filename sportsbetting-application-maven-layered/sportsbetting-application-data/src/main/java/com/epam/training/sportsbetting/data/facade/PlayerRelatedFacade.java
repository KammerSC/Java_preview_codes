package com.epam.training.sportsbetting.data.facade;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;

import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;

/** Facade for {@link UserEntity}, {@link PlayerEntity}, {@link WagerEntity} repositories.
 * */
public interface PlayerRelatedFacade {
    /** Query to get a {@link UserEntity} by it's email.
     * @param email 
     * @return {@link UserEntity}
     * @throws IllegalArgumentException if the given email is null.
     * @throws NoSuchElementException if there is no entity with the given id.
     * */
    UserEntity findUserByEmail(String email);

    /** Query to get a {@link PlayerEntity} by it's id.
     * @param id 
     * @return {@link PlayerEntity}
     * @throws IllegalArgumentException if the given id is 0 or less.
     * @throws NoSuchElementException if there is no entity with the given id.
     * */
    PlayerEntity findPlayerById(long id);

    /** Query to get a {@link PlayerEntity} by it's user's email.
     * @param email 
     * @return {@link PlayerEntity}
     * @throws IllegalArgumentException if the given email is null.
     * @throws NoSuchElementException if there is no entity with the given email.
     * */
    PlayerEntity findPlayerByUsermail(String email);

    /** Persist the given {@link PlayerEntity} if it not exists, merge if it already exists.
     * @param player 
     * @throws IllegalArgumentException if the given entity is null.
     * @throws ConcurrentModificationException if the entity is modified by an other thread.
     * */
    void savePlayer(PlayerEntity player);

    /** Query to get a {@link WagerEntity} by it's id.
     * @param id 
     * @return {@link WagerEntity}
     * @throws IllegalArgumentException if the given id is 0 or less.
     * @throws NoSuchElementException if there is no entity with the given id.
     * */
    WagerEntity findWagerById(long id);

    /** Query to get all wager of a player.
     * @param id of the {@link PlayerEntity}.
     * @return List<WagerEntity>
     * @throws IllegalArgumentException if the given id 0 or less.
     * */
    List<WagerEntity> findAllWagerOfPlayer(long id);

    /** Persist the given {@link WagerEntity} if it not exists, merge if it already exists.
     * @param wager 
     * @throws IllegalArgumentException if the given entity is null.
     * */
    void saveWager(WagerEntity wager);

    /** Deletes the given {@link WagerEntity}.
     * @param id of the wager
     * @throws IllegalArgumentException if the given entity is null.
     * */
    void deleteWager(long id);
}
