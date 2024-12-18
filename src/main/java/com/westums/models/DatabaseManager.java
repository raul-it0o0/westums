package com.westums.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseManager {

    private final static String EMAIL_DOMAIN = "e-uvt.ro";

    /**
     * Search the database for matches to the email
     * @return True if a match to the email is found, false otherwise
     * @throws SQLException if any error occurs while querying the database
     */
    public static boolean isValidProfessorEmail(String email) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT * " +
                    "FROM Professors " +
                    "WHERE Email = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public enum AccountType {
        PROFESSOR,
        STUDENT,
        ADMIN
    }

    /**
     * Search the database for identical emails and return the number of occurrences
     * @param email The email to be searched for
     * @return The number of identical emails found
     * @throws SQLException if any error occurs while querying the database
     */
    private static int numberOfIdenticalEmails(String email) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT COUNT(*) AS count " +
                    "FROM Accounts " +
                    "WHERE Email LIKE ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count");
            }
            return 0;
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Generate a unique email for a student based on their name, surname and birthdate,
     * based on the following templates:
     * <ol>
     *     <li>first_name.surnameYY@DOMAIN</li>
     *     <li>
     *         <ol>
     *             <li>middle_name.surnameYY@DOMAIN</li>
     *             if the student has a middle name, and
     *             <li>first_name.surnameYY<code>X</code>@DOMAIN</li>
     *             otherwise, where <code>X</code> is the number of identical emails found
     *         </ol>
     *     </li>
     *     <li>first_name.middle_nameYY@DOMAIN</li>
     *     if the student has a middle name and the previous templates did not return a unique email
     * </ol>
     * @return The unique email generated
     * @throws SQLException if any error occurs while querying the database
     */
    public static String generateStudentEmail(String name, String surname, Date birthDate) throws SQLException {
        String[] nameParts = name.toLowerCase().split("\\s*-\\s*");
        String firstName = nameParts.length > 0 ? nameParts[0] : name;
        String middleName = nameParts.length > 1 ? nameParts[1] : null;
        surname = surname.toLowerCase();
        String birthdateYY = new SimpleDateFormat("yy").format(birthDate);

        // First template: first_name.surnameYY@DOMAIN
        StringBuilder email = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(surname)
                .append(birthdateYY)
                .append("@")
                .append(EMAIL_DOMAIN);

        int count = 0;

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        if (middleName == null) {
            do {
                email = new StringBuilder()
                        .append(firstName)
                        .append(".")
                        .append(surname)
                        .append(birthdateYY)
                        .append(count)
                        .append("@")
                        .append(EMAIL_DOMAIN);
                count++;
            } while(numberOfIdenticalEmails(email.toString()) > 0);

            return email.toString();
        }

        // Second template: middle_name.surnameYY@DOMAIN

        email = new StringBuilder()
                .append(middleName)
                .append(".")
                .append(surname)
                .append(birthdateYY)
                .append("@")
                .append(EMAIL_DOMAIN);

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        // Third template: first_name.middle_nameYY@DOMAIN

        email = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(middleName)
                .append(birthdateYY)
                .append("@")
                .append(EMAIL_DOMAIN);

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        return email.toString();
    }

    /**
     * Generate a unique email for a professor based on their name, and surname,
     * based on the following templates:
     * <ol>
     *     <li>first_name.surname@DOMAIN</li>
     *     <li>
     *         <ol>
     *             <li>middle_name.surname@DOMAIN</li>
     *             if the professor has a middle name, and
     *             <li>first_name.surname<code>X</code>@DOMAIN</li>
     *             otherwise, where <code>X</code> is the number of identical emails found
     *         </ol>
     *     </li>
     *     <li>first_name.middle_name@DOMAIN</li>
     *     if the professor has a middle name and the previous templates did not return a unique email
     * </ol>
     * @return The unique email generated
     * @throws SQLException if any error occurs while querying the database
     */
    public static String generateProfessorEmail(String name, String surname, Date birthDate) throws SQLException {
        String[] nameParts = name.toLowerCase().split("\\s*-\\s*");
        String firstName = nameParts.length > 0 ? nameParts[0] : name;
        String middleName = nameParts.length > 1 ? nameParts[1] : null;
        surname = surname.toLowerCase();

        // First template: first_name.surname@DOMAIN
        StringBuilder email = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(surname)
                .append("@")
                .append(EMAIL_DOMAIN);

        int count = 0;

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        if (middleName == null) {
            do {
                email = new StringBuilder()
                        .append(firstName)
                        .append(".")
                        .append(surname)
                        .append(count)
                        .append("@")
                        .append(EMAIL_DOMAIN);
                count++;
            } while(numberOfIdenticalEmails(email.toString()) > 0);

            return email.toString();
        }

        // Second template: middle_name.surname@DOMAIN

        email = new StringBuilder()
                .append(middleName)
                .append(".")
                .append(surname)
                .append("@")
                .append(EMAIL_DOMAIN);

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        // Third template: first_name.middle_name@DOMAIN

        email = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(middleName)
                .append("@")
                .append(EMAIL_DOMAIN);

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        return email.toString();
    }

    /**
     * Search the database for a matching email and return the hashed password
     * along with the userType
     * @param email The email to be searched for
     * @return An <code>ArrayList</code> containing the hashed password and the userType
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
            AccountType accountType = AccountType.valueOf(rs.getString("UserType").toUpperCase());

            // Matching email is found, but no password is set for the account
            if (password == null) {
                ArrayList<Object> list = new ArrayList<>();
                list.add("null");
                list.add(accountType);
                return list;
            }

            // Matching email is found with password
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
     * with a <code>null</code> password.
     * @param email The email of the account to be added
     * @param accountType The type of the account to be added
     *                 (<code>PROFESSOR</code>, <code>STUDENT</code> or <code>ADMIN</code>)
     * @throws SQLException if any error occurs while querying the database.
     */
    public static void createAccount(String email, AccountType accountType) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO Accounts(Email, UserType) " +
                    "VALUES (?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, accountType.toString());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Insert a new record in the `Students` table.
     * @throws SQLException if any error occurs while querying the database
     */
    public static void addStudent(String email, String name, String surname, Date birthDate) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO Students(Email, Name, Surname, DateOfBirth) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setDate(4, new java.sql.Date(birthDate.getTime()));
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Insert a new record in the `Professors` table.
     * @throws SQLException if any error occurs while querying the database
     */
    public static void addProfessor(String email, String name, String surname, Date birthDate) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO Professors(Email, Name, Surname, DateOfBirth) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setDate(4, new java.sql.Date(birthDate.getTime()));
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * Insert a new record in the `Courses` table.
     * @throws SQLException if any error occurs while querying the database
     */
    public static void addCourse(String name, String professorEmail, String courseType) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO Courses(ProfessorID, CourseType, CourseName) " +
                    "VALUES((SELECT professors.ProfessorID FROM Professors WHERE Email = ?), ?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, professorEmail);
            stmt.setString(2, courseType);
            stmt.setString(3, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

}
