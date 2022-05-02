package org.example;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthAndRegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignIn;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField fieldEmailAddressSignIn;

    @FXML
    private TextField fieldEmailAddressSignUp;

    @FXML
    private TextField fieldNameSignUp;

    @FXML
    private PasswordField fieldPasswordSignIn;

    @FXML
    private PasswordField fieldPasswordSignUp;

    @FXML
    private PasswordField fieldRepeatPasswordSignUp;

    @FXML
    void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void initialize() {
        signIn();
    }

    public void signIn(){
        buttonSignIn.setOnAction(event -> {
            try {
                switchToPrimary();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

