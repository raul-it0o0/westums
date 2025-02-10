package com.westums.models;

import com.westums.models.uimodels.NewEnrollmentsTableModel;
import com.westums.models.utils.Authenticator;
import com.westums.models.utils.DatabaseConnection;
import com.westums.models.utils.JSONParser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Admin {

    /**
     * Insert a new record in the `Accounts` table,
     * as well as a new record in either the `Students` or
     * `Professors` table, depending on the accountType argument.
     * @throws SQLException if any error occurs while attempting
     *                      to insert into the database.
     */
    public static void addAccount(String email,
                                  Authenticator.AccountType accountType,
                                  String name,
                                  String surname,
                                  Date dateOfBirth) throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO Accounts(Email, UserType) " +
                    "VALUES (?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, accountType.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        // Depending on accountType, insert a new record
        // either in the `Students` or `Professors` table
        String query;

        if (accountType.equals(Authenticator.AccountType.STUDENT)) {
            query = "INSERT INTO Students(Email, Name, Surname, DateOfBirth) " +
                    "VALUES (?, ?, ?, ?)";
        }
        else {
            // implied: accountType = AccountType.Professor
            query = "INSERT INTO Professors(Email, Name, Surname, DateOfBirth) " +
                    "VALUES (?, ?, ?, ?)";
        }

        try {
            DatabaseConnection db = new DatabaseConnection();

            PreparedStatement stmt = db.connection.prepareStatement(query);

            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setDate(4, new java.sql.Date(dateOfBirth.getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
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

    /**
     * Searches the database for matches to the email
     * @return True if a match to the email is found, false otherwise
     * @throws SQLException if any error occurs while querying the database
     */
    public static boolean existsProfessorWithEmail(String email) throws SQLException {
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

    /**
     * Searches the database for a match to the email, and
     * returns the StudentID if found.
     * @throws SQLException if any error occurs while querying the database
     */
    public static int getStudentIDWithEmail(String studentEmail) throws SQLException {
        int studentID = 0;

        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT StudentID " +
                    "FROM Students " +
                    "WHERE Email = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, studentEmail);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                studentID = rs.getInt("StudentID");
            return studentID;
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    /**
     * Searches the database for all the course records.
     * @return An ArrayList of <code>Course</code> objects
     * @throws SQLException if any error occurs while querying the database
     */
    // NOTE: If we want to not keep the course objects in memory
    //  (in the event where many courses exist in the database,
    //  a real life scenario), we would fetch the Course object list,
    //  then destroy it (to free it from memory) and only keep the string
    //  representation of it in the view.
    //  Then, when we want to use any of the information originally stored
    //  in the objects (e.g. CourseID, CourseType) for insertions, updates
    //  we would create Course objects using
    //  their string representations from the view.
    public static ArrayList<Course> fetchAvailableCourses() throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT CourseID, CourseName, CourseType " +
                    "FROM courses";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Course> courses = new ArrayList<>();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getString("CourseType")
                ));
            }

            return courses;
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    /**
     * Searches the database for the courses that student
     * with <code>StudentID</code> is enrolled in (if any).
     * @param studentID
     * @return An ArrayList of <code>Course</code> objects
     * @throws SQLException if any error occurs while querying the database
     */
    public static ArrayList<Course> fetchStudentEnrollments(int studentID) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT courses.CourseID, CourseName, CourseType " +
                    "FROM Enrollments " +
                    "JOIN Courses ON Enrollments.CourseID = Courses.CourseID " +
                    "WHERE StudentID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getString("CourseType")
                ));
            }
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return courses;
    }

    public static void updateEnrollments(int studentID, List<Course> selectedCourses) throws SQLException {
        // Get initial courses (i.e. the courses that the student was originally enrolled in)
        ArrayList<Course> initialCourses = fetchStudentEnrollments(studentID);

        // Determine the courses that were added (i.e. new enrollments)
        ArrayList<Course> addedCourses = new ArrayList<>(selectedCourses);
        addedCourses.removeAll(initialCourses);

        // Determine the courses that were removed (i.e. enrollment records that need to be removed)
        ArrayList<Course> removedCourses = new ArrayList<>(initialCourses);
        removedCourses.removeAll(selectedCourses);

        try {
            DatabaseConnection db = new DatabaseConnection();

            // Insert new enrollments (batch insert)
            if (!addedCourses.isEmpty()) {
                String insertionQuery = "INSERT INTO Enrollments(StudentID, CourseID) " +
                        "VALUES(?, ?)";
                PreparedStatement stmt = db.connection.prepareStatement(insertionQuery);
                stmt.setInt(1, studentID);
                for (Course course : addedCourses) {
                    stmt.setInt(2, course.getID());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }

            if (!removedCourses.isEmpty()) {
                // Delete removed enrollments (batch delete)
                String deletionQuery = "DELETE FROM Enrollments " +
                        "WHERE StudentID = ? AND CourseID = ?";
                PreparedStatement stmt = db.connection.prepareStatement(deletionQuery);
                stmt.setInt(1, studentID);
                for (Course course : removedCourses) {
                    stmt.setInt(2, course.getID());
                    stmt.addBatch();
                }
                stmt.executeBatch();
            }
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
     *             <li>first_name.surnameYYX@DOMAIN</li>
     *             otherwise, where X is the number of identical emails found
     *         </ol>
     *     </li>
     *     <li>first_name.middle_nameYY@DOMAIN</li>
     *     if the student has a middle name and the previous templates did not return a unique email
     * </ol>
     * @return The unique email generated
     * @throws SQLException if any error occurs while querying the database
     */
    public static String generateStudentEmail(String name, String surname, Date birthDate) throws SQLException {
        String[] nameParts = name.toLowerCase().split("\\s*-\\s*|\\s+");
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
                .append(Authenticator.EMAIL_DOMAIN);

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
                        .append(Authenticator.EMAIL_DOMAIN);
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
                .append(Authenticator.EMAIL_DOMAIN);

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
                .append(Authenticator.EMAIL_DOMAIN);

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
        String[] nameParts = name.toLowerCase().split("\\s*-\\s*|\\s+");
        String firstName = nameParts.length > 0 ? nameParts[0] : name;
        String middleName = nameParts.length > 1 ? nameParts[1] : null;
        surname = surname.toLowerCase();

        // First template: first_name.surname@DOMAIN
        StringBuilder email = new StringBuilder()
                .append(firstName)
                .append(".")
                .append(surname)
                .append("@")
                .append(Authenticator.EMAIL_DOMAIN);

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
                        .append(Authenticator.EMAIL_DOMAIN);
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
                .append(Authenticator.EMAIL_DOMAIN);

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
                .append(Authenticator.EMAIL_DOMAIN);

        try {
            count = numberOfIdenticalEmails(email.toString());
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        if (count == 0) return email.toString();

        return email.toString();
    }

    // HELPER METHOD
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

    public static boolean existsCourse(String name, String type) throws SQLException{
        DatabaseConnection db = new DatabaseConnection();

        String query = "SELECT * " +
                "FROM Courses " +
                "WHERE CourseName = ? AND CourseType = ?";
        PreparedStatement stmt = db.connection.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, type);
        ResultSet rs = stmt.executeQuery();

        return rs.next();
    }

    public static void importParsedData(JSONParser parser) throws SQLException{
        // NOTE: Better to do a batch insert instead of individual inserts with existing methods
        // Prepare queries for account, student and professor insertions
        String accountInsertionQuery = "INSERT INTO Accounts(Email, UserType) " +
                "VALUES (?, ?)";
        String studentInsertionQuery = "INSERT INTO Students(Email, Name, Surname, DateOfBirth) " +
                "VALUES (?, ?, ?, ?)";
        String professorInsertionQuery = "INSERT INTO Professors(Email, Name, Surname, DateOfBirth) " +
                "VALUES (?, ?, ?, ?)";

        DatabaseConnection db = new DatabaseConnection();
        PreparedStatement accountStmt = db.connection.prepareStatement(accountInsertionQuery);
        PreparedStatement studentStmt = db.connection.prepareStatement(studentInsertionQuery);
        PreparedStatement professorStmt = db.connection.prepareStatement(professorInsertionQuery);

        for (Student student : parser.getStudentsTable().getData()) {
            accountStmt.setString(1, student.getEmail());
            accountStmt.setString(2, Authenticator.toDBEnum(Authenticator.AccountType.STUDENT));
            accountStmt.addBatch();

            studentStmt.setString(1, student.getEmail());
            studentStmt.setString(2, student.getName());
            studentStmt.setString(3, student.getSurname());
            studentStmt.setDate(4, new java.sql.Date(student.getDateOfBirth().getTime()));
            studentStmt.addBatch();
        }

        for (Professor professor : parser.getProfessorsTable().getData()) {
            accountStmt.setString(1, professor.getEmail());
            accountStmt.setString(2, Authenticator.toDBEnum(Authenticator.AccountType.PROFESSOR));
            accountStmt.addBatch();

            professorStmt.setString(1, professor.getEmail());
            professorStmt.setString(2, professor.getName());
            professorStmt.setString(3, professor.getSurname());
            professorStmt.setDate(4, new java.sql.Date(professor.getDateOfBirth().getTime()));
            professorStmt.addBatch();
        }

        accountStmt.executeBatch();
        studentStmt.executeBatch();
        professorStmt.executeBatch();

        // Prepare queries for course insertions
        String courseInsertionQuery = "INSERT INTO Courses(ProfessorID, CourseType, CourseName) " +
                "VALUES((SELECT ProfessorID FROM Professors WHERE Email = ?), ?, ?)";
        PreparedStatement courseStmt = db.connection.prepareStatement(courseInsertionQuery);

        for (Course course : parser.getCoursesTable().getData()) {
            courseStmt.setString(1, course.getProfessorEmail());
            courseStmt.setString(2, course.getType());
            courseStmt.setString(3, course.getName());
            courseStmt.addBatch();
        }

        courseStmt.executeBatch();

        // Prepare queries for enrollment insertions
        String enrollmentInsertionQuery = "INSERT INTO enrollments(StudentID, CourseID) " +
                "VALUES(" +
                "(SELECT StudentID FROM students WHERE Email = ?), " +
                "(SELECT CourseID FROM courses WHERE CourseName = ? AND CourseType = ?))";
        PreparedStatement enrollmentStmt = db.connection.prepareStatement(enrollmentInsertionQuery);

        for (NewEnrollmentsTableModel.Enrollment enrollment : parser.getEnrollmentsTable().getData()) {
            enrollmentStmt.setString(1, enrollment.getStudentEmail());
            enrollmentStmt.setString(2, enrollment.getCourseName());
            enrollmentStmt.setString(3, enrollment.getCourseType());
            enrollmentStmt.addBatch();
        }

        enrollmentStmt.executeBatch();

    }

}
