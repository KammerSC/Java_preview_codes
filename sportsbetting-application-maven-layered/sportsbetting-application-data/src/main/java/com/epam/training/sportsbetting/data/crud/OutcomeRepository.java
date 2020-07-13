package com.epam.training.sportsbetting.data.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.data.entities.OutcomeEntity;

/** Repository interface for Outcome queries.
 * */
public interface OutcomeRepository extends CrudRepository<OutcomeEntity, Long> {

    /** Query to get an Outcome by it's ID.
     * @param id of the Outcome.
     * @return OutcomeEntity with the given ID or a null if no such event found.
     * */
    OutcomeEntity findById(long id);

    /** Query to find an Outcomes related to a Bet.
     * @param id of the the bet.
     * @return List<OutcomeEntity> 
     * */
    List<OutcomeEntity> findByBetId(long id);

}
