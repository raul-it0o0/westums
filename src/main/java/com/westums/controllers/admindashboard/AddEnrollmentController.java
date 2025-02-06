package com.westums.controllers.admindashboard;

import com.westums.models.Admin;
import com.westums.models.Course;
import com.westums.models.uimodels.NonSelectableListSelectionModel;
import com.westums.views.admindashboard.AddEnrollmentCard;
import com.westums.views.customui.JAddButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEnrollmentController implements MouseListener, DocumentListener, ActionListener, ListSelectionListener {

    AddEnrollmentCard view;

    /**
     * Becomes true when email is filled, and any changes are made to the student's enrollments.
     */
    private boolean validFields;

    public boolean hasValidFields() {
        return validFields;
    }

    private DefaultListSelectionModel selectableListSelectionModel;
    private NonSelectableListSelectionModel nonSelectableListSelectionModel;

    public AddEnrollmentController(Container addEnrollmentCardInstance) {

        this.view = (AddEnrollmentCard) addEnrollmentCardInstance;

        // Load courses in list
        fetchAndDisplayCourses();

        // Register as a listener
        view.studentEnrollmentEmailField.addMouseListener(this);
        view.studentEnrollmentEmailField.getDocument().addDocumentListener(this);

        view.refreshCourseListButton.addActionListener(this);
        view.refreshCourseListButton.setActionCommand("Refresh course list");

        view.addEnrollmentButton.addActionListener(this);
        view.addEnrollmentButton.setActionCommand("Add Enrollment");

        // Save the list selection model that makes the course list selectable
        this.selectableListSelectionModel = (DefaultListSelectionModel) view.courseList.getSelectionModel();

        // Make course list non-selectable
        this.nonSelectableListSelectionModel = new NonSelectableListSelectionModel();
        view.courseList.setSelectionModel(nonSelectableListSelectionModel);

        // Upon view initialization, none of the fields are valid
        validFields = false;

    }

    private void fetchAndDisplayCourses() {
        ArrayList<Course> availableCourses;
        try {
            availableCourses = Admin.fetchAvailableCourses();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Course course : availableCourses) {
            if (!view.courseListModel.contains(course))
                view.courseListModel.addElement(course);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Refresh course list")) {
            // Fetch and display courses
            fetchAndDisplayCourses();
        }
        else if (event.getActionCommand().equals("Add Enrollment")) {
            String studentEmail = view.studentEnrollmentEmailField.getText().trim();
            try {
                Admin.updateEnrollments(Admin.getStudentIDWithEmail(studentEmail),
                        view.courseList.getSelectedValuesList());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Change the look of the button to show success
            view.addEnrollmentButton.setState(JAddButton.SUCCESS_STATE);
            // Clear all fields
            view.studentEnrollmentEmailField.setText("");
            view.courseList.clearSelection();
            // Since fields are cleared, no modifications are made
            // to keep the user from switching views or logging out
            validFields = false;
            view.revalidate();
            view.repaint();
        }
    }

    private void updateCourseSelection(int studentID) {
        // Update available courses (and display them)
        fetchAndDisplayCourses();
        view.courseList.clearSelection();
        // Make list selectable
        view.courseList.setSelectionModel(selectableListSelectionModel);
        view.courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Get the courses the student is enrolled in
        ArrayList<Course> studentEnrollments;
        try {
            studentEnrollments = Admin.fetchStudentEnrollments(studentID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Course course : studentEnrollments) {
            view.courseList.addSelectionInterval(view.courseListModel.indexOf(course),
                    view.courseListModel.indexOf(course));
        }

        // Listen for any selection changes beyond this point (after pre-selecting)
        view.courseList.addListSelectionListener(this);
    }

    private void checkStudentEnrollmentEmailField() {
        String studentEmail = view.studentEnrollmentEmailField.getText().trim();
        int studentID = 0;

        if (studentEmail.isEmpty()) {
            // Display `Invalid email` label
            view.studentEnrollmentEmailInvalidLabel.setVisible(true);
            // Unselect any selected courses
            view.courseList.clearSelection();
            // Disable selection
            view.courseList.setSelectionModel(nonSelectableListSelectionModel);
            // Disable add enrollment button
            view.addEnrollmentButton.setEnabled(false);

            view.revalidate();
            view.repaint();
            return;
        }
        else {
            if (view.studentEnrollmentEmailInvalidLabel.isVisible())
                view.studentEnrollmentEmailInvalidLabel.setVisible(false);
        }

        try {
            studentID = Admin.getStudentIDWithEmail(studentEmail);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (studentID == 0) {
            // Display `Student not found` label
            view.studentEnrollmentEmailNotFoundLabel.setVisible(true);
            // Unselect any selected courses
            view.courseList.clearSelection();
            // Disable selection
            view.courseList.setSelectionModel(nonSelectableListSelectionModel);
            // Disable add enrollment button
            view.addEnrollmentButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }
        else {
            // Student found
            // Display `Student found` label
            view.studentEnrollmentEmailFoundLabel.setVisible(true);
            updateCourseSelection(studentID);
            // Enable selection
            view.courseList.setSelectionModel(selectableListSelectionModel);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        // Stop listening for changes in the list selection
        view.courseList.removeListSelectionListener(this);
        // The modifications of the field should not
        // keep the user from switching views or logging out
        validFields = false;

        // Clear the label on the field the document change came from
        if (event.getDocument().equals(view.studentEnrollmentEmailField.getDocument())) {
            view.studentEnrollmentEmailNotFoundLabel.setVisible(false);
            view.studentEnrollmentEmailFoundLabel.setVisible(false);
        }

        view.revalidate();
        view.repaint();

        checkStudentEnrollmentEmailField();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Reset the button's state if already in success state
        // view.setButtonState(view.addEnrollmentButton, AdminDashboard.ButtonState.NEUTRAL);

        // If neither error label is visible, return
        if (!view.studentEnrollmentEmailNotFoundLabel.isVisible()
                && !view.studentEnrollmentEmailFoundLabel.isVisible()) return;

        // Clear the label on the field where the mouse was clicked
        if (event.getSource() == view.studentEnrollmentEmailField) {
            view.studentEnrollmentEmailNotFoundLabel.setVisible(false);
            view.studentEnrollmentEmailFoundLabel.setVisible(false);
        }

        view.revalidate();
        view.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // When any value in the list is selected or deselected
        // Mark the fields as modified
        validFields = true;
        // Enable add enrollment button, if not enabled already
        if (!view.addEnrollmentButton.isEnabled())
            view.addEnrollmentButton.setEnabled(true);
    }

    /**
     * Submit changes to the database (simulate pressing the add enrollment button)
     * @return 0 if successful, 1 if an SQLException occurred
     */
    public int submitChanges() {
        // Assume all fields are valid

        // Get field data
        String studentEmail = view.studentEnrollmentEmailField.getText().trim();

        // Update enrollments
        try {
            Admin.updateEnrollments(Admin.getStudentIDWithEmail(studentEmail),
                    view.courseList.getSelectedValuesList());
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
    public void changedUpdate(DocumentEvent e) {}
}
