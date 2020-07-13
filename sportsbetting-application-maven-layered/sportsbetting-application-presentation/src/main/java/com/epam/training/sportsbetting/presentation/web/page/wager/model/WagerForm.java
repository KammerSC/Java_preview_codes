package com.epam.training.sportsbetting.presentation.web.page.wager.model;

import java.math.BigDecimal;
import java.util.List;

/** Model of the wager form.
 * */
public class WagerForm {
    private BigDecimal amount;
    private long outcomeId;
    private List<OutcomeDescription> outcomes;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public List<OutcomeDescription> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<OutcomeDescription> outcomes) {
        this.outcomes = outcomes;
    }

}
