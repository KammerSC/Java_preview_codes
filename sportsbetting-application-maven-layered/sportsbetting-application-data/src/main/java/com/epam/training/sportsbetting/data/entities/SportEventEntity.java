package com.epam.training.sportsbetting.data.entities;

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.epam.training.sportsbetting.data.util.SportEventType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**The object representation of the SPORTEVENT database table.
 * */
@Entity
@Table(name = "SportEvent")
public class SportEventEntity {
    private static final int SHIFT = 32;

    @TableGenerator(name = "GEN_EVENT")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_EVENT")
    @Column(name = "ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "START_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start;

    @Column(name = "END_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private SportEventType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "event")
    private List<BetEntity> bets = new ArrayList<BetEntity>();

    public SportEventEntity() {
    }

    public SportEventEntity(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public List<BetEntity> getBets() {
        return bets;
    }

    public void setBets(List<BetEntity> bets) {
        this.bets = bets;
    }

    public SportEventType getType() {
        return type;
    }

    public void setType(SportEventType type) {
        this.type = type;
    }

    /**Creates the relation between this event and the given bet.
     * @param bet 
     * */
    public void createRelationWithBet(BetEntity bet) {
        bets.add(bet);
        bet.setEvent(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + (int) (id ^ (id >>> SHIFT));
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SportEventEntity other = (SportEventEntity) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "SportEventEntity [id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", type=" + type + "]";
    }

}
