package server;

import database.DatabaseManager;
import database.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    Server server;
    Socket socket;
    DataInputStream inMessage;
    DataOutputStream outMessage;

    User user = new User();
    DatabaseManager database = new DatabaseManager();


    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler() {
    }

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;

        try {
            inMessage = new DataInputStream(socket.getInputStream());
            outMessage = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    serverMessage("welcome to the chat");
                    serverMessage("set a nickname for yourself using the command: ");
                    serverMessage("/setName yourName");

                    try {

                        while (true){
                        String string;
                        string = inMessage.readUTF();

                        if (string.startsWith("/setName")){
                            String[] getName = string.split(" ");
                            for (String s:getName){
                                name = s;
                                serverMessage("the user has set a name for himself: "+name);
                            }
                        } else if
                          (name == null)
                            name = "user";
                        server.sendToAll(name+": "+string);

                        if (string.equals("/end")) {
                            break;
                        }
                         }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            outMessage.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            inMessage.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } server.removeClient(ClientHandler.this);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {

        System.out.println("client "+name+" send message: "+message);

//        System.out.println("вызов метода прошел успешно 2");
        try {
            outMessage.writeUTF(message+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverMessage(String message){
        System.out.println("server send message: "+message);
        try {
            outMessage.writeUTF(message+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
