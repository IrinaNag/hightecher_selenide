package com.elpisor.hq.manager.page_objects;

import com.elpisor.hq.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class RegistrationPage extends PageObject {

    private By username = By.cssSelector("input#username");
    private By name = By.cssSelector("input#name");
    private By surname = By.cssSelector("input#surname");
    private By email = By.cssSelector("input#email");
    private By phone = By.cssSelector("input#phone");
    private By password = By.cssSelector("input#password");
    private By password_confirmation = By.cssSelector("input#password_confirmation");
    private By errors = By.xpath("//div[@class='alert alert-danger']");
    private By body = By.xpath("//body//form");
    private By submit = By.xpath("//body//form//button");
    private By fieldNames = By.cssSelector("label");


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage fillRegistrationForm(User user) {
        type(username, user.getUsername());
        type(name, user.getName());
        type(surname, user.getSurname());
        type(email, user.getEmail());
        type(phone, user.getPhone());
        type(password, user.getPassword());
        type(password_confirmation, user.getPassword_confirmation());
        click(body);
        return this;
    }

    public void clickSubmit() {
        click(submit);
    }

    public boolean isSubmitActive() {
        return isElementActive(submit);
    }

    public List<String> getListOfErrors() {
        return getListOfElements(errors);
    }

    private boolean isThisTheRegistrationPage() {
        return getCurrentUrl().contains("/registration");
    }

}
