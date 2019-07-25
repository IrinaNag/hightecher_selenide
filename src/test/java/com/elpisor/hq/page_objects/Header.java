package com.elpisor.hq.page_objects;


import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;


public class Header extends PageObject {

    RegistrationPage registrationPage;
    LoginPage loginPage;

    private By registration = By.linkText("Registration");
    private By login = By.linkText("Login");

    public Header openHeader() {
        open("/");
        return this;
    }

    public LoginPage clickLogin() {
        if (!isThisTheLoginPage()) {
            loginPage = page(LoginPage.class);
        }
        click(login);
        return this.loginPage;
    }

    public RegistrationPage clickRegistration() {
        if (!isThisTheRegistrationPage()) {
            registrationPage = page(RegistrationPage.class);
        }
        click(registration);
        return this.registrationPage;
    }

    public boolean isThisTheLoginPage() {
        return url().contains("/login");
    }

    public boolean isThisTheRegistrationPage() {
        return url().contains("/registration");
    }

}
