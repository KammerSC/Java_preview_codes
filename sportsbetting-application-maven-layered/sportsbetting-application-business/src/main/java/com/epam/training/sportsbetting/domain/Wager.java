package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Representation of a concreted bet.
 * */
public class Wager {
    private static final int SHIFT = 32;
    private long id;
    private Player player;
    private long oddId;
    private BigDecimal amount;
    private LocalDateTime timestampCreated;
    private boolean processed;
    private boolean win;
    private Currency currency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getOddId() {
        return oddId;
    }

    public void setOddId(long wagerId) {
        this.oddId = wagerId;
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
        Wager other = (Wager) obj;
        return this.id == other.id;
    }

}
