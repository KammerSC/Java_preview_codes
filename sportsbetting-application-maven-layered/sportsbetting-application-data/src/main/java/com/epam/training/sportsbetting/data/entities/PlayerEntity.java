package com.epam.training.sportsbetting.data.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import com.epam.training.sportsbetting.data.util.CurrencyType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**The object representation of the PLAYER database table.
 * */
@Entity
@Table(name = "PLAYER")
@JsonIgnoreProperties(value = {"id", "version", "user", "wagers"})
public class PlayerEntity {
    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_PLAYER")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_PLAYER")
    @Column(name = "ID")

    private long id;
    @Version
    @Column(name = "VERSION")
    private long version;
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACCOUNT_NUMBER")
    private Integer accountNumber;
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @Column(name = "BIRTHDATE")
    private LocalDateTime birth;
    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY")
    private CurrencyType currency;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "player")
    private List<WagerEntity> wagers = new ArrayList<WagerEntity>();

    public PlayerEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public List<WagerEntity> getWagers() {
        return wagers;
    }

    public void setWagers(List<WagerEntity> wagers) {
        this.wagers = wagers;
    }

    /** Add the given {@link WagerEntity} to this entity wagers list and set the given wager's {@link PlayerEntity} to this object.
     * @param wager  
     * */
    public void setRelationWithWager(WagerEntity wager) {
        wagers.add(wager);
        wager.setPlayer(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + (int) (id ^ (id >>> SHIFT));
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
        PlayerEntity other = (PlayerEntity) obj;
        return this.id == other.id;
    }

}
