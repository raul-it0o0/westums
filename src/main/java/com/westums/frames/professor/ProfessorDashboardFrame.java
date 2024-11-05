package com.westums.frames.professor;

import com.westums.models.Professor;
import com.westums.uimodels.CustomButton;
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
        ProfessorAddEnrollablePanel panelAddEnrollable = new ProfessorAddEnrollablePanel(loggedProfessor, backToDashboard);
        mainPanel.add(panelAddEnrollable, "Add Enrollable");

        btnAddEnrollable = new CustomButton("Add Enrollable");
        panelDashboard.add(btnAddEnrollable);
        btnAddEnrollable.addActionListener(e -> cardLayout.show(mainPanel, "Add Enrollable"));

    }
}
