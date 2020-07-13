package com.epam.training.sportsbetting.presentation.web.page.home.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.presentation.web.page.home.model.PlayerDetails;

/** Converter that convert domain class {@link Player} to {@link PlayerDetails}.
 * */
@Service
public class PlayerToDetailsConverter implements Converter<Player, PlayerDetails> {
    @Override
    public PlayerDetails convert(Player source) {
        PlayerDetails target = null;
        if (source != null) {
            target = new PlayerDetails();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setBalance(source.getBalance());
            target.setDate(source.getBirth().toLocalDate());
            target.setCurrency(source.getCurrency());
            target.setAccountNumber(source.getAccountNumber());
            target.setVersion(source.getVersion());
        }
        return target;
    }

}
