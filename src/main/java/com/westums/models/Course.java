package com.westums.models;

import com.westums.models.utils.DatabaseConnection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {

    public final static String LECTURE = "Lecture";
    public final static String SEMINAR = "Seminar";
    public final static String LABORATORY = "Laboratory";

    private int id;
    private String name;
    private String type;
    private String professorEmail;

    public Course(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.professorEmail = builder.professorEmail;
    }

    public Course(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static Builder builder(String name, String type, String professorEmail) {
        return new Builder(name, type, professorEmail);
    }

    public static Builder builder(String name, String professorEmail) {
        return new Builder(name, professorEmail);
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public int getEnrolledStudents() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();

        String query = "SELECT COUNT(*) " +
                "FROM Enrollments " +
                "WHERE CourseID = ?";
        PreparedStatement stmt = db.connection.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next())
            return rs.getInt(1);
        else
            throw new SQLException("Error searching for enrollments of course with ID " + this.id);
    }

    public Float getAverageAttendance() throws SQLException {

        //  = (total attendances / (total enrollment * total classes)) * 100
        int totalAttendances = 0;
        int totalEnrollments = 0;
        int totalClasses = 0;

        DatabaseConnection db = new DatabaseConnection();

        // Get total attendances
        String query = "SELECT COUNT(*) " +
                "FROM attendances " +
                "WHERE EnrollmentID = ANY (SELECT EnrollmentID " +
                                            "FROM enrollments " +
                                            "WHERE CourseID = ?)";
        PreparedStatement stmt = db.connection.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next())
            totalAttendances = rs.getInt(1);
        else
            throw new SQLException("Error counting total attendances in course with ID " + this.id);

        // Get total enrollments
        query = "SELECT COUNT(*) " +
                "FROM enrollments " +
                "WHERE CourseID = ?";
        stmt = db.connection.prepareStatement(query);
        stmt.setInt(1, this.id);
        rs = stmt.executeQuery();

        if (rs.next())
            totalEnrollments = rs.getInt(1);
        else
            throw new SQLException("Error counting total enrollments in course with ID " + this.id);

        // Get total classes (total attendance dates)
        query = "SELECT COUNT(DISTINCT AttendanceDate) " +
                "FROM attendances " +
                "WHERE EnrollmentID = ANY (SELECT EnrollmentID " +
                                            "FROM enrollments " +
                                            "WHERE CourseID = ?)";
        stmt = db.connection.prepareStatement(query);
        stmt.setInt(1, this.id);
        rs = stmt.executeQuery();

        if (rs.next())
            totalClasses = rs.getInt(1);
        else
            throw new SQLException("Error counting total classes for course with ID " + this.id);

        if (totalEnrollments == 0 || totalClasses == 0)
            return 0f;
        else
            return ((float)totalAttendances / (totalEnrollments * totalClasses)) * 100;
    }

    public Float getAverageGrade() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();

        String query = "SELECT ROUND(SUM(Grade)/COUNT(*), 2) " +
                "FROM grades " +
                "WHERE EvaluationID = ANY (SELECT EvaluationID " +
                                            "FROM evaluations " +
                                            "WHERE CourseID = ?)";
        PreparedStatement stmt = db.connection.prepareStatement(query);
        stmt.setInt(1, this.id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next())
            return rs.getFloat(1);
        else
            throw new SQLException("Error computing average grade for course with ID " + this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            return ((Course) obj).getID() == id
                    && ((Course) obj).getName().equals(name)
                    && ((Course) obj).getType().equals(type);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Course: %s, ID = %d Type: %s", name, id, type);
    }

    public static class Builder {
        private int id;
        private String name;
        private String type;
        private String professorEmail;

        public Builder(String name, String type, String professorEmail) {
            this.id = 0;
            this.name = name;
            this.type = type;
            this.professorEmail = professorEmail;
        }

        public Builder(String name, String professorEmail) {
            this.name = name;
            this.professorEmail = professorEmail;
            // Fill rest of fields by searching in database
            // using given fields
            try {
                DatabaseConnection db = new DatabaseConnection();
                String query = "SELECT * FROM Courses " +
                        "WHERE CourseName = ? " +
                        "AND ProfessorID = (SELECT ProfessorID FROM Professors WHERE Email = ?)";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, professorEmail);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // NOTE: In the case of multiple courses with the same name and professor email, only the first one is considered
                    this.id = rs.getInt("CourseID");
                    this.type = rs.getString("CourseType");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error constructing course using name and professor email: ", JOptionPane.ERROR_MESSAGE);
            }

        }

        public Course build() {
            return new Course(this);
        }

    }
}
