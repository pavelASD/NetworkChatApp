package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import database.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import server.Config;

public class PrimaryController implements Initializable {

    private Socket socket;
    private DataInputStream inMessage;
    private DataOutputStream outMessage;

    User user = new User();


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
    private MenuItem buttonAdminVersion;

    @FXML
    private MenuItem buttonDarkTheme;

    @FXML
    private MenuItem buttonHelp;

    @FXML
    private MenuItem buttonRussianLanguage;

    @FXML
    private AnchorPane background;

    @FXML
    private AnchorPane header;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textShow.setEditable(false);

        buttonDarkTheme.setOnAction(event -> {
            background.setStyle("-fx-background-color: #000000;");
        });

        buttonSend.setOnAction(event -> {
            try {
                outMessage.writeUTF(enterText.getText());
                enterText.clear();
                enterText.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        enterText.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().getCode() == 10){
                try {
                    outMessage.writeUTF(enterText.getText());
                    enterText.clear();
                    enterText.requestFocus();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonBack.setOnAction(event -> {
            try {
                switchToAuthorization();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {

            socket = new Socket(Config.HOST, Config.PORT);
            inMessage = new DataInputStream(socket.getInputStream());
            outMessage = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true){
                        try {
                            String string = inMessage.readUTF();

                            textShow.appendText(string +"\n");
                                if (string.equals("/")) {
                                    break;
                                }
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                       } //   finally {
//                            try {
//                                socket.close();
//                                inMessage.close();
//                                outMessage.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
                    }
                }
            }).start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToAuthorization() throws IOException {
        App.setRoot("authorization");

    }
}

