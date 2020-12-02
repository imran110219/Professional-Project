package com.sadman.inventory;

import com.sadman.inventory.model.EmployeeModel;
import com.sadman.inventory.entity.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * @author Sadman
 */
class EmailTask extends TimerTask {
    public void run() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("Email is sending " + dtf.format(LocalDateTime.now()));
        try {
            EmailService.sendEmail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class EmailService {
    private static EmployeeModel model = new EmployeeModel();

    public static void sendTimerEmail(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 21); // Time 10 PM
        today.set(Calendar.MINUTE, 23);
        today.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.schedule(new EmailTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
    }

    public static void sendEmail() throws IOException {
        HibernateUtil.setSessionFactory();
        Employee employee = model.getEmployeeByUsername("admin");
        String email = employee.getEmail();

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("application.properties")) {
            properties.load(is);
        }

        final String username = properties.getProperty("admin.report.email");
        final String password = properties.getProperty("admin.report.password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            String file = "E:\\egp.pdf";
            String fileName = "egp.pdf";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        HibernateUtil.getSessionFactory().close();
    }
}
