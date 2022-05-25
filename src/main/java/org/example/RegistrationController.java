package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField fieldEmailAddressSignUp;

    @FXML
    private TextField fieldNameSignUp;

    @FXML
    private PasswordField fieldPasswordSignUp;

    @FXML
    private PasswordField fieldRepeatPasswordSignUp;

    @FXML
    private Label labelError;

    @FXML
    void switchToPrimary() {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        processRegistration();

    }

    public void switchToChat() {
        buttonSignUp.setOnAction(event -> {
            switchToPrimary();
        });
    }

    public void processRegistration() {

        buttonSignUp.setOnAction(event -> {
            signUpNewUser();

        });
    }

    private void signUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = fieldNameSignUp.getText();
        String email = fieldEmailAddressSignUp.getText();
        String password = fieldPasswordSignUp.getText();
        String repeatPassword = fieldRepeatPasswordSignUp.getText();

        if (password == repeatPassword && !password.equals("")) {

            User user = new User(name, email, password);
            databaseHandler.signUpUser(user);

            switchToChat();
        }
    }

        private void loginUser (String email, String password){
        }
    }
