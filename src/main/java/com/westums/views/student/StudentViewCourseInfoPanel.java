package com.westums.views.student;

import com.westums.DatabaseConnection;
import com.westums.models.Enrollable;
import com.westums.models.Student;
import com.westums.uimodels.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentViewCourseInfoPanel extends JPanel {

    public StudentViewCourseInfoPanel(Student student, Enrollable enrollable, ActionListener back) {
        CustomButton backButton = new CustomButton("<-");
        this.add(backButton);
        backButton.addActionListener(back);

        JLabel lbl = new JLabel();
        Integer attendances = getAttendancesOfStudentAtCourse(student, enrollable);
        lbl.setText(String.format(
                "%s, in the course '%s' you have %d attendances"
        ,
                student.name, enrollable.getName(), getAttendancesOfStudentAtCourse(student, enrollable)));
        this.add(lbl, BorderLayout.NORTH);
    }

    Integer getAttendancesOfStudentAtCourse(Student student, Enrollable enrollable) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "SELECT COUNT(*) " +
                    "FROM attendances " +
                    "WHERE studentID = ? "
                    + "AND courseID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, student.ID);
            stmt.setInt(2, enrollable.getID());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch (Exception e) {
            return 0;
        }
        return 0;
    }
}
