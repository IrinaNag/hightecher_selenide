package com.elpisor.hq.manager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browser;

public class ApplicationManager {
    private final Properties properties;
    private String br;

    private DataHelper dataHelper;

    public ApplicationManager(String br) {
        this.br = br;
        properties = new Properties();
        String target = System.getProperty("target", "general");
        try {
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        baseUrl = properties.getProperty("web.baseUrl");
        browser = br;
        dataHelper = new DataHelper();
    }


    public void stop() {
    }

    public DataHelper getData() {
        return dataHelper;
    }

}

