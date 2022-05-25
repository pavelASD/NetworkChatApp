package database;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
public class DatabaseHandler extends Configs {
    Connection connection;

    public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, DB_USER, DB_PASSWORD);

        return connection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Constant.USER_TABLE +"(" + Constant.USER_NAME +"," + Constant.USER_EMAIL+"," +Constant.USER_PASSWORD+")" +
                "VALUES(?,?,?)";


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
}
