package com.westums.controllers.admindashboard;

import com.westums.models.Admin;
import com.westums.models.Authenticator;
import com.westums.models.InputVerifier;
import com.westums.views.admindashboard.AddProfessorCard;
import com.westums.views.customui.JAddButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddProfessorController implements ActionListener, DocumentListener, MouseListener, PropertyChangeListener {

    AddProfessorCard view;
    private boolean validFields;

    public boolean hasValidFields() {
        return validFields;
    }

    public AddProfessorController(Container addProfessorCardInstance) {

        this.view = (AddProfessorCard) addProfessorCardInstance;

        // Register as listener
        view.addProfessorButton.addActionListener(this);
        view.addProfessorButton.setActionCommand("Add Professor");

        view.professorNameField.getDocument().addDocumentListener(this);
        view.professorSurnameField.getDocument().addDocumentListener(this);

        view.professorNameField.addMouseListener(this);
        view.professorSurnameField.addMouseListener(this);
        view.professorDateChooser.addMouseListener(this);

        view.professorDateChooser.addPropertyChangeListener(this);

        // Upon view initialization, none of the fields are valid
        validFields = false;
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
        if (!nameValid || !surnameValid) {
            validFields = false;
            return;
        }

        // Generate email and display it
        try {
            view.generatedProfessorEmailField.setText(Admin.generateProfessorEmail(name,
                    surname, birthDate));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Enable add professor button
        view.addProfessorButton.setEnabled(true);
        // Mark fields as valid
        validFields = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        // Event triggered on calendar date change
        verifyAndDisplayProfessorEmail();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Since button was made enabled and pressed,
        //  all fields are filled and valid

        // Get field data
        String email = view.generatedProfessorEmailField.getText().trim();
        String name = view.professorNameField.getText().trim();
        String surname = view.professorSurnameField.getText().trim();
        Date dateOfBirth = view.professorDateChooser.getDate();

        // Add professor to the database
        try {
            Admin.addAccount(email, Authenticator.AccountType.PROFESSOR, name, surname, dateOfBirth);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Change the look of the button to show success
        view.addProfessorButton.setState(JAddButton.SUCCESS_STATE);
        // Clear all fields
        view.professorNameField.setText("");
        view.professorSurnameField.setText("");
        view.generatedProfessorEmailField.setText("");
        // Since fields are cleared, they are not valid
        validFields = false;
        view.revalidate();
        view.repaint();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        insertUpdate(e);
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
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

    @Override
    public void mouseClicked(MouseEvent event) {
        // Reset the button's state if already in success state
        if (view.addProfessorButton.getState() == JAddButton.SUCCESS_STATE)
                view.addProfessorButton.setState(JAddButton.DEFAULT_STATE);

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

    /**
     * Submit changes to the database (simulate pressing the add professor button)
     * @return 0 if successful, 1 if an SQLException occurred
     */
    public int submitChanges() {
        // Assume all fields are valid

        // Get field data
        String email = view.generatedProfessorEmailField.getText().trim();
        String name = view.professorNameField.getText().trim();
        String surname = view.professorSurnameField.getText().trim();
        Date dateOfBirth = view.professorDateChooser.getDate();

        // Add professor to the database
        try {
            Admin.addAccount(email, Authenticator.AccountType.PROFESSOR, name, surname, dateOfBirth);
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
}
