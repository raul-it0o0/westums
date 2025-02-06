package com.westums.models.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used during the login process, to authenticate the user,
 * and to add their password whenever necessary.
 */
public class Authenticator {

    public final static String EMAIL_DOMAIN = "e-uvt.ro";

    public enum AccountType {
        PROFESSOR,
        STUDENT,
        ADMIN
    }

    public static String toDBEnum(AccountType accountType) {
        switch (accountType) {
            case PROFESSOR:
                return "Professor";
            case STUDENT:
                return "Student";
            case ADMIN:
                return "Admin";
        }
        return null;
    }

    /**
     * Search the database for a matching email and return the hashed password
     * along with the userType
     * @param email The email to be searched for
     * @return An ArrayList containing the hashed password and the userType
     */
    public static ArrayList<Object> getPasswordAndUserTypeByEmail(String email) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT Pass, UserType " +
                    "FROM Accounts " +
                    "WHERE Email = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            // If no matching email is found, return null
            if (!rs.next()) return null;

            String password = rs.getString("Pass");

            // Convert the string form of the account type to the enum form
            AccountType accountType =
                    AccountType.valueOf(rs.getString("UserType").toUpperCase());

            // Matching email is found, but no password is set for the account
            // Return the password and the account type
            ArrayList<Object> list = new ArrayList<>();
            list.add(password);
            list.add(accountType);
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
    public static boolean isMatchingPassword(String enteredPassword, String hashedPassword) {
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


}
