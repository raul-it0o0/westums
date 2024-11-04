package com.westums.models;

public class Professor {
    public String surname, name, password;
    public java.sql.Date dob;

    public Professor(String surname, String name, java.sql.Date dob, String password) {
        this.surname = surname;
        this.name = name;
        this.dob = dob;
        this.password = password;
    }



}
