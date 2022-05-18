package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.DatabaseManager;
import database.User;
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
    void initialize() {
        switchToReg();

        buttonSignIn.setOnAction(event -> {
            processAuthorization();
        });
    }

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


    public void switchToReg(){
        buttonRegistration.setOnAction(event -> {
            switchToRegistration();
        });
    }

    public void processAuthorization(){
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

    }

    private void loginUser(String name, String password) throws SQLException {
        DatabaseManager databaseHandler = new DatabaseManager();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        ResultSet resultSet = databaseHandler.signInUser(user);


        int counter = 0;

        while (resultSet.next()){
            counter++;
        } if (counter >=1 ){
            System.out.println("user authorized");
            switchToPrimary();
        } else if (!resultSet.next()){
            labelError.setOpacity(1);
        }
    }
}


