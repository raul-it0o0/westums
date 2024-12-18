package com.westums.controllers;

import com.westums.models.DatabaseManager;
import com.westums.models.InputVerifier;
import com.westums.views.AdminDashboard;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

public class AdminDashboardController implements ActionListener, TreeSelectionListener, MouseListener,
        PropertyChangeListener, DocumentListener, ComponentListener  {

    AdminDashboard view;
    Consumer<String> showCardMethod;


    AdminDashboardController(AdminDashboard adminDashboardInstance, Consumer<String> switchCard) {
        this.view = adminDashboardInstance;
        this.showCardMethod = switchCard;

        view.optionTree.addTreeSelectionListener(this);
        view.logoutPanel.addMouseListener(this);

        registerAsListener();
    }

    private void registerAsListener() {
        // Register this controller as a listener
        //  to the components which need to be listened to

        // View
        view.addComponentListener(this);

        // Add Student Card components:
        view.addStudentButton.addActionListener(this);
        view.addStudentButton.setActionCommand("Add Student");

        view.studentNameField.getDocument().addDocumentListener(this);
        view.studentSurnameField.getDocument().addDocumentListener(this);

        view.studentNameField.addMouseListener(this);
        view.studentSurnameField.addMouseListener(this);
        view.studentDateChooser.addMouseListener(this);

        view.studentDateChooser.addPropertyChangeListener(this);

        // Add Professor Card components:
        view.addProfessorButton.addActionListener(this);
        view.addProfessorButton.setActionCommand("Add Professor");

        view.professorNameField.getDocument().addDocumentListener(this);
        view.professorSurnameField.getDocument().addDocumentListener(this);

        view.professorNameField.addMouseListener(this);
        view.professorSurnameField.addMouseListener(this);
        view.professorDateChooser.addMouseListener(this);

        view.professorDateChooser.addPropertyChangeListener(this);

        // Add Course Card components:
        view.addCourseButton.addActionListener(this);
        view.addCourseButton.setActionCommand("Add Course");

        view.courseNameField.getDocument().addDocumentListener(this);
        view.courseProfessorEmailField.getDocument().addDocumentListener(this);

        view.courseNameField.addMouseListener(this);
        view.courseProfessorEmailField.addMouseListener(this);

        view.courseTypeComboBox.addActionListener(this);
        view.courseTypeComboBox.setActionCommand("Course type selected");
    }

    @Override
    public void componentHidden(ComponentEvent event) {
        if (event.getSource() == view) {
            resetViewFields();
        }
    }

    private void resetViewFields() {
        // Set the card layout to the first card
        view.optionTree.clearSelection();
        view.showCard("Default Card");

        // Add Student Card fields:
        view.studentNameField.setText(null);
        view.studentSurnameField.setText(null);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        view.studentDateChooser.getDateEditor().setDate(calendar.getTime());
        view.generatedStudentEmailField.setText("");
        // Clear error labels
        view.studentNameErrorLabel.setVisible(false);
        view.studentSurnameErrorLabel.setVisible(false);
        // Reset button
        view.setButtonState(view.addStudentButton, AdminDashboard.ButtonState.ADD);

        // Add Professor Card fields:
        view.professorNameField.setText(null);
        view.professorSurnameField.setText(null);
        calendar.set(2000, Calendar.JANUARY, 1);
        view.professorDateChooser.getDateEditor().setDate(calendar.getTime());
        view.generatedProfessorEmailField.setText("");
        // Clear error labels
        view.professorNameErrorLabel.setVisible(false);
        view.professorSurnameErrorLabel.setVisible(false);
        // Reset button
        view.setButtonState(view.addProfessorButton, AdminDashboard.ButtonState.ADD);

        // Add Course Card fields:
        view.courseNameField.setText(null);
        view.courseProfessorEmailField.setText(null);
        view.courseTypeComboBox.setSelectedItem(null);
        // Clear error labels
        view.courseNameErrorLabel.setVisible(false);
        view.courseProfessorEmailInvalidLabel.setVisible(false);
        view.courseProfessorEmailNotFoundLabel.setVisible(false);
        view.courseProfessorEmailFoundLabel.setVisible(false);
        view.courseTypeErrorLabel.setVisible(false);
        // Reset button
        view.setButtonState(view.addCourseButton, AdminDashboard.ButtonState.ADD);

        view.revalidate();
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Different actions based on which button was clicked
        if (event.getActionCommand().equals("Add Student")) {
            // Since button was made enabled and pressed,
            //  all fields are filled and valid

            // Get field data
            String email = view.generatedStudentEmailField.getText().trim();
            String name = view.studentNameField.getText().trim();
            String surname = view.studentSurnameField.getText().trim();
            Date birthDate = view.studentDateChooser.getDate();

            // Add student to the database
            try {
                DatabaseManager.createAccount(email, DatabaseManager.AccountType.STUDENT);
                DatabaseManager.addStudent(email, name, surname, birthDate);
            } catch (SQLException e) {
                new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Change the look of the button to show success
            view.setButtonState(view.addStudentButton, AdminDashboard.ButtonState.ADDED);
            // Clear all fields
            view.studentNameField.setText("");
            view.studentSurnameField.setText("");
            view.generatedStudentEmailField.setText("");
            view.revalidate();
            view.repaint();
        }
        else if (event.getActionCommand().equals("Add Professor")) {
            // Since button was made enabled and pressed,
            //  all fields are filled and valid

            // Get field data
            String email = view.generatedProfessorEmailField.getText().trim();
            String name = view.professorNameField.getText().trim();
            String surname = view.professorSurnameField.getText().trim();
            Date birthDate = view.professorDateChooser.getDate();

            // Add professor to the database
            try {
                DatabaseManager.createAccount(email, DatabaseManager.AccountType.PROFESSOR);
                DatabaseManager.addProfessor(email, name, surname, birthDate);
            } catch (SQLException e) {
                new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Change the look of the button to show success
            view.setButtonState(view.addProfessorButton, AdminDashboard.ButtonState.ADDED);
            // Clear all fields
            view.professorNameField.setText("");
            view.professorSurnameField.setText("");
            view.generatedProfessorEmailField.setText("");
            view.revalidate();
            view.repaint();
        }
        else if (event.getActionCommand().equals("Course type selected")) {
            // Reset the button's state if already in success state
            view.setButtonState(view.addCourseButton, AdminDashboard.ButtonState.ADD);
            // Clear the error label
            view.courseTypeErrorLabel.setVisible(false);

            verifyAddCourseFields();
        }
        else if (event.getActionCommand().equals("Add Course")) {
            // Get field data
            String courseName = view.courseNameField.getText().trim();
            String professorEmail = view.courseProfessorEmailField.getText().trim();
            String courseType = (String)view.courseTypeComboBox.getSelectedItem();

            // Add course to the database
            try {
                DatabaseManager.addCourse(courseName, professorEmail, courseType);
            } catch (SQLException e) {
                new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Change the look of the button to show success
            view.setButtonState(view.addCourseButton, AdminDashboard.ButtonState.ADDED);
            // Clear all fields
            view.courseNameField.setText("");
            view.courseProfessorEmailField.setText("");
            view.revalidate();
            view.repaint();
        }
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
            if (professorEmailValid && !DatabaseManager.isValidProfessorEmail(professorEmail)) {
                professorEmailValid = false;
                // Display `Professor not found` error label
                view.courseProfessorEmailNotFoundLabel.setVisible(true);
                // Disable add course button
                view.addCourseButton.setEnabled(false);

                view.revalidate();
                view.repaint();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                || !courseTypeSelected) return;

        // Enable add course button
        view.addCourseButton.setEnabled(true);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        // Get the name of the selected node (readability)
        String selectedNodeName = e.getPath().getLastPathComponent().toString();

        switch (selectedNodeName) {
            case "Add Student" -> {
                view.showCard("Add Student Card");
                return;
            }
            case "Add Professor" -> {
                view.showCard("Add Professor Card");
                return;
            }
            case "Add Course" -> {
                view.showCard("Add Course Card");
                return;
            }
            default -> {
                view.showCard("Default Card");
                return;
            }
        }
    }

    /**
     * Checks, based on the currently selected JList node, if the card is visible
     * @param cardName The name of the card to check if visible
     * @return True if the card is visible, false otherwise
     */
    private boolean isVisibleCard(String cardName) {
        String treeNodeName = cardName.replace(" Card", "");
        if (view.optionTree.getLastSelectedPathComponent() == null) return false;
        return (view.optionTree.getLastSelectedPathComponent().toString().equals(treeNodeName));
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == view.logoutPanel) {
            showCardMethod.accept("Login Panel");
            return;
        }

        // Different actions based on which card is currently visible
        if (isVisibleCard("Add Student Card")) {
            // Reset the button's state if already in success state
            view.setButtonState(view.addStudentButton, AdminDashboard.ButtonState.ADD);

            // If neither error label is visible, return
            if (!view.studentNameErrorLabel.isVisible()
                    && !view.studentSurnameErrorLabel.isVisible()) return;

            // Check which field was clicked, to remove its error label
            if (event.getSource() == view.studentNameField) {
                view.studentNameErrorLabel.setVisible(false);

                view.revalidate();
                view.repaint();

                return;
            }

            if (event.getSource() == view.studentSurnameField) {
                view.studentSurnameErrorLabel.setVisible(false);

                view.revalidate();
                view.repaint();

                return;
            }
        }
        else if (isVisibleCard("Add Professor Card")) {
            // Reset the button's state if already in success state
            view.setButtonState(view.addProfessorButton, AdminDashboard.ButtonState.ADD);
            // If neither error label is visible, return
            if (!view.professorNameErrorLabel.isVisible()
                    && !view.professorSurnameErrorLabel.isVisible()) return;

            // Check which field was clicked, to remove its error label
            if (event.getSource() == view.professorNameField) {
                view.professorNameErrorLabel.setVisible(false);

                view.revalidate();
                view.repaint();

                return;
            }

            if (event.getSource() == view.professorSurnameField) {
                view.professorSurnameErrorLabel.setVisible(false);

                view.revalidate();
                view.repaint();

                return;
            }
        }
        else if (isVisibleCard("Add Course Card")) {
            // Reset the button's state if already in success state
            view.setButtonState(view.addCourseButton, AdminDashboard.ButtonState.ADD);

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


    }

    /**
     * Check the fields for validity and
     * display error labels upon invalid input or display the generated email
     */
    private void verifyAndDisplayStudentEmail() {
        // Get field data and preprocess
        String name = view.studentNameField.getText().trim();
        String surname = view.studentSurnameField.getText().trim();
        Date birthDate = view.studentDateChooser.getDate();

        boolean nameValid = InputVerifier.isValidName(name);
        boolean surnameValid = InputVerifier.isValidName(surname);

        if (!nameValid) {
            // Display error label
            System.out.println("Displaying error label on student name");
            view.studentNameErrorLabel.setVisible(true);
            // Clear generated email field
            view.generatedStudentEmailField.setText("");
            // Disable add student button
            view.addStudentButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }

        if (!surnameValid) {
            // Display error label
            view.studentSurnameErrorLabel.setVisible(true);
            // Clear generated email field
            view.generatedStudentEmailField.setText("");
            // Disable add student button
            view.addStudentButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }

        // To generate email, both name and surname fields must be valid
        if (!nameValid || !surnameValid) return;

        // Generate email and display it
        try {
            view.generatedStudentEmailField.setText(DatabaseManager.generateStudentEmail(name,
                    surname, birthDate));
        } catch (SQLException e) {
            new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Enable add student button
        view.addStudentButton.setEnabled(true);
    }

    /**
     * Check the fields for validity and
     * display error labels upon invalid input or display the generated email
     */
    private void verifyAndDisplayProfessorEmail() {
        // Get field data and preprocess
        String name = view.professorNameField.getText().trim();
        String surname = view.professorSurnameField.getText().trim();
        Date birthDate = view.professorDateChooser.getDate();

        boolean nameValid = InputVerifier.isValidName(name);
        boolean surnameValid = InputVerifier.isValidName(surname);

        if (!nameValid) {
            System.out.println("Displaying error label on professor name");
            // Display error label
            view.professorNameErrorLabel.setVisible(true);
            // Clear generated email field
            view.generatedProfessorEmailField.setText("");
            // Disable add student button
            view.addProfessorButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }

        if (!surnameValid) {
            // Display error label
            view.professorSurnameErrorLabel.setVisible(true);
            // Clear generated email field
            view.generatedProfessorEmailField.setText("");
            // Disable add student button
            view.addProfessorButton.setEnabled(false);

            view.revalidate();
            view.repaint();
        }

        // To generate email, both name and surname fields must be valid
        if (!nameValid || !surnameValid) return;

        // Generate email and display it
        try {
            view.generatedProfessorEmailField.setText(DatabaseManager.generateProfessorEmail(name,
                    surname, birthDate));
        } catch (SQLException e) {
            new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Enable add professor button
        view.addProfessorButton.setEnabled(true);
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        // The document change came from the currently visible card

        // Determine which card is visible
        if (isVisibleCard("Add Student Card")) {
            // Clear the error label on the field the document change came from
            if (event.getDocument().equals(view.studentNameField.getDocument()))
                view.studentNameErrorLabel.setVisible(false);

            else if (event.getDocument().equals(view.studentSurnameField.getDocument()))
                view.studentSurnameErrorLabel.setVisible(false);

            view.revalidate();
            view.repaint();

            // Verify fields and display email upon validity
            verifyAndDisplayStudentEmail();
        }
        else if (isVisibleCard("Add Professor Card")) {
            // Clear the error label on the field the document change came from
            if (event.getDocument().equals(view.professorNameField.getDocument()))
                view.professorNameErrorLabel.setVisible(false);

            else if (event.getDocument().equals(view.professorSurnameField.getDocument()))
                view.professorSurnameErrorLabel.setVisible(false);

            view.revalidate();
            view.repaint();

            // Verify fields and display email upon validity
            verifyAndDisplayProfessorEmail();
        }
        else if (isVisibleCard("Add Course Card")) {
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
    }

    // Redirect to the same method as insertUpdate
    @Override
    public void removeUpdate(DocumentEvent event) {insertUpdate(event);}

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        // Event triggered on calendar date change
        // The date change came from the currently visible card

        if (isVisibleCard("Add Student Card")) {
            verifyAndDisplayStudentEmail();
        }
        else if (isVisibleCard("Add Professor Card")) {
            verifyAndDisplayProfessorEmail();
        }
    }

    // Empty methods
    @Override
    public void mousePressed(MouseEvent e) {return;}
    @Override
    public void mouseReleased(MouseEvent e) {return;}
    @Override
    public void mouseEntered(MouseEvent e) {return;}
    @Override
    public void mouseExited(MouseEvent e) {return;}
    @Override
    public void changedUpdate(DocumentEvent e) {return;}

    @Override
    public void componentResized(ComponentEvent e) {return;}

    @Override
    public void componentMoved(ComponentEvent e) {return;}

    @Override
    public void componentShown(ComponentEvent e) {return;}
}
