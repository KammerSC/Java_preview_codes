package com.epam.training.sportsbetting.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/** The object representation of the OUTCOME database table.
 * */
@Entity
@Table(name = "Outcome")
public class OutcomeEntity {
    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_OUTCOME")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_OUTCOME")
    @Column(name = "ID")
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    private BetEntity bet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "outcome")
    private List<OutcomeOddEntity> odds = new ArrayList<OutcomeOddEntity>();

    @Column(name = "WINNER")
    private boolean winner;

    public OutcomeEntity() {
    }

    /**Creates the relation between this outcome and the given odd.
     * @param odd 
     * */
    public void createRelationWithOdd(OutcomeOddEntity odd) {
        odds.add(odd);
        odd.setOutcome(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BetEntity getBet() {
        return bet;
    }

    public void setBet(BetEntity bet) {
        this.bet = bet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OutcomeOddEntity> getOdds() {
        return odds;
    }

    public void setOdds(List<OutcomeOddEntity> odds) {
        this.odds = odds;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bet == null) ? 0 : bet.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        OutcomeEntity other = (OutcomeEntity) obj;
        return this.id == other.id;
    }

}
