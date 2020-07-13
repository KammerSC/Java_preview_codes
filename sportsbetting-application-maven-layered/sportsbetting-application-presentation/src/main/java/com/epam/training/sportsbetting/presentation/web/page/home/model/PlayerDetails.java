package com.epam.training.sportsbetting.presentation.web.page.home.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.epam.training.sportsbetting.domain.Currency;

/** Value class that holds the details of a user.
 * */
public class PlayerDetails {
    private static final int SHIFT = 32;
    private long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer accountNumber;
    private Currency currency;
    private BigDecimal balance;
    private long version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        PlayerDetails other = (PlayerDetails) obj;
        return other.id == this.id && other.currency == this.currency;
    }

}
