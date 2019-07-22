package com.elpisor.hq.manager.page_objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class PageObject {
    WebDriver driver;

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if(text==null)
            text="";
        driver.findElement(locator).sendKeys(text);
     }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementActive(By locator){
        return driver.findElement(locator).isEnabled()? true : false;
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected List<String> getListOfElements(By locator) {
        List<WebElement> list = driver.findElements(locator);
        if (list.isEmpty())
            return new ArrayList<>();
        return list.stream().map(e -> e.getText()).collect(Collectors.toList());
    }

}
