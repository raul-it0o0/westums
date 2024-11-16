package com.westums.views.student;

import com.westums.models.Professor;
import com.westums.models.Student;
import com.westums.uimodels.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentDashboardFrame extends JFrame {

    Student loggedStudent;
    CardLayout cardLayout;
    CustomButton btnViewMyCourses;

    public StudentDashboardFrame(Student loggedStudent) {
        super("Student Dashboard");
        this.loggedStudent = loggedStudent;

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        cardLayout = new CardLayout();

        JPanel mainPanel = new JPanel(cardLayout);
        mainPanel.setOpaque(false);
        super.add(mainPanel);

        // Initialize the other panels in the cardLayout
        JPanel panelDashboard = new JPanel();
        mainPanel.add(panelDashboard, "Student Dashboard");
        cardLayout.show(mainPanel, "Student Dashboard");

        ActionListener backToDashboard = e -> cardLayout.show(mainPanel, "Student Dashboard");

        StudentViewMyCoursesPanel panelViewMyCourses = new StudentViewMyCoursesPanel(loggedStudent,
                backToDashboard);
        mainPanel.add(panelViewMyCourses, "View Courses");

        // Dashboard UI
        btnViewMyCourses = new CustomButton("View Courses");
        panelDashboard.add(btnViewMyCourses);
        btnViewMyCourses.addActionListener(e -> cardLayout.show(mainPanel, "View Courses"));



    }
}
