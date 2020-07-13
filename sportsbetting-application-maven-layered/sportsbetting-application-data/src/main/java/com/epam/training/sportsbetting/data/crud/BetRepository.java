package com.epam.training.sportsbetting.data.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.data.entities.BetEntity;

/** Repository interface for Bet queries.
 * */
public interface BetRepository extends CrudRepository<BetEntity, Long> {

    /** Query to get a Bet by it's ID.
     * @param id of the bet.
     * @return Bet with the given ID or a null if no such event found.
     * */
    BetEntity findById(long id);

    /** Query to find an Bets related to a SportEvent.
     * @param id of the the event.
     * @return List<BetEntity> 
     * */
    List<BetEntity> findByEventId(long id);

}
