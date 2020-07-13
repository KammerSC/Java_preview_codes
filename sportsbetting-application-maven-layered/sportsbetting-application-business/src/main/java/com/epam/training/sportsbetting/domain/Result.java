package com.epam.training.sportsbetting.domain;

import java.util.List;
/** Hold a list of winning {@link Outcome}s.
 * */
public class Result {
    private List<Outcome> winningOutcomes;

    public List<Outcome> getWinningOutcomes() {
        return winningOutcomes;
    }

    public void setWinningOutcomes(List<Outcome> winningOutcomes) {
        this.winningOutcomes = winningOutcomes;
    }

}
