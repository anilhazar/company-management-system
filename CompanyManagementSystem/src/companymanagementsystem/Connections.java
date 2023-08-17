package companymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {

    private final String username = "root";
    private final String password = "123456";

    private final String dbname = "company_management";

    private final String host = "localhost";

    private final int port = 3306;

    private Connection connection;

    private Statement statement;

    private PreparedStatement preparedStatement;

    public Connections() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed" + e);
        }
    }

    public boolean preparedAddData(String username, String password) { // Dönen boolean göre. 

        try {
            String query = "INSERT INTO ADMIN (username, password) VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int resultSet = preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.out.println(exception);
            return false;
        }

        return true;
    }

    public boolean preparedLoginCheckpoint(String username, String password) {

        try {
            String query = "SELECT username,password FROM ADMIN WHERE username=? AND password=? ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultS = preparedStatement.executeQuery();
            if (resultS.next()) {
                return true;
            }

        } catch (SQLException exception) {
            return false;
        }
        return false;
       }

}
