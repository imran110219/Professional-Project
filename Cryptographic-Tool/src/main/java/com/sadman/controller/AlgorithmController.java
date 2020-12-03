package com.sadman.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sadman
 */
public class AlgorithmController implements Initializable {

    @FXML
    private ComboBox<String> typeCBX;

    @FXML
    private ComboBox<String> nameCBX;

    @FXML
    private Pane pnlAES;

    @FXML
    private Pane pnlDES;

    @FXML
    private Pane pnlRSA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCBX.setItems(FXCollections.observableArrayList("Symmetric", "Asymmetric", "Hashing"));

        typeCBX.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue)
            {
                typeChanged(ov, oldvalue, newvalue);
            }});

    }

    public void typeChanged(ObservableValue<? extends String> observable,String oldValue,String newValue) {
        String oldText = oldValue == null ? "null" : oldValue.toString();
        String newText = newValue == null ? "null" : newValue.toString();

        if(newText.equals("Symmetric")){
            nameCBX.setItems(FXCollections.observableArrayList("AES", "DES"));
            nameCBX.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue)
                {
                    try {
                        nameChangedSymmetric(ov, oldvalue, newvalue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }});
        }
        else if(newText.equals("Asymmetric")){
            nameCBX.setItems(FXCollections.observableArrayList("RSA", "ECC"));
            nameCBX.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue)
                {
                    try {
                        nameChangedAsymmetric(ov, oldvalue, newvalue);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }});
        }
        else {

        }
    }

    public void nameChangedSymmetric(ObservableValue<? extends String> observable,String oldValue,String newValue) throws IOException {
        String oldText = oldValue == null ? "null" : oldValue.toString();
        String newText = newValue == null ? "null" : newValue.toString();

        if(newText.equals("AES")){
            Pane aesPane =  FXMLLoader.load(getClass().getResource("/view/aes.fxml"));
            pnlAES.getChildren().setAll(aesPane);
            pnlAES.setStyle("-fx-background-color : #1620A1");
            pnlAES.toFront();
        }
        else if(newText.equals("DES")){
            Pane aesPane =  FXMLLoader.load(getClass().getResource("/view/des.fxml"));
            pnlDES.getChildren().setAll(aesPane);
            pnlDES.setStyle("-fx-background-color : #eaeaef");
            pnlDES.toFront();
        }
//        else {
//
//        }
    }

    public void nameChangedAsymmetric(ObservableValue<? extends String> observable,String oldValue,String newValue) throws IOException {
        String oldText = oldValue == null ? "null" : oldValue.toString();
        String newText = newValue == null ? "null" : newValue.toString();

        if(newText.equals("RSA")){
            Pane aesPane =  FXMLLoader.load(getClass().getResource("/view/rsa.fxml"));
            pnlRSA.getChildren().setAll(aesPane);
            pnlRSA.setStyle("-fx-background-color : #1620A1");
            pnlRSA.toFront();
        }
//        else if(newText.equals("DES")){
//
//        }
//        else {
//
//        }
    }
}
