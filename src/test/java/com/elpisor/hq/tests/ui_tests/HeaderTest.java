package com.elpisor.hq.tests.ui_tests;

import com.elpisor.hq.manager.page_objects.Header;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class HeaderTest extends UiTestBase {

    Header header;

    @BeforeMethod
    public void initPageObjects() {
        app.goTo().openSite(app.getProperties().getProperty("web.baseUrl"));
        header = new Header(app.getDriver());
    }

    @Test
    public void testGotoLoginPageFromMainPage() {
        header.clickLogin();
        assertTrue(header.isThisTheLoginPage());
    }

    @Test
    public void testGotoLoginPageFromRegistrationPage() {
        header.clickRegistration();
        header.clickLogin();
        assertTrue(header.isThisTheLoginPage());
    }

    @Test
    public void testGotoLoginPageFromLoginPage() {
        header.clickLogin();
        header.clickLogin();
        assertTrue(header.isThisTheLoginPage());
    }

    @Test
    public void testGotoRegistrationPageFromMainPage() {
        header.clickRegistration();
        assertTrue(header.isThisTheRegistrationPage());
    }

    @Test
    public void testGotoRegistrationPageFromRegistrationPage() {
        header.clickRegistration();
        header.clickRegistration();
        assertTrue(header.isThisTheRegistrationPage());
    }

    @Test
    public void testGotoRegistrationPageFromLoginPage() {
        header.clickLogin();
        header.clickRegistration();
        assertTrue(header.isThisTheRegistrationPage());
    }

}
