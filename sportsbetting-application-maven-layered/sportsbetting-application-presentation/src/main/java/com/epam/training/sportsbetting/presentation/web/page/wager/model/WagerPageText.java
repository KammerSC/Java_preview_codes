package com.epam.training.sportsbetting.presentation.web.page.wager.model;

import com.epam.training.sportsbetting.presentation.web.text.HeaderText;

/** Provides texts to the wager page.
 * */
public class WagerPageText {
    private HeaderText header;
    private String pleaseSelect;
    private String outcome;
    private String amount;
    private String save;
    private String cancel;

    public String getPleaseSelect() {
        return pleaseSelect;
    }

    public void setPleaseSelect(String pleaseSelect) {
        this.pleaseSelect = pleaseSelect;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public HeaderText getHeader() {
        return header;
    }

    public void setHeader(HeaderText header) {
        this.header = header;
    }

}
