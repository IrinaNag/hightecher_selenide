package com.elpisor.hq.page_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.elpisor.hq.model.User;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class RegistrationPage extends PageObject {

    private By username = By.cssSelector("input#username");
    private By name = By.cssSelector("input#name");
    private By surname = By.cssSelector("input#surname");
    private By email = By.cssSelector("input#email");
    private By phone = By.cssSelector("input#phone");
    private By password = By.cssSelector("input#password");
    private By password_confirmation = By.cssSelector("input#password_confirmation");
    private String error = "//div[contains(text(),'%s')]";
    private By errors = By.xpath("//div[@class='alert alert-danger']");
    private By body = By.xpath("//body//form");
    private By submit = By.xpath("//body//form//button");
    private By fieldNames = By.cssSelector("label");

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

    public SelenideElement getSubmit() {
        return $(submit);
    }

    public SelenideElement getError(String text) {
        return $(By.xpath(format(error, text)));
    }

    public ElementsCollection getErrors() {
        return getCollectionOfElements(errors);
    }

}
