package database;

import java.sql.*;

public class DatabaseManager extends Configs {

    Connection connection;
    Statement statement;

    public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:postgresql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(connectionString, DB_USER, DB_PASSWORD);

        return connection;
    }

    public void signUpUser(User user){

        String insert = "INSERT INTO " + Constant.USER_TABLE +"("+ Constant.USER_NAME +", " + Constant.USER_EMAIL+", " +Constant.USER_PASSWORD+")" +
                "VALUES(?, ?, ?)";

        try {
        PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement(insert);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public ResultSet signInUser(User user){
        ResultSet resultSet = null;

        String select = "SELECT * FROM "+Constant.USER_TABLE+" WHERE "+Constant.USER_NAME +"=? AND "+Constant.USER_PASSWORD+"=?";

        try{
            PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

}
