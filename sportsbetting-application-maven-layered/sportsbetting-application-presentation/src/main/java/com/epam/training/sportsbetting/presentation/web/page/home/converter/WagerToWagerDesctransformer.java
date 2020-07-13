package com.epam.training.sportsbetting.presentation.web.page.home.converter;

import org.springframework.beans.factory.annotation.Value;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.presentation.web.page.home.model.WagerDescription;

/** Transforms a {@link Wager} to {@link WagerDescription}.
 * */
public class WagerToWagerDesctransformer {
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final String UNPROCESSED = "-";

    @Value("${mapping.deleteWagerBasePath}")
    private String deleteBase;

    /** Transforms a {@link Wager} to {@link WagerDescription}.
     * @param wager 
     * @param odd ( {@link OutcomeOdd} ) of the wager, need for reaching the outcome ( {@link Outcome} ),
     *  bet ( {@link Bet} ), and event ( {@link SportEvent} ) object.
     * @return {@link WagerDescription}
     * @throws IllegalArgumentException if 
     * */
    public WagerDescription toWagerDescription(Wager wager, OutcomeOdd odd) {
        checkValidParameters(wager, odd);
        return transformToDescription(wager, odd);
    }

    /** Set the given {@link WagerDescription} win and processed fields.
     * @param desc 
     * @param wager to be the base of settings
     * */
    protected void setProcessedAndWin(WagerDescription desc, Wager wager) {
        if (wager.isProcessed()) {
            desc.setProcessed(YES);
            if (wager.isWin()) {
                desc.setWin(YES);
            } else {
                desc.setWin(NO);
            }
        } else {
            desc.setProcessed(UNPROCESSED);
            desc.setWin(UNPROCESSED);
        }
    }

    protected void setDeleteBase(String deleteBase) {
        this.deleteBase = deleteBase;
    }

    private void checkValidParameters(Wager wager, OutcomeOdd odd) {
        if(wager == null || odd == null || wager.getOddId() != odd.getId()) {
            throw new IllegalArgumentException();
        }

    }

    private WagerDescription transformToDescription(Wager wager, OutcomeOdd odd) {
        WagerDescription result = new WagerDescription();
        result.setId(wager.getId());
        result.setWagerAmount(wager.getAmount().toString());
        setProcessedAndWin(result, wager);
        result.setOutcomeOdd(odd.getValue().toString());
        Outcome outcome = odd.getOutcome();
        result.setOutcomeValue(outcome.getDescription());
        Bet bet = outcome.getBet();
        result.setBetType(bet.getType().toString());
        SportEvent event = bet.getEvent();
        result.setEventType("asd");
        result.setEventTitle(event.getTitle());
        result.setDeleteURL(deleteBase + wager.getId());
        return result;
    }
}
