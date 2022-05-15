package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseHandler;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        processAuthorization();

    }

    public void switchToChat(){
        buttonSignIn.setOnAction(event -> {
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
                try {
                    loginUser(email, password);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (email.equals("") && password.equals("")) {
                labelError.setOpacity(1);
                labelError.setText("login or/and password \n is empty");
            }
        });
    }

    private void loginUser(String email, String password) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        ResultSet resultSet = databaseHandler.signInUser(user);

        int counter = 0;

        while (resultSet.next()){
            counter++;
        } if (counter >=1 ){
            System.out.println("user authorized");
            switchToChat();
        } else if (!resultSet.next()){
            labelError.setOpacity(1);
        }
    }
}


