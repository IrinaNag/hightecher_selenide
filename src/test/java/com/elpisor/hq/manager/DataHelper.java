package com.elpisor.hq.manager;

import com.elpisor.hq.model.User;
import com.elpisor.hq.model.UserCreds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

public class DataHelper {

    public Iterator<Object[]> getUserCredsFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().map(line -> line.split(";"))
                    .map(split -> new Object[]{new UserCreds(split[0].equals("null") ? null : split[0], split[1].equals("null") ? null : split[1])})
                    .collect(Collectors.toList()).iterator();
        }
    }

    public Iterator<Object[]> getUsersFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines().map(line -> line.split(";"))
                    .map(split -> new Object[]{new User(split[0].equals("null") ? null : split[0]
                            , split[1].equals("null") ? null : split[1], split[2].equals("null") ? null : split[2]
                            , split[3].equals("null") ? null : split[3], split[4].equals("null") ? null : split[4]
                            , split[5].equals("null") ? null : split[5], split[6].equals("null") ? null : split[6])})
                    .collect(Collectors.toList()).iterator();
        }
    }

}