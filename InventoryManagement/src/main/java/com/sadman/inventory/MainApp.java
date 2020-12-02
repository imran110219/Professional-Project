package com.sadman.inventory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class MainApp extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        //EmailService.sendTimerEmail();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory:: Version 1.0");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Initialize");
        if (HibernateUtil.setSessionFactory() ) {

            LocalDate today = LocalDate.now();
            int currentYear= today.getYear();

            Properties properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream is = loader.getResourceAsStream("application.properties")) {
                properties.load(is);
            }

            final int validyear = Integer.parseInt(properties.getProperty("validity.year"));

            if(currentYear > validyear){
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Update");
                    alert.setHeaderText("Please Update System");
                    alert.setContentText("Send Email to ciphertextbd@gmail.com");
                    alert.showAndWait();
                    Platform.exit();
                });
            }
            else {
                launch(args);
                HibernateUtil.getSessionFactory().close();
            }
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An error has occured!");
                alert.setHeaderText("Database Connection Error!");
                alert.setContentText("Please contact the developer");
                alert.showAndWait();
                Platform.exit();
            });
        }

    }

}
