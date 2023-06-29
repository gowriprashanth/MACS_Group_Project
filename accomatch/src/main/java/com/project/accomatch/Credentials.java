package com.project.accomatch;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Credentials {

    private String username;
    private String password;
    private String JDBC;

    public void credentialsFinder() {
        Properties identity = new Properties();
        String propertyFilename = "accomatch/src/main/java/com/project/accomatch/credentials.prop";

        try {
            InputStream stream = new FileInputStream(propertyFilename);

            identity.load(stream);
            username = identity.getProperty("username");
            password = identity.getProperty("password");
            JDBC = identity.getProperty("Connection");
            System.out.println(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJDBC() {
        return JDBC;
    }

    public void setJDBC(String JDBC) {
        this.JDBC = JDBC;
    }

    public Credentials returnObject(){
        return this;
    }
}