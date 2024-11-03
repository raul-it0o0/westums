package com.westums.models;

public class Professor {
    public String professorID, surname, name, password;
    public java.sql.Date dob;

    public Professor(String professorID, String surname, String name, java.util.Date dob, String password) {
        this.professorID = professorID;
        this.surname = surname;
        this.name = name;
        this.dob = new java.sql.Date(dob.getTime());
        this.password = password;
    }



}
