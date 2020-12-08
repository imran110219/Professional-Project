package com.sadman.inventory;

import com.sadman.inventory.entity.Category;
import com.sadman.inventory.entity.Invoice;
import com.sadman.inventory.entity.Product;
import com.sadman.inventory.model.CategoryModel;
import com.sadman.inventory.model.InvoiceModel;
import com.sadman.inventory.model.ProductModel;
import com.sadman.inventory.pdf.PrintDailyReport;
import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.util.Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Test {

    private static Session session;

    public static void main(String[] args) throws Exception {
        String password = "Nasir12345";
        System.out.println(DigestUtils.sha1Hex(password));
//
//        HibernateUtil.setSessionFactory();
//        ProductModel productModel = new ProductModel();
//        CategoryModel categoryModel = new CategoryModel();
//
//        Category category = categoryModel.getCategory(7L);
//        List<Product> products = productModel.getProductListByCategory(category);

//        System.out.println(productModel.getProductListByCategory(category).isEmpty());
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
