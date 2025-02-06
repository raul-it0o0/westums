package com.westums.models;

import java.sql.SQLException;
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

    // Creates a builder
    public static Builder builder(String name, String surname, Date dateOfBirth) throws SQLException {
        return new Builder(name, surname, dateOfBirth);
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
}
