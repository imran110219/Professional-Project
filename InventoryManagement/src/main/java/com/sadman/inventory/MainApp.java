package com.sadman.inventory;

import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.util.Util;
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

        if (HibernateUtil.setSessionFactory() ) {

            int currentYear= Util.getCurrentYear();

            final int validYear = Integer.parseInt(Util.readApplicationProperty("validity.year"));

            if(currentYear > validYear){
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Update");
                    alert.setHeaderText("Please Update System");
                    alert.setContentText("Please Send Email to ciphertextbd@gmail.com");
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
                alert.setTitle("Error");
                alert.setHeaderText("Database Connection Error");
                alert.setContentText("Please Send Email to ciphertextbd@gmail.com");
                alert.showAndWait();
                Platform.exit();
            });
        }

    }

}
