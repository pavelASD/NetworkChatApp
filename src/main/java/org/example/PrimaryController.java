package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PrimaryController implements Initializable {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String HOST = "localhost";
    final int PORT = 8189;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonSend;

    @FXML
    private TextField enterText;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuButton settings;

    @FXML
    private TextArea textShow;
    @FXML
    void initialize() {


    }

    public void sendMSG(){
        buttonSend.setOnAction(event -> {
            textShow.appendText(enterText.getText()+"\n");
            enterText.clear();
        });
    }

    public void sendMSG_Enter(){
        enterText.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().getCode() == 10){
                textShow.appendText(enterText.getText()+"\n");
                enterText.clear();
            }
        });
    }

    @FXML
    private void switchToAuthAndReg() throws IOException {
        App.setRoot("authAndReg");
    }

    public void back(){
        buttonBack.setOnAction(event -> {
            try {
                switchToAuthAndReg();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textShow.setEditable(false);

        back();
        sendMSG();
        sendMSG_Enter();
    }
}

