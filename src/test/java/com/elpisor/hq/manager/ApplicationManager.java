package com.elpisor.hq.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private String browser;
    protected WebDriver driver;

    private NavigationHelper navigationHelper;
    private DataHelper dataHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties=new Properties();
        String target=System.getProperty("target","general");
        try {
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

        if (browser.equals(BrowserType.CHROME))
            driver = new ChromeDriver();
        else if (browser.equals(BrowserType.FIREFOX))
            driver = new FirefoxDriver();
        else if (browser.equals(BrowserType.IE))
            driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        navigationHelper = new NavigationHelper(driver);
        dataHelper = new DataHelper(driver);
    }


    public void stop() {
        driver.quit();
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DataHelper getData() {
        return dataHelper;
    }

    public Properties getProperties() {
        return properties;
    }

    public WebDriver getDriver() {
        return driver;
    }
}

