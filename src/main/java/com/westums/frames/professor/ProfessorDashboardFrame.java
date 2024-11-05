package com.westums.frames.professor;

import com.westums.models.Professor;
import com.westums.uimodels.CustomButton;
import javax.swing.*;
import java.awt.*;

public class ProfessorDashboardFrame extends JFrame {

    Professor loggedProfessor;
    CardLayout cardLayout;
    CustomButton btnAddEnrollable, btnViewEnrollables, btnTakeAttendance;

    public ProfessorDashboardFrame(Professor loggedProfessor) {
        super("Professor Dashboard");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        cardLayout = new CardLayout();

        JPanel mainPanel = new JPanel(cardLayout);
        mainPanel.setOpaque(false);
        super.add(mainPanel);

        // Initialize the other panels in the cardLayout
        ProfessorAddEnrollablePanel panelAddEnrollable = new ProfessorAddEnrollablePanel(loggedProfessor);
        mainPanel.add(panelAddEnrollable);

        btnAddEnrollable = new CustomButton("Add Enrollable");
        mainPanel.add(btnAddEnrollable);
        btnAddEnrollable.addActionListener(e -> cardLayout.show(panelAddEnrollable, "Add Enrollable"));

    }
}
