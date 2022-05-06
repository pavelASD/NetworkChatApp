package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonRegistration;

    @FXML
    private Button buttonSignIn;

    @FXML
    private TextField fieldEmailAddressSignIn;

    @FXML
    private PasswordField fieldPasswordSignIn;

    @FXML
    void switchToPrimary() {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToRegistration() {
        try {
            App.setRoot("registration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        switchToReg();
        switchToChat();

    }

    public void switchToChat(){
        buttonSignIn.setOnAction(event -> {
            buttonSignIn.getScene().getWindow().hide();

              switchToPrimary();
        });
    }

    public void switchToReg(){
        buttonRegistration.setOnAction(event -> {
            switchToRegistration();
        });
    }

    public void processAuthorization(){
            DatabaseHandler databaseHandler = new DatabaseHandler();

        buttonSignIn.setOnAction(event -> {


            String email = fieldEmailAddressSignIn.getText().trim();
            String password = fieldPasswordSignIn.getText().trim();

            if (!email.equals("") && !password.equals("")){
                loginUser(email, password);
            } else
                System.out.println("login or/and password is empty");
        });
    }

    private void loginUser(String email, String password) {
    }


}


