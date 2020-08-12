package com.sadman.inventory;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {
    public static void main(String[] args) {
        String password = "123456";
        System.out.println(DigestUtils.sha1Hex(password));
    }
}
