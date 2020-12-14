package com.sadman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.ResourceBundle;

/**
 * @author Sadman
 */
public class RSAController implements Initializable {

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    private TextField privateKeyText;

    @FXML
    private TextField publicKeyText;

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
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom();
        kpg.initialize(2048,secureRandom);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();

        String strPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String strPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        publicKeyText.setText(strPublicKey);
        privateKeyText.setText(strPrivateKey);
    }

    public void doEncrypt(ActionEvent actionEvent) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidKeySpecException {
        String originalString = inputText.getText();
        String publicKeyString = publicKeyText.getText();
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publickey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publickey);
        String encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(originalString.getBytes("UTF-8")));
        outputText.setText(encryptedString);
    }

    public void doDecrypt(ActionEvent actionEvent) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        String encryptedString = inputText.getText();
        String privateKeyString = privateKeyText.getText();
        byte[] privateBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedString)));
        outputText.setText(decryptedString);
    }
}
