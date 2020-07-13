package com.epam.training.sportsbetting.presentation.web.page.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.epam.training.sportsbetting.presentation.web.page.login.modell.LoginPageText;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

/** Get controller of the login page.
 * */
@Controller
public class LoginPageController {
    private static final String TEXT = "text";

    @Value("${mapping.loginView}")
    private String loginView;
    private PageTextProvider textProvider;

    /** Returns the login page's name.
     * @return the login page's name as string
     * */
    @GetMapping("${mapping.loginPath}")
    public String loginPage() {
        return loginView;
    }

    /** Provides localized text to the login page.
     * @return {@link LoginPageText}
     * */
    @ModelAttribute(TEXT)
    public LoginPageText providePageText() {
        return textProvider.createLoginText();
    }

    @Autowired
    public void setTextProvider(PageTextProvider textProvider) {
        this.textProvider = textProvider;
    }

    protected void setLoginView(String loginView) {
        this.loginView = loginView;
    }
}
