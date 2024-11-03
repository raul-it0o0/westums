package com.westums.models;

public class Student {
    public String ID, surname, name, specialization, password;
    public java.sql.Date dob;
    public Integer year;

    public Student(String ID, String surname, String name, java.util.Date dob, String specialization, Integer year,
                   String password) {
        this.ID = ID;
        this.surname = surname;
        this.name = name;
        this.dob = new java.sql.Date(dob.getTime());
        this.specialization = specialization;
        this.year = year;
        this.password = password;
    }
}
