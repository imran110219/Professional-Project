package com.sadman.controller;

import com.sadman.service.DESService;
import com.sadman.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * @author Sadman
 */
public class DESController implements Initializable {

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

    @FXML
    private Button btnGenerateKey;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void generateKey(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
        String key = Util.convertKeyToString(secretKey);
        keyText.setText(key);
    }

    public void doEncrypt(ActionEvent actionEvent) throws IOException {
        String originalString = inputText.getText();
        String keyString = keyText.getText();
        String encryptedString = DESService.encrypt(originalString, keyString);
        outputText.setText(encryptedString);
    }

    public void doDecrypt(ActionEvent actionEvent) throws IOException {
        String originalString = inputText.getText();
        String keyString = keyText.getText();
        String decryptedString = DESService.decrypt(originalString, keyString) ;
        outputText.setText(decryptedString);
    }
}
