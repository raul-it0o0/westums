package com.westums.frames.professor;

import com.westums.models.InputStringNotEmptyVerifier;
import com.westums.models.Professor;
import com.westums.uimodels.CustomButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ProfessorAddEnrollablePanel extends JPanel {

    JLabel lblCourseName, lblSpots, lblCredits, lblSemester, lblYear;
    JFormattedTextField ftfSpots, ftfCourseName;
    JSpinner spnCredits;
    CustomButton btnOK;

    public ProfessorAddEnrollablePanel(Professor professor) {
        super.setOpaque(false);

        lblCourseName = new JLabel("Course Name: ");
        ftfCourseName = new JFormattedTextField();
        ftfCourseName.setColumns(20);
        ftfCourseName.setInputVerifier(new InputStringNotEmptyVerifier());
        super.add(lblCourseName);
        super.add(ftfCourseName);

        lblSpots = new JLabel("Available Spots: ");
        ftfSpots = new JFormattedTextField(NumberFormat.getInstance());
        lblSpots.setLabelFor(ftfSpots);
        // TODO: ^ add this to everything else that is a label to something
        ftfSpots.setColumns(4);
        super.add(lblSpots);
        super.add(ftfSpots);

        lblCredits = new JLabel("Credits: ");
        spnCredits = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        super.add(lblCredits);
        super.add(spnCredits);

        btnOK = new CustomButton("OK");
        super.add(btnOK);
        btnOK.addActionListener(e -> {
            // Make sure fields are not empty
        });

    }


}
