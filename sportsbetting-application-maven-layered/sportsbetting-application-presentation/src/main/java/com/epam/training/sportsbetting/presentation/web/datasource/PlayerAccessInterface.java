package com.epam.training.sportsbetting.presentation.web.datasource;

import java.util.List;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;
import com.epam.training.sportsbetting.presentation.web.page.home.model.WagerDescription;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerForm;

/** Provides access to {@link Player} objects.
 * */
public interface PlayerAccessInterface {
    /** Returns a {@link PlayerDetails} that belongs to the given user email.
     * @param email the email address of the user.
     * @return {@link PlayerDetails}  
     * */
    PlayerDetails getPlayerDetailsByEmail(String email);

    /** Saves the changes of the user's details.
     * @param email of the user
     * @param details is the {@link PlayerDetails} of the user.
     * */
    void savePlayerChanges(String email, PlayerDetails details);

    /** Returns all wagers of the a player converted to a list of {@link WagerDescription}.
     * @param email of the user.
     * @return List<WagerDescription> 
     * */
    List<WagerDescription> getWagerDescriptionsOfPlayer(String email);

    /** Deletes the wager with the given id and and add its amount to the player's balance if it is not processed.
     * @param id of the wager.
     * @param email user's email
     * */
    void deleteWager(long id, String email);

    /** Creates a new wager if the player has enough balance to it.
     * @param form {@link WagerForm} 
     * @param email of the user
     * */
    void saveWager(WagerForm form, String email);

}
