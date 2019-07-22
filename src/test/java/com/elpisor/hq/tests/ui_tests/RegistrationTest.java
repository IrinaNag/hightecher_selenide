package com.elpisor.hq.tests.ui_tests;

import com.elpisor.hq.manager.page_objects.Header;
import com.elpisor.hq.manager.page_objects.RegistrationPage;
import com.elpisor.hq.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class RegistrationTest extends UiTestBase {

    RegistrationPage registrationPage;

    private final static String REGISTRATION_WITH_VALID_DATA_FILE = "registrationWithValidData.csv";
    private final static String REGISTRATION_WITHOUT_MANDATORY_FIELDS_FILE = "registrationWithoutMandatoryFields.csv";
    private final static String REGISTRATION_WITH_WRONG_USERNAME_FILE = "registrationWithWrongUsername.csv";
    private final static String REGISTRATION_WITH_WRONG_NAME_FILE = "registrationWithWrongName.csv";
    private final static String REGISTRATION_WITH_WRONG_SURNAME_FILE = "registrationWithWrongSurname.csv";
    private final static String REGISTRATION_WITH_WRONG_EMAIL_FILE = "registrationWithWrongEmail.csv";
    private final static String REGISTRATION_WITH_WRONG_PHONE_FILE = "registrationWithWrongPhone.csv";
    private final static String REGISTRATION_WITH_WRONG_PASSWORD_FILE = "registrationWithWrongPassword.csv";
    private final static String REGISTRATION_WITH_WRONG_PASSWORD_CONFIRM_FILE = "registrationWithWrongPasswordConfirm.csv";

    @DataProvider
    public Iterator<Object[]> registrationWithValidData() throws IOException {
        return app.getData().getUsersFromFile(RESOURCES_URL + REGISTRATION_WITH_VALID_DATA_FILE);
    }

    @DataProvider
    public Iterator<Object[]> registrationWithoutMandatoryFields() throws IOException {
        return app.getData().getUsersFromFile(RESOURCES_URL + REGISTRATION_WITHOUT_MANDATORY_FIELDS_FILE);
    }

    @DataProvider
    public Iterator<Object[]> registrationWithWrongEmail() throws IOException {
        return app.getData().getUsersFromFile(RESOURCES_URL + REGISTRATION_WITH_WRONG_EMAIL_FILE);
    }

    @DataProvider
    public Iterator<Object[]> registrationWithWrongPassword() throws IOException {
        return app.getData().getUsersFromFile(RESOURCES_URL + REGISTRATION_WITH_WRONG_PASSWORD_FILE);
    }

    @DataProvider
    public Iterator<Object[]> registrationWithWrongPasswordConfirm() throws IOException {
        return app.getData().getUsersFromFile(RESOURCES_URL + REGISTRATION_WITH_WRONG_PASSWORD_CONFIRM_FILE);
    }


    @BeforeMethod
    public void initPageObjects() {
        app.goTo().openSite(app.getProperties().getProperty("web.baseUrl"));
        Header header = new Header(app.getDriver());
        registrationPage = header.clickRegistration();
    }

    @Test(dataProvider = "registrationWithValidData")
    public void testRegistrationWithValidData(User user) {
        registrationPage.fillRegistrationForm(user);
        List<String> errors = registrationPage.getListOfErrors();
//        registrationPage.clickSubmit();
        assertTrue(errors.size() == 0);
        assertTrue(registrationPage.isSubmitActive());
    }

    @Test(dataProvider = "registrationWithoutMandatoryFields")
    public void testRegistrationWithoutMandatoryFields(User user) {
        registrationPage.fillRegistrationForm(user);
        List<String> errors = registrationPage.getListOfErrors();
        int errorsNumber = 0;
        if (user.getUsername() == null) {
            assertTrue(errors.contains("Enter username"));
            errorsNumber++;
        }
        if (user.getEmail() == null) {
            assertTrue(errors.contains("Enter email"));
            errorsNumber++;
        }
        if (user.getPassword() == null) {
            assertTrue(errors.contains("Enter password"));
            errorsNumber++;
        }
        if (user.getPassword_confirmation() == null) {
            assertTrue(errors.contains("Enter password confirmation"));
            errorsNumber++;
        }
        assertTrue(errors.size() == errorsNumber);
        assertFalse(registrationPage.isSubmitActive());
    }

    @Test(dataProvider = "registrationWithWrongEmail")
    public void testRegistrationWithWrongEmail(User user) {
        registrationPage.fillRegistrationForm(user);
        List<String> errors = registrationPage.getListOfErrors();
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains("Wrong email pattern"));
        assertFalse(registrationPage.isSubmitActive());
    }

    @Test(dataProvider = "registrationWithWrongPassword")
    public void testRegistrationWithWrongPassword(User user) {
        registrationPage.fillRegistrationForm(user);
        List<String> errors = registrationPage.getListOfErrors();
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains("Password must at least 8 character length and must include at least one upper case letter, one down case letter, one digit and one symbol"));
        assertFalse(registrationPage.isSubmitActive());
    }

    @Test(dataProvider = "registrationWithWrongPasswordConfirm")
    public void testRegistrationWithWrongPasswordConfirm(User user) {
        registrationPage.fillRegistrationForm(user);
        List<String> errors = registrationPage.getListOfErrors();
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains("Wrong confirmation"));
        assertFalse(registrationPage.isSubmitActive());
    }

}
