package com.epam.training.sportsbetting.data.crud;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.data.entities.UserEntity;

/** Repository interface for UserEntity queries.
 * */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    /** Query to get a {@link UserEntity} by it's ID.
     * @param id of the {@link UserEntity}
     * @return {@link UserEntity} or null if there is no UserEntity with the given ID. 
     * */
    UserEntity findById(long id);

    /** Query to get a {@link UserEntity} by it's Email.
     * @param email of the {@link UserEntity}
     * @return {@link UserEntity} or null if there is no UserEntity with the given Email. 
     * */
    UserEntity findByEmail(String email);
}
