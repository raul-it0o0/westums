package com.westums.views.professor;

import com.westums.models.Enrollable;
import com.westums.uimodels.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfessorViewEnrollableOptionsPanel extends JPanel {

    public ProfessorViewEnrollableOptionsPanel(Enrollable enrollable, ActionListener back) {
        // Display some data about the enrollable
        CardLayout cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        JPanel thisPanel = new JPanel();
        this.add(thisPanel, "View Enrollable");

        ActionListener backToHere = (e -> cardLayout.show(this, "View Enrollable"));

        CustomButton btnTakeAttendance = new CustomButton("Take Attendance");
        btnTakeAttendance.addActionListener(e -> {
            if (!enrollable.hasEnrolledStudents()) {
                ProfessorAddStudentsToEnrollablePanel pnlAddStudents =
                        new ProfessorAddStudentsToEnrollablePanel(enrollable, backToHere);
                this.add(pnlAddStudents, "Enroll Students");
                cardLayout.show(this, "Enroll Students");
            }
            else {
                ProfessorTakeAttendancePanel pnlTakeAttendance =
                        new ProfessorTakeAttendancePanel(enrollable, this, cardLayout);
                this.add(pnlTakeAttendance, "Take Attendance");
                cardLayout.show(this, "Take Attendance");
            }
        });
        thisPanel.add(btnTakeAttendance);

        /*
        CustomButton btnAssignGrades = new CustomButton("Assign Grades");
        btnAssignGrades.addActionListener(e -> {
            ProfessorAssignGradesPanel pnlAssignGrades = new ProfessorTakeAttendanceFrame(enrollable);
            this.add(pnlAssignGrades, "Assign Grades");
            cardLayout.show(this, "Assign Grades");
        });
        this.add(btnAssignGrades);
         */

        CustomButton btnBack = new CustomButton("<-");
        btnBack.addActionListener(back);
        thisPanel.add(btnBack);
    }
}
