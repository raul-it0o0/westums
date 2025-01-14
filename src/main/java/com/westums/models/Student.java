package com.westums.models;

import java.util.Date;


public class Student {
    // TODO: Better documentation, with doxygen.
    //  Make sure to include usage of the builder pattern in the presentation

    private int ID;
    private String email;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private int numberOfCoursesEnrolled;

    // Constructor used by builder
    private Student(Builder builder) {
        this.ID = builder.ID;
        this.email = builder.email;
        this.name = builder.name;
        this.surname = builder.surname;
        this.dateOfBirth = builder.dateOfBirth;
        this.numberOfCoursesEnrolled = builder.numberOfCoursesEnrolled;
    }

    // Creates a builder
    public static Builder builder(String email, String name, String surname) {
        return new Builder(email, name, surname);
    }

    // Getters
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

    public int getNumberOfCoursesEnrolled() {
        return numberOfCoursesEnrolled;
    }

    public static class Builder {
        // Fields must be identical to the Student class
        private int ID;
        private String email;
        private String name;
        private String surname;
        private Date dateOfBirth;
        private int numberOfCoursesEnrolled;

        // Required fields
        private Builder(String email, String name, String surname) {
            this.email = email;
            this.name = name;
            this.surname = surname;
        }

        private Builder() {}

        public Builder ID(int ID) {
            this.ID = ID;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

}
