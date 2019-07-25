package com.elpisor.hq.page_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.elpisor.hq.model.UserCreds;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class LoginPage extends PageObject {

    private By email = By.cssSelector("input#email");
    private By password = By.cssSelector("input#password");
    private String error = "//div[contains(text(),'%s')]";
    private By errors = By.xpath("//div[@class='alert alert-danger']");
    private By body = By.xpath("//body//form");
    private By submit = By.xpath("//body//form//button");
    private By fieldNames = By.cssSelector("label");

    public LoginPage fillLoginForm(UserCreds userCreds) {
        type(email, userCreds.getEmail());
        type(password, userCreds.getPassword());
        click(body);
        return this;
    }

    public void clickSubmit() {//Change void to other page
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
