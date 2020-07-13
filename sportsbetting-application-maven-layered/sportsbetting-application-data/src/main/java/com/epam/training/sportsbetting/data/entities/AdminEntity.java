package com.epam.training.sportsbetting.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**The object representation of the ADMIN database table.
 * */
@Entity
@Table(name = "ADMIN")
public class AdminEntity {

    @TableGenerator(name = "GEN_ADMIN")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ADMIN")
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    public AdminEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
