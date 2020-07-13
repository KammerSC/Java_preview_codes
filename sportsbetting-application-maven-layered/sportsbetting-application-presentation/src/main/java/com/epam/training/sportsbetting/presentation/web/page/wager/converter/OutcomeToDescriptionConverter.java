package com.epam.training.sportsbetting.presentation.web.page.wager.converter;

import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.OutcomeDescription;

/** Convert an outcome to a selectable option.
 * */
public class OutcomeToDescriptionConverter {

    /** Convert the given {@link Outcome} and it's valid {@link OutcomeOdd} to a {@link OutcomeDescription}.
     * @param source {@link Outcome}
     * @param odd {@link OutcomeOdd}
     * @return {@link OutcomeDescription} or null if any given parameter is null.
     * */
    public OutcomeDescription convert(Outcome source, OutcomeOdd odd) {
        OutcomeDescription target = null;
        if (source != null && odd != null) {
            target = new OutcomeDescription();
            target.setDescription(source.getDescription() + " - odd: " + odd.getValue());
            target.setId(source.getId());
        }
        return target;
    }

}
