package com.epam.training.sportsbetting.data.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.epam.training.sportsbetting.data.util.BetType;

/** The object representation of the BET database table.
 * */
@Entity
@Table(name = "Bet")
public class BetEntity {

    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_BET")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_BET")
    @Column(name = "ID")
    private long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    private SportEventEntity event;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private BetType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "bet")
    private List<OutcomeEntity> outcomes = new ArrayList<OutcomeEntity>();

    public BetEntity() {
    }

    /**Creates the relation between this bet and the given outcome.
     * @param outcome 
     * */
    public void createRelationWithOutcome(OutcomeEntity outcome) {
        outcomes.add(outcome);
        outcome.setBet(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportEventEntity getEvent() {
        return event;
    }

    public void setEvent(SportEventEntity event) {
        this.event = event;
    }

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }

    public List<OutcomeEntity> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<OutcomeEntity> outcomes) {
        this.outcomes = outcomes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + (int) (id ^ (id >>> SHIFT));
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BetEntity other = (BetEntity) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "BetEntity [id=" + id + ", description=" + description + ", event=" + event + ", type=" + type + "]";
    }

}
