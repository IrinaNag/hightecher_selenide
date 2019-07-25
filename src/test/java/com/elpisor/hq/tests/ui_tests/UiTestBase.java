package com.elpisor.hq.tests.ui_tests;

import com.elpisor.hq.manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class UiTestBase {


    protected final static String RESOURCES_URL = "src/test/resources/";
    Logger logger = LoggerFactory.getLogger(UiTestBase.class);


    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.start();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method method, Object[] p) {
        logger.info("Start test " + method.getName() + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method, Object[] p) {
        logger.info("Stop test " + method.getName() + Arrays.asList(p));
    }

}