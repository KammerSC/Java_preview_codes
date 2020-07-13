package com.epam.training.sportsbetting.data.crud;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.epam.training.sportsbetting.data.entities.SportEventEntity;

/** Repository interface for SportEvent queries.
 * */
public interface SportEventEntityRepository extends CrudRepository<SportEventEntity, Long> {

    /** Query to get a SportEvent by it's ID.
     * @param id of the event.
     * @return SportEventEntity with the given ID or a null if no such event found.
     * */
    SportEventEntity findById(long id);

    /** Query to get every SportEvent that ends after the given time.
     * @param time 
     * @return List<SportEventEntity>   
     * */
    @Query("SELECT event FROM SportEventEntity event WHERE event.end >= :time")
    List<SportEventEntity> findAllWithEndAfter(@Param("time") LocalDateTime time);

}
