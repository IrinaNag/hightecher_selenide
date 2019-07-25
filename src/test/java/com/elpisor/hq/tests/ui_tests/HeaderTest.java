package com.elpisor.hq.tests.ui_tests;

import com.elpisor.hq.manager.page_objects.Header;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class HeaderTest extends UiTestBase {

    Header header;

    @BeforeMethod
    public void initPageObjects() {
        header = new Header();
    }

    @Test
    public void testGotoLoginPageFromMainPage() {
        header.openHeader()
                .clickLogin();
        assertTrue(header.isThisTheLoginPage());
    }

    @Test
    public void testGotoLoginPageFromRegistrationPage() {
        header.openHeader()
                .clickRegistration();
        header.clickLogin();
        assertTrue(header.isThisTheLoginPage());
    }

    @Test
    public void testGotoLoginPageFromLoginPage() {
        header.openHeader()
                .clickLogin();
        header.clickLogin();
        assertTrue(header.isThisTheLoginPage());
    }

    @Test
    public void testGotoRegistrationPageFromMainPage() {
        header.openHeader()
                .clickRegistration();
        assertTrue(header.isThisTheRegistrationPage());
    }

    @Test
    public void testGotoRegistrationPageFromRegistrationPage() {
        header.openHeader()
                .clickRegistration();
        header.clickRegistration();
        assertTrue(header.isThisTheRegistrationPage());
    }

    @Test
    public void testGotoRegistrationPageFromLoginPage() {
        header.openHeader()
                .clickLogin();
        header.clickRegistration();
        assertTrue(header.isThisTheRegistrationPage());
    }

}
