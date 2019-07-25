package com.elpisor.hq.page_objects;

import com.codeborne.selenide.ElementsCollection;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@NoArgsConstructor
public class PageObject {

    protected void click(By locator) {
        $(locator).click();
    }

    protected void type(By locator, String text) {
        if (text == null)
            text = "";
        $(locator).sendKeys(text);
    }

    protected ElementsCollection getCollectionOfElements(By locator) {
        return $$(locator);
    }
}
