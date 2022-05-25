package server;

import database.DatabaseManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server {

    private ArrayList<ClientHandler> clients;
    private int clientCounter = 1;

    public void start(){

        ServerSocket server;
        Socket socket;
        DatabaseManager database = new DatabaseManager();

        clients = new ArrayList<>();

        try {

            server = new ServerSocket(Config.PORT);
            System.out.println("the server is running");
            System.out.println("");


            while (true){
                Thread.sleep(1000);
                System.out.println("the server is waiting for connection");
                socket = server.accept();
                System.out.println("the client is connected");
                System.out.println("amount clients: "+(clientCounter++));
                ClientHandler client = new ClientHandler(this, socket);
                clients.add(client);
                System.out.println("client added in arraylist");
                System.out.println("");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addClient(ClientHandler client){
        clients.add(client);
        System.out.println("client add in arraylist");
        System.out.println("");
    }

    public void removeClient(ClientHandler client){
        clients.remove(client);
        System.out.println("the client left the chat ");
        System.out.println("client amount: "+(clientCounter--));
    }

    public void sendToAll(String message) {
//        System.out.println("вызов метода прошел успешно 1");
        for (ClientHandler client : clients){
            client.sendMessage(message);
        }
    }
}
