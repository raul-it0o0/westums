package com.westums.controllers.admindashboard;

import com.westums.models.Admin;
import com.westums.models.Authenticator;
import com.westums.models.InputVerifier;
import com.westums.views.admindashboard.AddStudentCard;
import com.westums.views.customui.JAddButton;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Date;

public class AddStudentController implements ActionListener, DocumentListener, MouseListener, PropertyChangeListener {

    AddStudentCard view;
    private boolean validFields;

    public boolean hasValidFields() {
        return validFields;
    }

    public AddStudentController(Container addStudentCardInstance) {

        this.view = (AddStudentCard) addStudentCardInstance;

        // Register as listener
        view.addStudentButton.addActionListener(this);
        view.addStudentButton.setActionCommand("Add Student");

        view.studentNameField.getDocument().addDocumentListener(this);
        view.studentSurnameField.getDocument().addDocumentListener(this);

        view.studentNameField.addMouseListener(this);
        view.studentSurnameField.addMouseListener(this);
        view.studentDateChooser.addMouseListener(this);

        view.studentDateChooser.addPropertyChangeListener(this);

        // Upon view initialization, none of the fields are valid
        validFields = false;

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Add Student button was pressed
        // Since button was made enabled and pressed,
        //  all fields are filled and valid

        // Get field data
        String email = view.generatedStudentEmailField.getText().trim();
        String name = view.studentNameField.getText().trim();
        String surname = view.studentSurnameField.getText().trim();
        Date dateOfBirth = view.studentDateChooser.getDate();

        // Add student to the database
        try {
            Admin.addAccount(email, Authenticator.AccountType.STUDENT, name, surname, dateOfBirth);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Change the look of the button to show success
        view.addStudentButton.setState(JAddButton.SUCCESS_STATE);
        // Clear all fields
        view.studentNameField.setText("");
        view.studentSurnameField.setText("");
        view.generatedStudentEmailField.setText("");
        // Since fields are cleared, they are not valid
        validFields = false;
        view.revalidate();
        view.repaint();
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

        boolean nameValid = com.westums.models.InputVerifier.isValidName(name);
        boolean surnameValid = InputVerifier.isValidName(surname);

        if (!nameValid) {
            // Display error label
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
        if (!nameValid || !surnameValid) {
            validFields = false;
            return;
        }

        // Generate email and display it
        try {
            view.generatedStudentEmailField.setText(Admin.generateStudentEmail(name,
                    surname, birthDate));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Enable add student button
        view.addStudentButton.setEnabled(true);
        // Mark fields as valid
        validFields = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Event triggered on calendar date change
        verifyAndDisplayStudentEmail();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void insertUpdate(DocumentEvent event) {

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

    @Override
    public void mouseClicked(MouseEvent event) {
        // Reset the button's state if already in success state
        if (view.addStudentButton.getState() == JAddButton.SUCCESS_STATE)
            view.addStudentButton.setState(JAddButton.DEFAULT_STATE);

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

    /**
     * Submit changes to the database (simulate pressing the add student button)
     * @return 0 if successful, 1 if an SQLException occurred
     */
    public int submitChanges() {
        // Assume all fields are valid

        // Get field data
        String email = view.generatedStudentEmailField.getText().trim();
        String name = view.studentNameField.getText().trim();
        String surname = view.studentSurnameField.getText().trim();
        Date dateOfBirth = view.studentDateChooser.getDate();

        // Add student to the database
        try {
            Admin.addAccount(email, Authenticator.AccountType.STUDENT, name, surname, dateOfBirth);
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
    public void changedUpdate(DocumentEvent e) {

    }
}
