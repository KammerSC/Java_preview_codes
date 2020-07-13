package com.epam.training.sportsbetting.data.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.data.entities.WagerEntity;

/** Repository interface for OutcomeOdd queries.
 * */
public interface WagerRepository extends CrudRepository<WagerEntity, Long> {

    /** Query to find a WagerEntity by it's id.
     * @param id of the WagerEntity
     * @return WagerEntity, or null if there is no WagerEntity exist with this id. 
     * */
    WagerEntity findById(long id);

    /** Query to get every WagerEntity related to a player.
     * @param id The players ID.
     * @return List<WagerEntity> 
     * */
    List<WagerEntity> findByPlayerId(long id);

    /** Query to get every WagerEntity related to a Odd.
     * @param id The odds ID.
     * @return List<WagerEntity> 
     * */
    List<WagerEntity> findByOddId(long id);

}
