package com.westums.models;

import com.westums.DatabaseConnection;
import com.westums.utils.ModelUpdateListener;

import java.sql.*;
import java.util.ArrayList;

public class Laboratory implements Enrollable {

    public String name;
    public int spots, credits, semester, year;
    public Professor professor;
    private ArrayList<Student> students;
    public final int minimumAttendanceRequirement = (int) (14 * 1.00);
    public ModelUpdateListener modelUpdateListener;
    public boolean hasEnrolledStudents;

    public Laboratory(String name, int spots, int credits, int semester, int year, Professor professor) throws SQLException {
        this.name = name;
        this.spots = spots;
        this.credits = credits;
        this.semester = semester;
        this.year = year;
        this.professor = professor;
        this.hasEnrolledStudents = false;
        this.students = new ArrayList<>();

        // Check DB for enrolled students
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "SELECT * " +
                    "FROM attendances " +
                    "WHERE courseID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setInt(1, this.getID());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.hasEnrolledStudents = true;
            }
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void addStudent(Student student) {
        this.students.add(student);

        if (modelUpdateListener != null) {
            modelUpdateListener.modelUpdated();
        }
    }

    @Override
    public ArrayList<Student> getStudentList() {
        return students;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAvailableSpots() {
        return this.spots;
    }

    @Override
    public int getCredits() {
        return this.credits;
    }

    @Override
    public int getSemester() {
        return this.semester;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public int getID() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT courseID " +
                "FROM courses " +
                "WHERE courseName = ? " +
                "AND courseType = ? ";
        PreparedStatement stmt = db.connection.prepareStatement(query);
        stmt.setString(1, this.name);
        stmt.setString(2, this.getClass().getSimpleName());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("courseID");
        }
        else throw new SQLException();
    }

    @Override
    public void addAttendance(String studentID, Date dateOfAttendance)
    throws SQLException {
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO attendances " +
                    "VALUES (?,?,?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, studentID);
            stmt.setInt(2, this.getID());
            stmt.setDate(3, dateOfAttendance);
        }
        catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean hasEnrolledStudents() {
        return this.hasEnrolledStudents;
    }

    public void addModelUpdateListener(ModelUpdateListener modelUpdateListener) {
        /*
            If a model has a ModelUpdateListener, that means that changes to its state (data members)
            require corresponding GUI (View) changes.
         */
        this.modelUpdateListener = modelUpdateListener;
    }

    @Override
    public void setEnrolledStudents(boolean b) {
        this.hasEnrolledStudents = b;
    }

    @Override
    public Enrollable getEnrollable(String name) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "SELECT * " +
                    "FROM courses " +
                    "WHERE courseName = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return switch (rs.getString("courseType")) {
                    case "Lecture" ->  new Lecture(
                            name,
                            rs.getInt("availableSpots"),
                            rs.getInt("credits"),
                            Integer.valueOf(rs.getString("semester")),
                            Integer.valueOf(rs.getString("year")),
                            null
                    );
                    case "Seminar" -> new Seminar(
                            name,
                            rs.getInt("availableSpots"),
                            rs.getInt("credits"),
                            Integer.valueOf(rs.getString("semester")),
                            Integer.valueOf(rs.getString("year")),
                            null
                    );
                    case "Laboratory" -> new Laboratory(
                            name,
                            rs.getInt("availableSpots"),
                            rs.getInt("credits"),
                            Integer.valueOf(rs.getString("semester")),
                            Integer.valueOf(rs.getString("year")),
                            null
                    );
                    default -> null;
                };
            }
        }
        catch (Exception e) {
            return null;
        }
        return null;
    }
}
