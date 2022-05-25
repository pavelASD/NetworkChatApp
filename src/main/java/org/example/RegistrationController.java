package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.DatabaseManager;
import database.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonSignUp.setOnAction(event -> {
                signUpNewUser();
             switchToPrimary();
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

    private void signUpNewUser() {
        DatabaseManager data = new DatabaseManager();

        String name = fieldNameSignUp.getText();
        String email = fieldEmailAddressSignUp.getText();
        String password = fieldPasswordSignUp.getText();

        User user = new User(name, email, password);
        data.signUpUser(user);
    }
}
