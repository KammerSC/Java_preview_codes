package com.epam.training.sportsbetting.data.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.UserEntity;

/** Repository interface for Player queries.
 * */
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

    /** Query to get a {@link PlayerEntity} by it's ID.
     * @param id of the PlayerEntity.
     * @return PlayerEntity or null if there is no player with the given id.
     * */
    PlayerEntity findById(long id);

    /** Query to get a {@link PlayerEntity} by it's related {@link UserEntity}'s ID.
     * @param id of the UserEntity.
     * @return PlayerEntity or null if there is no UserEntity with the given id.
     * */
    PlayerEntity findByUserId(long id);

    /** Query to get a {@link PlayerEntity} by it's related {@link UserEntity}'s Email.
     * @param email of the UserEntity.
     * @return PlayerEntity or null if there is no UserEntity with the given email.
     * */
    @Query("SELECT player FROM PlayerEntity player WHERE player.user.email = :email")
    PlayerEntity findByUserEmail(@Param("email") String email);
}
