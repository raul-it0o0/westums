package com.westums.models;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Enrollable {

    public void addStudent(Student student);
    public ArrayList<Student> getStudentList();
    public String getName();
    public int getAvailableSpots();
    public int getCredits();
    public int getSemester();
    public int getYear();
    public int getID() throws SQLException;
    public void addAttendance(String StudentID, Date dateOfAttendance) throws SQLException;
    public boolean hasEnrolledStudents();
    public void setEnrolledStudents(boolean b);

}
