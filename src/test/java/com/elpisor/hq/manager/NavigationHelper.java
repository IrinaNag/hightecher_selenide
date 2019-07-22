package com.elpisor.hq.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    private static final String LOGIN = "/login";
    private static final String REGISTRATION = "/registration";

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void openSite(String url) {
        driver.get(url);
    }

    public void openLoginPage(String url){
        driver.get(url+LOGIN);
    }

    public void openRegistrationPage(String url){
        driver.get(url+REGISTRATION);
    }

}