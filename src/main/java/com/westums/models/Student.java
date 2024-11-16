package com.westums.models;

import com.westums.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Student {
    public String ID, surname, name, specialization, password;
    public java.sql.Date dob;
    public Integer year;
    public ArrayList<String> enrolledCourses;

    public Student(String ID, String surname, String name, java.util.Date dob, String specialization, Integer year,
                   String password) {
        this.ID = ID;
        this.surname = surname;
        this.name = name;
        this.dob = new java.sql.Date(dob.getTime());
        this.specialization = specialization;
        this.year = year;
        this.password = password;

        // Search DB for enrolled courses
        enrolledCourses = new ArrayList<>();
        try {
            ArrayList<Integer> courseIDs = new ArrayList<>();
            DatabaseConnection db = new DatabaseConnection();
            String query = "SELECT DISTINCT courseID " +
                        "FROM attendances " +
                        "WHERE studentID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, this.ID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courseIDs.add(rs.getInt("courseID"));
            }

            query = "SELECT courseName " +
                    "FROM courses " +
                    "WHERE courseID = ?";
            stmt = db.connection.prepareStatement(query);

            for (int i = 0; i < courseIDs.size(); i++) {
                stmt.setInt(1, courseIDs.get(i));
                rs = stmt.executeQuery();
                if (rs.next()) {
                    enrolledCourses.add(rs.getString("courseName"));
                }
            }
        }
        catch (Exception e) {
            return;
        }
    }

}
