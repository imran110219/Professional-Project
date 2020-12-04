package com.sadman.inventory;

import com.sadman.inventory.entity.Invoice;
import com.sadman.inventory.model.InvoiceModel;
import com.sadman.inventory.pdf.PrintDailyReport;
import com.sadman.inventory.util.Util;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception {
        String password = "123456";
        System.out.println(DigestUtils.sha1Hex(password));


//        System.out.println(Util.convertNumberToWord(213213));
//        HibernateUtil.setSessionFactory();

//        PrintDailyReport printDailyReport = new PrintDailyReport();
//        printDailyReport.generateReport();


//        Properties properties = new Properties();
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        try (InputStream is = loader.getResourceAsStream("application.properties")) {
//            properties.load(is);
//        }

//        System.out.println(properties.getProperty("admin.report.email"));

//        EmailService.sendEmail(); System.currentTimeMillis()

//        LocalDate today = LocalDate.now();
//        int currentDate= today.getDayOfMonth();
//        int currentMonth= today.getMonthValue();
//        int currentYear= today.getYear();

//        int year = 2019;

//        System.out.println(currentYear + "      " + year);
//        if(currentYear > year){
//            System.out.println("Ok");
//        }
    }
}
