package com.westums.models;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnection {

    public Connection connection;
    public Statement statement;

    private static final String DBMS = "MySQL";

    public DatabaseConnection() throws SQLException {

        // Check if MySQL JDBC driver is installed on the system
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Missing database connectivity driver.");
        }

        try {
            String dbURL = "jdbc:mysql://localhost:3306/west_ums";
            this.connection = DriverManager.getConnection(dbURL, "root", "");
            statement = connection.createStatement();
            statement.execute("USE west_ums");
        }
        catch (SQLException e) {
            throw new SQLException(
                String.format("Error connecting to database. The %s service may not be running.\n" +
                                "Detailed error: %s",
                        DBMS,
                        e.getMessage()));
        }
    }
}
