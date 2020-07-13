package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Hold detailed information about a user. Derived from {@link User}.
 * */
public class Player extends User {
    private static final int SHIFT = 32;
    private long id;
    private long version;
    private String name;
    private Integer accountNumber;
    private BigDecimal balance;
    private LocalDateTime birth;
    private Currency currency;
    private List<Wager> wagers = new ArrayList<Wager>();

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Wager> getWagers() {
        return wagers;
    }

    public void setWagers(List<Wager> wagers) {
        this.wagers = wagers;
    }

    /** Adds the given {@link Wager} to this object's wager list and set the given wager's {@link Player} reference to this object.
     * @param wager 
     * */
    public void setReferencesWithWager(Wager wager) {
        wagers.add(wager);
        wager.setPlayer(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        Player other = (Player) obj;
        return this.id == other.id;
    }

}
