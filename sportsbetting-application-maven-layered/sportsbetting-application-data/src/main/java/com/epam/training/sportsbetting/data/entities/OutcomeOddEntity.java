package com.epam.training.sportsbetting.data.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/** The object representation of the OUTCOME database table.
 * */
@Entity
@Table(name = "Odd")
public class OutcomeOddEntity {
    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_ODD")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_ODD")
    private long id;

    @ManyToOne
    private OutcomeEntity outcome;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "VALID_FROM")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime validFrom;

    @Column(name = "VALID_UNTIL")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime validUntil;

    public OutcomeOddEntity() {
    }

    public OutcomeOddEntity(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUntil) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public OutcomeEntity getOutcome() {
        return outcome;
    }

    public void setOutcome(OutcomeEntity outcome) {
        this.outcome = outcome;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> SHIFT));
        result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
        result = prime * result + ((validUntil == null) ? 0 : validUntil.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        OutcomeOddEntity other = (OutcomeOddEntity) obj;
        return this.id == other.id;
    }

}
