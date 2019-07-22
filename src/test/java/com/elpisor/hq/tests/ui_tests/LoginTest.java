package com.elpisor.hq.tests.ui_tests;

import com.elpisor.hq.manager.page_objects.Header;
import com.elpisor.hq.manager.page_objects.LoginPage;
import com.elpisor.hq.model.UserCreds;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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
        app.goTo().openSite(app.getProperties().getProperty("web.baseUrl"));
        Header header = new Header(app.getDriver());
        loginPage = header.clickLogin();
    }

    @Test(dataProvider="loginWithoutCreds")
    public void testLoginWithoutCreds(UserCreds userCreds) {
        loginPage.fillLoginForm(userCreds);
        List<String> errors = loginPage.getListOfErrors();
        int errorsNumber=0;
        if(userCreds.getEmail()==null){
            assertTrue(errors.contains("Enter email"));
            errorsNumber++;
        }
        if(userCreds.getPassword()==null){
            assertTrue(errors.contains("Enter password"));
            errorsNumber++;
        }
        assertTrue(errors.size() == errorsNumber);
        assertFalse(loginPage.isSubmitActive());
    }

    @Test(dataProvider = "loginWithWrongEmail")
    public void testLoginWithWrongEmail(UserCreds userCreds) {
        loginPage.fillLoginForm(userCreds);
        List<String> errors = loginPage.getListOfErrors();
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains("Wrong email pattern"));
        assertFalse(loginPage.isSubmitActive());
    }

    @Test(enabled = false)
    public void testLoginWithWrongPassword() {
        loginPage.fillLoginForm(UserCreds.builder().email("a@a").password("1").build());
        List<String> errors = loginPage.getListOfErrors();
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains("Wrong password pattern"));
        assertFalse(loginPage.isSubmitActive());
    }

    @Test(dataProvider = "loginWithPositiveData")
    public void testLoginWithValidCreds(UserCreds userCreds) {
        loginPage.fillLoginForm(userCreds);
        List<String> errors = loginPage.getListOfErrors();
        assertTrue(errors.size() == 0);
//        loginPage.clickSubmit();
        assertTrue(loginPage.isSubmitActive());
    }
}
