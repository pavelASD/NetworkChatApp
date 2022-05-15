package server;

import database.DatabaseHandler;
import database.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server {

    private ArrayList<ClientHandler> clients;
    private int clientCounter = 1;
    DatabaseHandler database = new DatabaseHandler();

    public void start(){

        ServerSocket server;
        Socket socket;

        clients = new ArrayList<>();

        try {

            database.getDatabaseConnection();
            server = new ServerSocket(Config.PORT);
            System.out.println("the server is running");
            System.out.println("");

            while (true){
                System.out.println("the server is waiting for connection");
                socket = server.accept();
                System.out.println("the client is connected");
                System.out.println("amount clients: "+(clientCounter++));
                ClientHandler client = new ClientHandler(this, socket);
                clients.add(client);
                System.out.println("client add in arraylist");
                System.out.println("");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendToAll(String message) {
        System.out.println("вызов метода прошел успешно 1");
        for (ClientHandler client : clients){
            client.sendMessage(message);
        }
    }
}
