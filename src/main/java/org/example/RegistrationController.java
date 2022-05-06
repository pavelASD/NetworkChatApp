package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void switchToChat(){
        buttonSignUp.setOnAction(event -> {
            switchToPrimary();
        });
    }

    public void processRegistration(){

        buttonSignUp.setOnAction(event -> {

            signUpNewUser();
            switchToChat();

        });
    }


//            email = fieldEmailAddressSignUp.getText().trim();
//            password = fieldPasswordSignUp.getText().trim();

//            if (!email.equals("") && !password.equals("")){
//                loginUser(email, password);
//            } else
//                System.out.println("login or/and password is empty");


    private void signUpNewUser() {
            DatabaseHandler databaseHandler = new DatabaseHandler();

            String name = fieldNameSignUp.getText();
            String email = fieldEmailAddressSignUp.getText();
            String password = fieldPasswordSignUp.getText();

            User user = new User(name, email, password);

            databaseHandler.signUpUser(user);
    }

    private void loginUser(String email, String password) {
    }
}
