package com.epam.training.sportsbetting.data.crud;

import org.springframework.data.repository.CrudRepository;

import com.epam.training.sportsbetting.data.entities.AdminEntity;

/** Repository interface for Admin queries.
 * */
public interface AdminRepository extends CrudRepository<AdminEntity, Long> {
    /** Returns an {@link AdminEntity} with the given name.
     * @param name of the admin
     * @return {@link AdminEntity}
     * */
    AdminEntity findByName(String name);
}
