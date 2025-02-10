package com.westums.models;

import com.westums.models.utils.DatabaseConnection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Professor {

    private int ID;
    private String email;
    private String name;
    private String surname;
    private Date dateOfBirth;

    // Constructor used by builder
    private Professor(Builder builder) {
        this.ID = builder.ID;
        this.email = builder.email;
        this.name = builder.name;
        this.surname = builder.surname;
        this.dateOfBirth = builder.dateOfBirth;
    }

    private Professor(Finder finder) {
        this.ID = finder.ID;
        this.email = finder.email;
        this.name = finder.name;
        this.surname = finder.surname;
        this.dateOfBirth = finder.dateOfBirth;
    }

    // Creates a builder
    public static Builder builder(String name, String surname, Date dateOfBirth) throws SQLException {
        return new Builder(name, surname, dateOfBirth);
    }

    // Creates a finder
    public static Finder finder(String email) {
        return new Finder(email);
    }

    public int getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public ArrayList<String> getCourseNames() throws SQLException {
        ArrayList<String> courseNames = new ArrayList<>();

        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT CourseName FROM Courses WHERE ProfessorID = ?";
        PreparedStatement stmt = db.connection.prepareStatement(query);
        stmt.setInt(1, this.ID);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            courseNames.add(rs.getString("CourseName"));
        }

        return courseNames;
    }

    public static class Builder {
        // Fields must be identical to the Student class
        private int ID;
        private String email;
        private String name;
        private String surname;
        private Date dateOfBirth;

        // Required fields for creating a professor
        public Builder(String name, String surname, Date dateOfBirth) throws SQLException {
            this.name = name;
            this.surname = surname;
            this.dateOfBirth = dateOfBirth;

            // Generate email based on given data
            this.email = Admin.generateProfessorEmail(name, surname, dateOfBirth);
        }

        // Optional fields
        public Builder ID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        // Build the student
        public Professor build() {
            return new Professor(this);
        }
    }

    public static class Finder {
        // Fields must be identical to the Student class
        private int ID;
        private String email;
        private String name;
        private String surname;
        private Date dateOfBirth;

        // Required fields for finding a professor
        public Finder(String email) {
            this.email = email;
            // Fill in object fields with data from the database
            try {
                DatabaseConnection db = new DatabaseConnection();
                String query = "SELECT * FROM Professors WHERE Email = ?";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    this.ID = rs.getInt("ProfessorID");
                    this.name = rs.getString("Name");
                    this.surname = rs.getString("Surname");
                    this.dateOfBirth = rs.getDate("DateOfBirth");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error finding professor using email: ", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Optional fields
        public Finder ID(int ID) {
            // TODO: Search database using ID
            this.ID = ID;
            return this;
        }

        // Create new object using filled fields
        public Professor find() {
            return new Professor(this);
        }
    }
}
