package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tic_tac_toe";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "sammy2003@21";

    // Main method for testing the connection
    public static void main(String[] args) {
        try {
            Connection connection = getConnection();  // Use getConnection here
            System.out.println("Database connection successful!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed!: " + e.getMessage());
        }
    }

    // getConnection method to be used by other classes
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
