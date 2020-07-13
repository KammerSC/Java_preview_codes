package com.epam.training.sportsbetting.presentation.web.text;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.epam.training.sportsbetting.presentation.web.page.bet.model.BetPageText;
import com.epam.training.sportsbetting.presentation.web.page.event.model.EventPageText;
import com.epam.training.sportsbetting.presentation.web.page.home.model.HomePageText;
import com.epam.training.sportsbetting.presentation.web.page.login.modell.LoginPageText;
import com.epam.training.sportsbetting.presentation.web.page.wager.model.WagerPageText;

/** Provides localized texts to different pages.
 * */
public class PageTextProvider {

    @Value("${mapping.engParam}")
    private String engParam;
    @Value("${mapping.hunParam}")
    private String hunParam;
    @Value("${mapping.betBasePath}")
    private String betBasePath;
    @Value("${mapping.wagerBasePath}")
    private String wagerBasePath;

    private MessageSource msgSource;

    //test needed
    /** Creates a {@link LoginPageText} according to a context's locale.
     * @return {@link LoginPageText} 
     * */
    public LoginPageText createLoginText() {
        Locale locale = LocaleContextHolder.getLocale();
        LoginPageText text = new LoginPageText();
        text.setDescription(msgSource.getMessage("login.description", null, locale));
        text.setLogin(msgSource.getMessage("login.login", null, locale));
        text.setOrText(msgSource.getMessage("login.orText", null, locale));
        text.setRegister(msgSource.getMessage("login.register", null, locale));
        text.setTitle(msgSource.getMessage("login.title", null, locale));
        text.setToStart(msgSource.getMessage("login.toStart", null, locale));
        text.setLoginButton(msgSource.getMessage("login.loginButton", null, locale));
        text.setPasswordField(msgSource.getMessage("login.passwordField", null, locale));
        return text;
    }

    //test needed
    /** Creates a {@link HomePageText} according to the context's locale.
     * @return {@link HeaderText} 
     * */
    public HomePageText createHomepageText() {
        Locale locale = LocaleContextHolder.getLocale();
        HomePageText text = new HomePageText();
        text.setHeader(createHeaderText());
        setPlayerFormText(locale, text);
        setWagerText(locale, text);
        return text;
    }

    //test
    /** Creates a {@link EventPageText} according to the context's locale.
     * @return {@link EventPageText}
     * */
    public EventPageText createEventPageText() {
        Locale locale = LocaleContextHolder.getLocale();
        EventPageText text = new EventPageText();
        text.setHeader(createHeaderText());
        text.setBet(msgSource.getMessage("event.bet", null, locale));
        text.setEventDate(msgSource.getMessage("event.eventDate", null, locale));
        text.setEvents(msgSource.getMessage("event.events", null, locale));
        text.setEventTitle(msgSource.getMessage("event.eventTitle", null, locale));
        text.setEventType(msgSource.getMessage("event.eventType", null, locale));
        return text;
    }

    //test
    /** Creates a {@link BetPageText} according to the context's locale.
     * @return {@link BetPageText}
     * @param id of the event on the bet page
     * */
    public BetPageText createBetPageText(long id) {
        Locale locale = LocaleContextHolder.getLocale();
        BetPageText text = new BetPageText();
        HeaderText header = createHeaderText();
        setHeaderLangsWithParam(locale, header, betBasePath + id);
        text.setHeader(header);
        text.setBets(msgSource.getMessage("bet.bets", null, locale));
        text.setBetType(msgSource.getMessage("bet.betType", null, locale));
        text.setDescription(msgSource.getMessage("bet.description", null, locale));
        text.setNewWager(msgSource.getMessage("bet.newWager", null, locale));
        text.setSelectedEvent(msgSource.getMessage("bet.selectedEvent", null, locale));
        return text;
    }
    /** Creates a {@link WagerPageText} according to the context's locale.
     * @return {@link WagerPageText}
     * @param id of the bet on the wager page
     * */
    public WagerPageText createWagerPageText(long id) {
        Locale locale = LocaleContextHolder.getLocale();
        WagerPageText text = new WagerPageText();
        HeaderText header = createHeaderText();
        setHeaderLangsWithParam(locale, header, wagerBasePath + id);
        text.setHeader(header);
        text.setAmount(msgSource.getMessage("wager.amount", null, locale));
        text.setCancel(msgSource.getMessage("wager.cancel", null, locale));
        text.setOutcome(msgSource.getMessage("wager.outcome", null, locale));
        text.setPleaseSelect(msgSource.getMessage("wager.pleaseSelect", null, locale));
        text.setSave(msgSource.getMessage("wager.save", null, locale));
        return text;
    }

    @Autowired
    public void setMsgSource(MessageSource msgSource) {
        this.msgSource = msgSource;
    }

    //test needed
    /** Creates a {@link HeaderText} according to the context's locale.
     * @return {@link HeaderText} 
     * */
    protected HeaderText createHeaderText() {
        Locale locale = LocaleContextHolder.getLocale();
        HeaderText text = new HeaderText();
        setHeaderLangs(locale, text);
        text.setEvents(msgSource.getMessage("header.event", null, locale));
        text.setHome(msgSource.getMessage("header.home", null, locale));
        text.setLanguage(msgSource.getMessage("header.language", null, locale));
        text.setLogout(msgSource.getMessage("header.logout", null, locale));
        return text;
    }

    protected void setEngParam(String engParam) {
        this.engParam = engParam;
    }

    protected void setHunParam(String hunParam) {
        this.hunParam = hunParam;
    }

    protected void setBetBasePath(String betBasePath) {
        this.betBasePath = betBasePath;
    }

    protected void setWagerBasePath(String wagerBasePath) {
        this.wagerBasePath = wagerBasePath;
    }

    private void setWagerText(Locale locale, HomePageText text) {
        text.setBetType(msgSource.getMessage("home.betType", null, locale));
        text.setEventTitle(msgSource.getMessage("home.eventTitle", null, locale));
        text.setEventType(msgSource.getMessage("home.eventType", null, locale));
        text.setOutcomeOdd(msgSource.getMessage("home.outcomeOdd", null, locale));
        text.setOutcomeValue(msgSource.getMessage("home.outcomeValue", null, locale));
        text.setProcessed(msgSource.getMessage("home.processed", null, locale));
        text.setRemove(msgSource.getMessage("home.remove", null, locale));
        text.setWager(msgSource.getMessage("home.wager", null, locale));
        text.setWagerAmount(msgSource.getMessage("home.wagerAmount", null, locale));
        text.setWinner(msgSource.getMessage("home.winner", null, locale));
    }

    private void setPlayerFormText(Locale locale, HomePageText text) {
        text.setAccountDetails(msgSource.getMessage("home.accountDetails", null, locale));
        text.setAccountNumber(msgSource.getMessage("home.accountNumber", null, locale));
        text.setBalance(msgSource.getMessage("home.balance", null, locale));
        text.setCurrency(msgSource.getMessage("home.currency", null, locale));
        text.setDateOfBirth(msgSource.getMessage("home.dateOfBirth", null, locale));
        text.setName(msgSource.getMessage("home.name", null, locale));
        text.setSave(msgSource.getMessage("home.save", null, locale));
    }

    private void setHeaderLangs(Locale locale, HeaderText header) {
        header.setEngUrl("?" + engParam);
        header.setHunUrl("?" + hunParam);
    }

    private void setHeaderLangsWithParam(Locale locale, HeaderText header, String param) {
        header.setEngUrl(param + "&" + engParam);
        header.setHunUrl(param + "&" + hunParam);
    }

}
