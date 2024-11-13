package com.westums.views.professor;

import com.westums.models.Professor;
import com.westums.uimodels.CustomButton;
import com.westums.utils.ModelUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfessorDashboardFrame extends JFrame {

    Professor loggedProfessor;
    CardLayout cardLayout;
    CustomButton btnAddEnrollable, btnViewEnrollables, btnTakeAttendance;

    public ProfessorDashboardFrame(Professor loggedProfessor) {
        super("Professor Dashboard");
        this.loggedProfessor = loggedProfessor;

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
        mainPanel.add(panelDashboard, "Professor Dashboard");
        cardLayout.show(mainPanel, "Professor Dashboard");
        ActionListener backToDashboard = e -> cardLayout.show(mainPanel, "Professor Dashboard");
        ProfessorAddEnrollablePanel panelAddEnrollable = new ProfessorAddEnrollablePanel(loggedProfessor,
                backToDashboard, null);
        mainPanel.add(panelAddEnrollable, "Add New Course");
        ProfessorViewEnrollablesPanel panelViewEnrolllables = new ProfessorViewEnrollablesPanel(loggedProfessor, backToDashboard);
        mainPanel.add(panelViewEnrolllables, "View Enrollables");

        btnAddEnrollable = new CustomButton("Add New Course");
        panelDashboard.add(btnAddEnrollable);
        btnAddEnrollable.addActionListener(e -> cardLayout.show(mainPanel, "Add New Course"));

        btnViewEnrollables = new CustomButton("View My Courses");
        panelDashboard.add(btnViewEnrollables);
        btnViewEnrollables.addActionListener(e -> {
            // Check if professor teaches any courses
            if (!loggedProfessor.taughtCourses.isEmpty())
                cardLayout.show(mainPanel, "View Enrollables");
            else
                JOptionPane.showMessageDialog(
                        this,
                        String.format("%s %s, you do not teach any courses!", loggedProfessor.name,
                                loggedProfessor.surname),
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
        });

        JLabel lbl = new JLabel("Professor teaches " + loggedProfessor.taughtCourses.size() + " courses");
        mainPanel.add(lbl);
    }
}
