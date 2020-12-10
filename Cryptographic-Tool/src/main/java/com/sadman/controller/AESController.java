package com.sadman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sadman
 */
public class AESController implements Initializable {

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    private TextField keyText;

    @FXML
    private Button btnEncrypt;

    @FXML
    private Button btnDecrypt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void doEncrypt(ActionEvent actionEvent) throws IOException {

    }

    public void doDecrypt(ActionEvent actionEvent) throws IOException {

    }
}
