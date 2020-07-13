package com.epam.training.sportsbetting.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**The object representation of the USER database table.
 * */
@Entity
@Table(name = "USER")
public class UserEntity {
    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_USER")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_USER")
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PlayerEntity player;

    public UserEntity() {
    }

    /**Creates the relation between this user and the given player.
     * @param player  
     * */
    public void createRelationWithPlayer(PlayerEntity player) {
        this.player = player;
        player.setUser(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (int) (id ^ (id >>> SHIFT));
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserEntity other = (UserEntity) obj;
        return this.id == other.id;
    }

}
