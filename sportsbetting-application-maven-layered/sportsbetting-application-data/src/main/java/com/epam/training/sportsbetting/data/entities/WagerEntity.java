package com.epam.training.sportsbetting.data.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**The object representation of the WAGER database table.
 * */
@Entity
@Table(name = "WAGER")
public class WagerEntity {
    private static final int BOOL_FALSE_VALUE = 1237;

    private static final int BOOL_TRUE_VALUE = 1231;

    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_WAGER")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_WAGER")
    @Column(name = "ID")
    private long id;

    @OneToOne
    private OutcomeOddEntity odd;

    @ManyToOne
    private PlayerEntity player;

    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "TIMESTAMP")
    private LocalDateTime timeStampCreated;
    @Column(name = "PROCESSED")
    private boolean procesed;
    @Column(name = "WIN")
    private boolean win;

    public WagerEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OutcomeOddEntity getOdd() {
        return odd;
    }

    public void setOdd(OutcomeOddEntity odd) {
        this.odd = odd;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(LocalDateTime timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }

    public boolean isProcesed() {
        return procesed;
    }

    public void setProcesed(boolean procesed) {
        this.procesed = procesed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
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
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + (int) (id ^ (id >>> SHIFT));
        result = prime * result + (procesed ? BOOL_TRUE_VALUE : BOOL_FALSE_VALUE);
        result = prime * result + (win ? BOOL_TRUE_VALUE : BOOL_FALSE_VALUE);
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
        WagerEntity other = (WagerEntity) obj;
        return this.id == other.id;
    }

}
