package org.example;

import java.sql.*;

public class DatabaseConnection {

    public Connection connection;
    public Statement statement;

    public DatabaseConnection() {

        // Check if MySQL JDBC driver is installed on the system
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e) {
            System.out.printf("%s (Missing JDBC MySQL driver)", e.getMessage());
            return;
        }

        try {
            String dbURL = "jdbc:mysql://localhost:3306/westums";
            this.connection = DriverManager.getConnection(dbURL, "root", " ");
            this.statement = connection.createStatement();

            // Test query
            ResultSet resultSet = statement.executeQuery("select * from dual");
        }
        catch (SQLException e) {
            System.out.printf("Error connecting to DB: %s", e.getMessage());
            return;
        }
    }
}
