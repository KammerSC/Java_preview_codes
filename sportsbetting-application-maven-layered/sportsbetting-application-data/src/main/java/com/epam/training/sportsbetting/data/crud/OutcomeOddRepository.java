package com.epam.training.sportsbetting.data.crud;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;

/** Repository interface for OutcomeOdd queries.
 * */
public interface OutcomeOddRepository extends CrudRepository<OutcomeOddEntity, Long> {

    /** Query to get an OutcomeOdd by it's ID.
     * @param id of the OutcomeOddEntity.
     * @return OutcomeOddEntity with the given ID or a null if no such event found.
     * */
    OutcomeOddEntity findById(long id);

    /** Query to get the valid OutcomeOdd of the Outcome to the given time.
     * @param outcomeId id of the Outcome.
     * @param time 
     * @return OutcomeOddEntity that is valid at the given time  
     * */
    @Query("SELECT odd FROM OutcomeOddEntity odd WHERE odd.outcome.id = :outcomeId AND odd.validFrom <= :time AND odd.validUntil >= :time")
    OutcomeOddEntity findValidOddOfOutcome(@Param("outcomeId") long outcomeId, @Param("time") LocalDateTime time);

}
