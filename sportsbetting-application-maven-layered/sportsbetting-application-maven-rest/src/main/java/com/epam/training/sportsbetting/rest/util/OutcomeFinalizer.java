package com.epam.training.sportsbetting.rest.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.training.sportsbetting.data.crud.BetRepository;
import com.epam.training.sportsbetting.data.crud.OutcomeRepository;
import com.epam.training.sportsbetting.data.crud.PlayerRepository;
import com.epam.training.sportsbetting.data.crud.WagerRepository;
import com.epam.training.sportsbetting.data.entities.BetEntity;
import com.epam.training.sportsbetting.data.entities.OutcomeEntity;
import com.epam.training.sportsbetting.data.entities.PlayerEntity;
import com.epam.training.sportsbetting.data.entities.WagerEntity;

public class OutcomeFinalizer {

    private BetRepository betrepo;
    private OutcomeRepository outcomeRepo;
    private WagerRepository wagerRepo;
    private PlayerRepository playerRepo;

    @Autowired
    public void setBetrepo(BetRepository betrepo) {
        this.betrepo = betrepo;
    }

    @Autowired
    public void setOutcomeRepo(OutcomeRepository outcomeRepo) {
        this.outcomeRepo = outcomeRepo;
    }

    @Autowired
    public void setWagerRepo(WagerRepository wagerRepo) {
        this.wagerRepo = wagerRepo;
    }

    @Autowired
    public void setPlayerRepo(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    public void finalizeOutcomes(ListWrapper wrapper) {
        wrapper.getIds().forEach(id -> process(id));
    }

    protected void process(long id) {
        OutcomeEntity outcome = outcomeRepo.findById(id);
        outcome.setWinner(true);
        BetEntity bet = outcome.getBet();
        bet.getOutcomes().forEach(actualOutcome -> processOutcome(actualOutcome));
        betrepo.save(bet);
    }

    protected void processOutcome(OutcomeEntity outcome) {
        List<WagerEntity> wagers = listWagers(outcome);
        if (outcome.isWinner()) {
            processWiningWagers(wagers);
        } else {
            processLosingWagers(wagers);
        }

    }

    protected List<WagerEntity> listWagers(OutcomeEntity outcome) {
        List<WagerEntity> wagers = new ArrayList<WagerEntity>();
        outcome.getOdds().forEach(odd -> wagers.addAll(wagerRepo.findByOddId(odd.getId())));
        return wagers;
    }

    protected void processWiningWagers(List<WagerEntity> wagers) {
        wagers.forEach(wager -> {
            processAWinningWager(wager);
        });
    }

    protected void processAWinningWager(WagerEntity wager) {
        wager.setProcesed(true);
        wager.setWin(true);
        PlayerEntity player = wager.getPlayer();
        BigDecimal newBalance = calculateNewBalance(wager, player);
        player.setBalance(newBalance);
        playerRepo.save(wager.getPlayer());
    }

    protected BigDecimal calculateNewBalance(WagerEntity wager, PlayerEntity player) {
        return player.getBalance().add(wager.getOdd().getValue().multiply(wager.getAmount()));
    }

    protected void processLosingWagers(List<WagerEntity> wagers) {
        wagers.forEach(wager -> {
            wager.setProcesed(true);
            wagerRepo.save(wager);
        });
    }

}
