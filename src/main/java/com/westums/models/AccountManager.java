package com.westums.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

public class AccountManager {

    public enum UserType {
        Professor,
        Student,
        Admin
    }

    /**
     * Search the database for a matching email and return the hashed password
     * along with the userType
     */
    public static ArrayList<Object> isValidEmail(String email) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT Pass, UserType " +
                    "FROM Accounts " +
                    "WHERE Email = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // If no matching email is found
            if (!rs.next()) return null;

            String password = rs.getString("Pass");
            UserType userType = UserType.valueOf(rs.getString("UserType"));

            // Matching email is found, but no password is set for the account
            if (password == null) {
                ArrayList<Object> list = new ArrayList<>();
                list.add("null");
                list.add(userType);
                return list;
            }

            // Matching email is found with password
            ArrayList<Object> list = new ArrayList<>();
            list.add(password);
            list.add(userType);
            return list;
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Checks if the entered password, when hashed,
     * matches the hashed password stored in the database.
     */
    public static boolean isValidPassword(String enteredPassword, String hashedPassword) {
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }

    /**
     * Add a password to an existing account which did not
     * previously have one.
     */
    public static void insertPassword(String email, String enteredPassword) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "UPDATE Accounts " +
                    "SET Pass = ? " +
                    "WHERE Email = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, BCrypt.hashpw(enteredPassword, BCrypt.gensalt()));
            stmt.setString(2, email);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Create a new account of type `userType` and insert it into the database,
     * with a null password
     */
    public static void createAccount(String email, UserType userType) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO Accounts(Email, UserType) " +
                    "VALUES (?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, userType.toString());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }




}
