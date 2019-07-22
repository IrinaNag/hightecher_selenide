package com.elpisor.hq.manager.page_objects;

import com.elpisor.hq.model.UserCreds;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LoginPage extends PageObject {

    private By email= By.cssSelector("input#email");
    private By password=By.cssSelector("input#password");
    private By errors=By.xpath("//div[@class='alert alert-danger']");
    private By body=By.xpath("//body//form");
    private By submit=By.xpath("//body//form//button");
    private By fieldNames=By.cssSelector("label");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillLoginForm (UserCreds userCreds){
        type(email, userCreds.getEmail());
        type(password, userCreds.getPassword());
        click(body);
        return this;
    }

    public void clickSubmit(){//Change void to other page
        click(submit);
    }

    public boolean isSubmitActive(){
        return isElementActive(submit);
    }

    public List<String> getListOfErrors(){
        return getListOfElements(errors);
    }

    private boolean isThisTheLoginPage() {
        return getCurrentUrl().contains("/login");
    }


}
