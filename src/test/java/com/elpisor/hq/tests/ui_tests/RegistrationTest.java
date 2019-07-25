package com.elpisor.hq.tests.ui_tests;

import com.codeborne.selenide.ElementsCollection;
import com.elpisor.hq.page_objects.Header;
import com.elpisor.hq.page_objects.RegistrationPage;
import com.elpisor.hq.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;

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
    private Header header;

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
        Header header = new Header();
        header.openHeader();
        registrationPage = header.clickRegistration();
    }


    @Test(dataProvider = "registrationWithValidData")
    public void testRegistrationWithValidData(User user) {
        registrationPage.fillRegistrationForm(user);
        ElementsCollection errors = registrationPage.getErrors();
        errors.shouldBe(empty);
        registrationPage.getSubmit().shouldBe(enabled);
    }

    @Test(dataProvider = "registrationWithoutMandatoryFields")
    public void testRegistrationWithoutMandatoryFields(User user) {
        registrationPage.fillRegistrationForm(user);
        ElementsCollection errors = registrationPage.getErrors();
        int errorsNumber = 0;
        if (user.getUsername() == null) {
            registrationPage.getError("Enter username").shouldBe(visible);
            errorsNumber++;
        }
        if (user.getEmail() == null) {
            registrationPage.getError("Enter email").shouldBe(visible);
            errorsNumber++;
        }
        if (user.getPassword() == null) {
            registrationPage.getError("Enter password").shouldBe(visible);
            errorsNumber++;
        }
        if (user.getPassword_confirmation() == null) {
            registrationPage.getError("Enter password confirmation").shouldBe(visible);
            errorsNumber++;
        }
        errors.shouldHave(size(errorsNumber));
        registrationPage.getSubmit().shouldNotBe(enabled);
    }

    @Test(dataProvider = "registrationWithWrongEmail")
    public void testRegistrationWithWrongEmail(User user) {
        registrationPage.fillRegistrationForm(user);
        ElementsCollection errors = registrationPage.getErrors();
        errors.shouldHave(size(1));
        registrationPage.getError("Wrong email pattern").shouldBe(visible);
        registrationPage.getSubmit().shouldNotBe(enabled);
    }

    @Test(dataProvider = "registrationWithWrongPassword")
    public void testRegistrationWithWrongPassword(User user) {
        registrationPage.fillRegistrationForm(user);
        ElementsCollection errors = registrationPage.getErrors();
        errors.shouldHave(size(1));
        registrationPage
                .getError("Password must at least 8 character length and must include at least one upper case letter," +
                        " one down case letter, one digit and one symbol")
                .shouldBe(visible);
        registrationPage.getSubmit().shouldNotBe(enabled);
    }

    @Test(dataProvider = "registrationWithWrongPasswordConfirm")
    public void testRegistrationWithWrongPasswordConfirm(User user) {
        registrationPage.fillRegistrationForm(user);
        ElementsCollection errors = registrationPage.getErrors();
        errors.shouldHave(size(1));
        registrationPage.getError("Wrong confirmation").shouldBe(visible);
        registrationPage.getSubmit().shouldNotBe(enabled);
    }

}
