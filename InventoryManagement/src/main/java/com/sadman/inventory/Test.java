package com.sadman.inventory;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.InputStream;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception {
        String password = "123456";
        System.out.println(DigestUtils.sha1Hex(password));

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("application.properties")) {
            properties.load(is);
        }

        System.out.println(properties.getProperty("admin.report.email"));

        EmailService.sendEmail();
    }
}
