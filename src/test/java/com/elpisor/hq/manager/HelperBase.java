package com.elpisor.hq.manager;

import com.elpisor.hq.model.User;
import com.elpisor.hq.model.UserCreds;
import lombok.NoArgsConstructor;
import org.openqa.selenium.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

@NoArgsConstructor
public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }


}
