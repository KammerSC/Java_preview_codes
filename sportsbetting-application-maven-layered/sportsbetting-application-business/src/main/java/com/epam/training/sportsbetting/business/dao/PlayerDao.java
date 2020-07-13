package com.epam.training.sportsbetting.business.dao;

import java.util.NoSuchElementException;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;

/** Interface get domain object form database and persist changes on {@link Player} and {@link Wager} objects.
 * */
public interface PlayerDao {
    /** Return a {@link Player} that belongs to the given id.
     * @param id of the player.
     * @return {@link Player} with the given id.
     * @throws NoSuchElementException if there is no player with given id.
     * @throws IllegalArgumentException if the given id is invalid.
     * */
    Player getPlayerById(long id);

    /** Return a {@link Player} that belongs to the given email.
     * @param email of the player.
     * @return {@link Player} with the given email.
     * @throws NoSuchElementException if there is no player with given email.
     * @throws IllegalArgumentException if the given email is null.
     * */
    Player getPlayerByEmail(String email);

    /** Saves the changes of the given {@link Player}.
     * @param player 
     * @throws IllegalArgumentException if the given player is null
     *  or there is version number mismatch or the given player has invalid id .
     * @throws NoSuchElementException if there is no player in the database with the given id.
     * */
    void savePlayerChanges(Player player);

    /** Deletes the {@link Wager} with the given id and adds the wager's amount to the player's balance if its exist.
     * @param id of the wager
     * @param email the user's email
     * @throws IllegalArgumentException is the given id invalid, the wager is processed, the email is null or the wager does not belong to the given player.
     * @throws NoSuchElementException if there is no wager with the give id.
     * */
    void deleteWager(long id, String email);

    /** Saves the given {@link Wager} and subtract the wager's amount from players balance.
     * @param wager 
     * @throws IllegalArgumentException if the given wager is null, it's player reference is null or it's player has invalid id.
     * @throws IllegalStateException if the wager's amount cannot be subtracted from the player or there is version mismatch with the database.
     * */
    void saveNewWager(Wager wager);

}
