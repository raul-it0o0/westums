package com.westums.views.student;

import com.westums.DatabaseConnection;
import com.westums.models.*;
import com.westums.uimodels.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentViewMyCoursesPanel extends JPanel {

    Student loggedStudent;
    CardLayout cardLayout;
    JPanel mainPanel;
    ActionListener backToDashboard, back;

    public StudentViewMyCoursesPanel(Student loggedStudent, ActionListener backToDashboard) {
        this.backToDashboard = backToDashboard;
        this.loggedStudent = loggedStudent;

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        // Add cardLayout panels
        mainPanel = new JPanel();
        this.add(mainPanel, "View Courses");
        cardLayout.show(this, "View Courses");

        // Create ActionListener to return to previous (this) screen
        back = e -> cardLayout.show(this, "View Courses");

        super.setOpaque(false);
        this.loggedStudent = loggedStudent;

        CustomButton backButton = new CustomButton("<-");
        backButton.addActionListener(backToDashboard);
        mainPanel.add(backButton);

        JLabel lbl1 = new JLabel("Choose a course to see more information:");
        mainPanel.add(lbl1, BorderLayout.NORTH);

        // Show the course buttons
        showCourseButtons();

    }

    void showCourseButtons() {
        for (String courseName : loggedStudent.enrolledCourses) {
            CustomButton btn = new CustomButton(courseName);
            btn.addActionListener(e -> {
                Enrollable enrollable = createEnrollable(courseName);
                StudentViewCourseInfoPanel pnlViewCourse =
                        new StudentViewCourseInfoPanel(loggedStudent,
                        enrollable, back);
                this.add(pnlViewCourse, "View Course");
                cardLayout.show(this, "View Course");
            });
            mainPanel.add(btn);
        }
    }

    Enrollable createEnrollable(String name) {
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
