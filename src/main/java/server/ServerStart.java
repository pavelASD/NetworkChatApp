package server;

import database.DatabaseManager;

import java.io.IOException;
import java.sql.SQLException;

public class ServerStart {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Server server = new Server();
        server.start();
    }
}
