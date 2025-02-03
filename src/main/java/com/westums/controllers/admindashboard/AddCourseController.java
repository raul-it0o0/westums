package com.westums.controllers.admindashboard;

import com.westums.models.Admin;
import com.westums.models.Authenticator;
import com.westums.models.InputVerifier;
import com.westums.views.admindashboard.AddCourseCard;
import com.westums.views.customui.JAddButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;

public class AddCourseController implements ActionListener, DocumentListener, MouseListener {

    AddCourseCard view;
    private boolean validFields;

    public boolean hasValidFields() {
        return validFields;
    }

    public AddCourseController(Container addCourseCardInstance) {

        this.view = (AddCourseCard) addCourseCardInstance;

        // Register as listener
        view.addCourseButton.addActionListener(this);
        view.addCourseButton.setActionCommand("Add Course");

        view.courseNameField.getDocument().addDocumentListener(this);
        view.courseProfessorEmailField.getDocument().addDocumentListener(this);

        view.courseNameField.addMouseListener(this);
        view.courseProfessorEmailField.addMouseListener(this);

        view.courseTypeComboBox.addActionListener(this);
        view.courseTypeComboBox.setActionCommand("Course type selected");

        // Upon view initialization, none of the fields are valid
        validFields = false;
    }

    /**
     * Check all the fields of the Add Course card for validity
     */
    private void verifyAddCourseFields() {
        // Check if course name is valid, display error label is not
        // Search for the professor email in the database, display appropriate label
        // Enable or disable the add course button based on the validity of the fields

        // Get field data and preprocess
        String name = view.courseNameField.getText().trim();
        String professorEmail = view.courseProfessorEmailField.getText().trim();
        boolean courseTypeSelected = (view.courseTypeComboBox.getSelectedItem() != null);

        boolean nameValid = InputVerifier.isValidCourseName(name);
        boolean professorEmailValid = true;

        if (!nameValid) {
            // Display error label
            view.courseNameErrorLabel.setVisible(true);
            // Disable add course button
            view.addCourseButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }

        if (professorEmail.isEmpty()) {
            professorEmailValid = false;
            // Display `Invalid email` error label
            view.courseProfessorEmailInvalidLabel.setVisible(true);
            // Disable add course button
            view.addCourseButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }

        // NOTE: The professorEmailValid variable is part of the condition so as to prevent unnecessary
        //  searching in the database if it was already set to
        //  false due to the email field being empty
        try {
            if (professorEmailValid && !Admin.existsProfessorWithEmail(professorEmail)) {
                professorEmailValid = false;
                // Display `Professor not found` error label
                view.courseProfessorEmailNotFoundLabel.setVisible(true);
                // Disable add course button
                view.addCourseButton.setEnabled(false);

                view.revalidate();
                view.repaint();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (professorEmailValid) {
            // Display `Found` label
            view.courseProfessorEmailFoundLabel.setVisible(true);

            view.revalidate();
            view.repaint();
        }

        if (!courseTypeSelected) {
            // Display `Please choose a course type!` label
            view.courseTypeErrorLabel.setVisible(true);

            view.revalidate();
            view.repaint();
        }

        // To enable the add course button, all fields must be valid
        if (!nameValid
                || !professorEmailValid
                || !courseTypeSelected) {
            validFields = false;
            return;
        }

        // Enable add course button
        view.addCourseButton.setEnabled(true);
        // Mark fields as valid
        validFields = true;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Course type selected")) {
            // Reset the button's state if already in success state
            if (view.addCourseButton.getState() == JAddButton.SUCCESS_STATE)
                view.addCourseButton.setState(JAddButton.DEFAULT_STATE);

            // Clear the error label
            view.courseTypeErrorLabel.setVisible(false);

            verifyAddCourseFields();
        }
        else if (event.getActionCommand().equals("Add Course")) {
            // Get field data
            String courseName = view.courseNameField.getText().trim();
            String professorEmail = view.courseProfessorEmailField.getText().trim();
            String courseType = (String) view.courseTypeComboBox.getSelectedItem();

            // Add course to the database
            try {
                Admin.addCourse(courseName, professorEmail, courseType);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Change the look of the button to show success
            view.addCourseButton.setState(JAddButton.SUCCESS_STATE);
            // Clear all fields
            view.courseNameField.setText("");
            view.courseProfessorEmailField.setText("");
            // Since fields are cleared, they are not valid
            validFields = false;
            view.revalidate();
            view.repaint();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        // Clear the label on the field the document change came from
        if (event.getDocument().equals(view.courseNameField.getDocument()))
            view.courseNameErrorLabel.setVisible(false);

        else if (event.getDocument().equals(view.courseProfessorEmailField.getDocument())) {
            view.courseProfessorEmailInvalidLabel.setVisible(false);
            view.courseProfessorEmailNotFoundLabel.setVisible(false);
            view.courseProfessorEmailFoundLabel.setVisible(false);
        }

        view.revalidate();
        view.repaint();

        verifyAddCourseFields();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Reset the button's state if already in success state
        if (view.addCourseButton.getState() == JAddButton.SUCCESS_STATE)
            view.addCourseButton.setState(JAddButton.DEFAULT_STATE);

        // If neither error label is visible, return
        if (!view.courseNameErrorLabel.isVisible()
                && !view.courseProfessorEmailInvalidLabel.isVisible()
                && !view.courseProfessorEmailNotFoundLabel.isVisible()
                && !view.courseProfessorEmailFoundLabel.isVisible()
                && !view.courseTypeErrorLabel.isVisible()) return;

        // Clear the label on the field where the mouse was clicked
        if (event.getSource() == view.courseNameField) {
            view.courseNameErrorLabel.setVisible(false);
        }

        else if (event.getSource() == view.courseProfessorEmailField) {
            view.courseProfessorEmailInvalidLabel.setVisible(false);
            view.courseProfessorEmailNotFoundLabel.setVisible(false);
            view.courseProfessorEmailFoundLabel.setVisible(false);
        }

        view.revalidate();
        view.repaint();
    }

    /**
     * Submit changes to the database (simulate pressing the add course button)
     * @return 0 if successful, 1 if an SQLException occurred
     */
    public int submitChanges() {
        // Assume all fields are valid

        // Get field data
        String courseName = view.courseNameField.getText().trim();
        String professorEmail = view.courseProfessorEmailField.getText().trim();
        String courseType = (String) view.courseTypeComboBox.getSelectedItem();

        // Add course to the database
        try {
            Admin.addCourse(courseName, professorEmail, courseType);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return 1;
        }

        return 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
