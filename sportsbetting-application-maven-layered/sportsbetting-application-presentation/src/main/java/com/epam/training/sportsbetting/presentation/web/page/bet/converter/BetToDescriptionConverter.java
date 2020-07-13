package com.epam.training.sportsbetting.presentation.web.page.bet.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetDescription;

/** Class that converts {@link Bet} to a {@link BetDescription}.
 * */
public class BetToDescriptionConverter implements Converter<Bet, BetDescription> {

    @Value("${mapping.wagerBasePath}")
    private String wagerBasePath;

    //test
    @Override
    public BetDescription convert(Bet source) {
        BetDescription result = null;
        if (source != null) {
            result = new BetDescription();
            result.setDescription(source.getDescription());
            result.setType(source.getType().toString());
            result.setUrl(wagerBasePath + source.getId());
        }
        return result;
    }

    protected void setWagerBasePath(String wagerBasePath) {
        this.wagerBasePath = wagerBasePath;
    }
}
