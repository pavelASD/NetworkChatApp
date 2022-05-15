package server;

import database.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    Server server;
    Socket socket;
    DataInputStream inMessage;
    DataOutputStream outMessage;

    User user = new User();

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
                    try {
                        while (true){
                        String string;
                        string = inMessage.readUTF();

                        server.sendToAll(name+": "+string);

                        if (string.equals("/end")) {
                            outMessage.writeUTF("/end");
                            break;
                        }
                         }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } //finally {
//                        try {
//                            outMessage.close();
//                            inMessage.close();
//                            socket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {

        System.out.println("client "+name+" send message: "+message);

        System.out.println("вызов метода прошел успешно 2");
        try {
            outMessage.writeUTF(message+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
