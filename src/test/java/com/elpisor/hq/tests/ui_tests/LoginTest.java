package com.elpisor.hq.tests.ui_tests;

import com.codeborne.selenide.ElementsCollection;
import com.elpisor.hq.manager.page_objects.Header;
import com.elpisor.hq.manager.page_objects.LoginPage;
import com.elpisor.hq.model.UserCreds;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

public class LoginTest extends UiTestBase {

    LoginPage loginPage;

    private final static String LOGIN_WITH_POSITIVE_DATA_FILE = "loginWithPositiveData.csv";
    private final static String LOGIN_WITHOUT_CREDS_FILE = "loginWithoutCreds.csv";
    private final static String LOGIN_WITH_WRONG_EMAIL_FILE = "loginWithWrongEmail.csv";

    @DataProvider
    public Iterator<Object[]> loginWithPositiveData() throws IOException {
        return app.getData().getUserCredsFromFile(RESOURCES_URL + LOGIN_WITH_POSITIVE_DATA_FILE);
    }

    @DataProvider
    public Iterator<Object[]> loginWithoutCreds() throws IOException {
        return app.getData().getUserCredsFromFile(RESOURCES_URL + LOGIN_WITHOUT_CREDS_FILE);
    }

    @DataProvider
    public Iterator<Object[]> loginWithWrongEmail() throws IOException {
        return app.getData().getUserCredsFromFile(RESOURCES_URL + LOGIN_WITH_WRONG_EMAIL_FILE);
    }

    @BeforeMethod
    public void initPageObjects() {
        Header header = new Header();
        header.openHeader();
        loginPage = header.clickLogin();
    }

    @Test(dataProvider = "loginWithPositiveData")
    public void testLoginWithValidCreds(UserCreds userCreds) {
        loginPage.fillLoginForm(userCreds);
        ElementsCollection errors = loginPage.getErrors();
        errors.shouldBe(empty);
        loginPage.getSubmit().shouldBe(enabled);
    }

    @Test(dataProvider = "loginWithoutCreds")
    public void testLoginWithoutCreds(UserCreds userCreds) {
        loginPage.fillLoginForm(userCreds);
        ElementsCollection errors = loginPage.getErrors();
        int errorsNumber = 0;
        if (userCreds.getEmail() == null) {
            loginPage.getError("Enter email").shouldBe(visible);
            errorsNumber++;
        }
        if (userCreds.getPassword() == null) {
            loginPage.getError("Enter password").shouldBe(visible);
            errorsNumber++;
        }
        errors.shouldHave(size(errorsNumber));
        loginPage.getSubmit().shouldNotBe(enabled);
    }

    @Test(dataProvider = "loginWithWrongEmail")
    public void testLoginWithWrongEmail(UserCreds userCreds) {
        loginPage.fillLoginForm(userCreds);
        ElementsCollection errors = loginPage.getErrors();
        loginPage.getError("Wrong email pattern").shouldBe(visible);
        errors.shouldHave(size(1));
        loginPage.getSubmit().shouldNotBe(enabled);
    }

    @Test(enabled = false)
    public void testLoginWithWrongPassword() {
        loginPage.fillLoginForm(UserCreds.builder().email("a@a").password("1").build());
        ElementsCollection errors = loginPage.getErrors();
        loginPage.getError("Wrong password pattern").shouldBe(visible);
        errors.shouldHave(size(1));
        loginPage.getSubmit().shouldNotBe(enabled);
    }

}
