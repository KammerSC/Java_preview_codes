package com.epam.training.sportsbetting.presentation.web.datasource;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import com.epam.training.sportsbetting.business.dao.EventDataSource;
import com.epam.training.sportsbetting.business.dao.PlayerDao;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.presentation.web.page.home.converter.WagerToWagerDesctransformer;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;
import com.epam.training.sportsbetting.presentation.web.page.home.model.WagerDescription;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerForm;

/** Implementation of {@link PlayerAccessInterface}.
 * */
public class PlayerAccessInterfaceImpl implements PlayerAccessInterface {

    private PlayerDao playerDao;
    private EventDataSource eventDao;
    private ConversionService conversionService;
    private WagerToWagerDesctransformer wagerTransformer;

    //test missing
    @Override
    public PlayerDetails getPlayerDetailsByEmail(String email) {
        Player player = playerDao.getPlayerByEmail(email);
        return conversionService.convert(player, PlayerDetails.class);
    }

    //test missing
    @Override
    public void savePlayerChanges(String email, PlayerDetails details) {
        Player player = playerDao.getPlayerByEmail(email);
        if (isSaveable(player, details)) {
            setPlayerChanges(player, details);
            playerDao.savePlayerChanges(player);
        }
    }

    //test missing
    @Override
    public void deleteWager(long id, String email) {
        playerDao.deleteWager(id, email);
    }

    //test missing
    @Override
    public List<WagerDescription> getWagerDescriptionsOfPlayer(String email) {
        List<Wager> wagers = playerDao.getPlayerByEmail(email).getWagers();
        List<WagerDescription> descriptions = new ArrayList<WagerDescription>();
        wagers.forEach(wager -> descriptions.add(transformWager(wager)));
        return descriptions;
    }

    @Override
    public void saveWager(WagerForm form, String email) {
        Player player = playerDao.getPlayerByEmail(email);
        Wager wager = new Wager();
        Outcome outcome = eventDao.getOutcomeById(form.getOutcomeId());
        OutcomeOdd odd = eventDao.getValidOddOfOutcome(outcome, LocalDateTime.now());
        setWagerFields(form, player, wager, odd);
        playerDao.saveNewWager(wager);
    }

    @Autowired
    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Autowired
    public void setEventDao(EventDataSource eventDao) {
        this.eventDao = eventDao;
    }

    @Autowired
    public void setWagerTransformer(WagerToWagerDesctransformer wagerTransformer) {
        this.wagerTransformer = wagerTransformer;
    }

    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    //test missing
    protected boolean isSaveable(Player player, PlayerDetails details) {
        return details.getId() == player.getId() && details.getId() == player.getId();
    }

    //test missing
    protected void setPlayerChanges(Player player, PlayerDetails details) {
        player.setAccountNumber(details.getAccountNumber());
        player.setBalance(details.getBalance());
        player.setBirth(LocalDateTime.of(details.getDate(), LocalTime.NOON));
        player.setCurrency(details.getCurrency());
        player.setName(details.getName());
    }

    //test missing
    protected WagerDescription transformWager(Wager wager) {
        OutcomeOdd odd = eventDao.getOutcomeOddById(wager.getOddId());
        return wagerTransformer.toWagerDescription(wager, odd);
    }

    private void setWagerFields(WagerForm form, Player player, Wager wager, OutcomeOdd odd) {
        wager.setAmount(form.getAmount());
        wager.setTimestampCreated(LocalDateTime.now());
        wager.setPlayer(player);
        wager.setCurrency(player.getCurrency());
        wager.setOddId(odd.getId());
    }

}
