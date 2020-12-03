package com.sadman.inventory.util;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

/**
 * @author Sadman
 */
public class Util {

    public static final String[] units = { "", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen" };

    public static final String[] tens = {
            "", 		// 0
            "",		// 1
            "Twenty", 	// 2
            "Thirty", 	// 3
            "Forty", 	// 4
            "Fifty", 	// 5
            "Sixty", 	// 6
            "Seventy",	// 7
            "Eighty", 	// 8
            "Ninety" 	// 9
    };

    public static String convertNumberToWord(final int n) {
        if (n < 0) {
            return "Minus " + convertNumberToWord(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        }

        if (n < 1000) {
            return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convertNumberToWord(n % 100);
        }

        if (n < 100000) {
            return convertNumberToWord(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convertNumberToWord(n % 1000);
        }

        if (n < 10000000) {
            return convertNumberToWord(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convertNumberToWord(n % 100000);
        }

        return convertNumberToWord(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convertNumberToWord(n % 10000000);
    }


    public static String readApplicationProperty(String name) throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("application.properties")) {
            properties.load(is);
        }
        return properties.getProperty(name);
    }

    public static int getCurrentYear() {
        LocalDate today = LocalDate.now();
        return today.getYear();
    }

    public static int getCurrentMonth() {
        LocalDate today = LocalDate.now();
        return today.getMonthValue();
    }

    public static int getCurrentDay() {
        LocalDate today = LocalDate.now();
        return today.getDayOfMonth();
    }
}
