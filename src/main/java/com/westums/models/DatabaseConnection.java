package com.westums.models;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnection {

    public Connection connection;
    public Statement statement;

    // TODO: Make errors appear as JOptionPanes

    public DatabaseConnection() throws SQLException {

        // Check if MySQL JDBC driver is installed on the system
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            new JOptionPane("Missing database connectivity driver.",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String dbURL = "jdbc:mysql://localhost:3306/west_ums";
            this.connection = DriverManager.getConnection(dbURL, "root", "");
            statement = connection.createStatement();
            statement.execute("USE west_ums");
        }
        catch (SQLException e) {
            throw new SQLException("Error connecting to database");
        }
    }
}
