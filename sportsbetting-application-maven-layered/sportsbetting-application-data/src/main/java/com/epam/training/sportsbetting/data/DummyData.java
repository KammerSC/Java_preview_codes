package com.epam.training.sportsbetting.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.epam.training.sportsbetting.data.crud.SportEventEntityRepository;
import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeOddEntity;
import com.epam.training.sportsbetting.data.entities.SportEventEntity;
import com.epam.training.sportsbetting.data.util.BetType;
import com.epam.training.sportsbetting.data.util.SportEventType;

public class DummyData {

    private SportEventEntityRepository repo;

    public void setRepo(SportEventEntityRepository repo) {
        this.repo = repo;
    }

    public void dummyEventOne() {
        //Event 
        SportEventEntity event = new SportEventEntity();
        event.setTitle("AvsB");
        event.setStart(LocalDateTime.of(2020, 1, 10, 10, 0));
        event.setEnd(LocalDateTime.of(2020, 1, 11, 10, 0));
        event.setType(SportEventType.FOOTTBALL);

        //Bet A team wins
        BetEntity bet = new BetEntity();
        event.createRelationWithBet(bet);
        bet.setDescription("A");
        bet.setType(BetType.WINNER);

        //Outcome A team win result 0
        OutcomeEntity outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("0");
        outcome.setWinner(true);
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(2), LocalDateTime.of(2020, 1, 10, 10, 0), LocalDateTime.of(2020, 1, 11, 10, 0)));

        //Outcome A team win result 1
        outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("1");
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(1), LocalDateTime.of(2020, 1, 10, 10, 0), LocalDateTime.of(2020, 1, 11, 10, 0)));

        //Bet B team wins
        bet = new BetEntity();
        event.createRelationWithBet(bet);
        bet.setDescription("B");
        bet.setType(BetType.WINNER);

        //Outcome B team win result 0
        outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("0");
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(2), LocalDateTime.of(2020, 1, 10, 10, 0), LocalDateTime.of(2020, 1, 11, 10, 0)));

        //Outcome B team win result 1
        outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("1");
        outcome.setWinner(true);
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(5), LocalDateTime.of(2020, 1, 10, 10, 0), LocalDateTime.of(2020, 1, 11, 10, 0)));

        repo.save(event);
    }

    public void dummyEventTwo() {
        SportEventEntity event = new SportEventEntity();
        event.setTitle("CvsD");
        event.setStart(LocalDateTime.of(2020, 2, 20, 13, 0));
        event.setEnd(LocalDateTime.of(2020, 5, 25, 13, 0));
        event.setType(SportEventType.TENNIS);

        BetEntity bet = new BetEntity();
        event.createRelationWithBet(bet);
        bet.setDescription("Sets");
        bet.setType(BetType.NUMBER_OF_SETS);

        OutcomeEntity outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("0");
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(2), LocalDateTime.of(2020, 1, 20, 10, 0), LocalDateTime.of(2020, 5, 25, 10, 0)));

        outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("1");
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(3), LocalDateTime.of(2020, 1, 20, 10, 0), LocalDateTime.of(2020, 5, 25, 10, 0)));

        outcome = new OutcomeEntity();
        bet.createRelationWithOutcome(outcome);
        outcome.setDescription("1");
        outcome.createRelationWithOdd(
                new OutcomeOddEntity(BigDecimal.valueOf(4), LocalDateTime.of(2020, 1, 20, 10, 0), LocalDateTime.of(2020, 5, 25, 10, 0)));

        repo.save(event);
    }
}
