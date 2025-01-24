package com.westums.controllers;

import com.westums.models.Authenticator;
import com.westums.views.LoginPanel;
import com.westums.views.View;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPanelController implements ActionListener, CaretListener {

    LoginPanel view;

    // Helper fields
    private boolean userHadPassword;
    private boolean passwordFetched;
    private String hashedPassword;
    private Authenticator.AccountType accountType;

    public LoginPanelController(Container loginPanelInstance) {

        this.view = (LoginPanel) loginPanelInstance;

        // Helper fields
        userHadPassword = false;
        passwordFetched = false;
        hashedPassword = "";

        registerAsListener();
    }

    private void registerAsListener() {

        // Register as listener
        view.signInButton.addActionListener(this);
        view.signInButton.setActionCommand("Sign In Button Pressed");

        view.passwordField.addActionListener(this);
        view.passwordField.setActionCommand("Password Field Filled");

        view.emailField.addActionListener(this);
        view.emailField.setActionCommand("Email Field Filled");

        view.emailField.addCaretListener(this);

        view.passwordField.addCaretListener(this);
    }

    private void redirectView(Authenticator.AccountType accountType) {
        switch (accountType) {
            case ADMIN:
                MainController.show(View.ADMIN_DASHBOARD);
                break;
            case PROFESSOR:
                MainController.show("Professor Dashboard");
                System.out.println("Professor Dashboard");
                break;
            case STUDENT:
                MainController.show("Student Dashboard");
                System.out.println("Student Dashboard");
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        // Remove any previously displayed error messages
        view.emailErrorLabel.setVisible(false);
        view.passwordErrorLabel.setVisible(false);
        view.revalidate();
        view.repaint();

        if (event.getActionCommand().equals("Sign In Button Pressed") ||
                event.getActionCommand().equals("Email Field Filled") ||
                event.getActionCommand().equals("Password Field Filled")) {

            // Trim any whitespace from email and password fields
            view.emailField.setText(view.emailField.getText().trim());
            view.passwordField.setText(new String(view.passwordField.getPassword()).trim());

            // Error checking for email field
            if (view.emailField.getText().isEmpty()) {
                // Display error message
                view.emailErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
                return;
            }

            // Error checking for password field
            if (view.passwordPanel.isVisible() && view.passwordField.getPassword().length == 0) {
                // Display error message
                view.passwordErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
                return;
            }

            // If password not searched for yet
            if (!passwordFetched) {

                // Check if email exists in the database
                String enteredEmail = view.emailField.getText();
                ArrayList<Object> list;
                try {
                    list = Authenticator.getPasswordAndUserTypeByEmail(enteredEmail);
                    if (list != null) {
                        // If email is found,
                        // get the hashed password and account type
                        hashedPassword = (String) list.get(0);
                        accountType = Authenticator.AccountType.valueOf(String.valueOf(list.get(1)));
                    }
                }
                catch (java.sql.SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If no match found for the entered email
                if (list == null) {
                    // Display error message
                    view.emailErrorLabel.setVisible(true);
                    view.revalidate();
                    view.repaint();
                    return;
                }

                // Match found
                passwordFetched = true;
                // Make email field not editable
                view.emailField.setEditable(false);
                view.passwordPanel.setVisible(true);

                view.passwordField.requestFocus();

                view.emailField.removeActionListener(this);

                // If no password set (found null in DB)
                // Prompt user to add password
                if (hashedPassword == null) {
                    userHadPassword = false;
                    view.inputDescriptionLabel.setVisible(true);
                }
                else {
                    userHadPassword = true;
                }

                view.revalidate();
                view.repaint();
                return;
            }

            // Password already fetched
            String enteredEmail = view.emailField.getText();
            String enteredPassword = new String(view.passwordField.getPassword());
            if (userHadPassword) {
                // Check if entered password matches the hashed password
                if (Authenticator.isMatchingPassword(enteredPassword, hashedPassword)) {
                    redirectView(accountType);
                    return;
                }

                // Display error message
                view.passwordErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
            }
            else {
                // User had no password set
                try {

                    Authenticator.insertPassword(enteredEmail, enteredPassword);
                    redirectView(accountType);
                }
                catch (java.sql.SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

        }
    }

    @Override
    public void caretUpdate(CaretEvent event) {
        // When user clicks on a field, all error messages are not displayed anymore
        if (event.getSource() == view.emailField || event.getSource() == view.passwordField) {
            view.emailErrorLabel.setVisible(false);
            view.passwordErrorLabel.setVisible(false);
            view.revalidate();
            view.repaint();
        }
    }

}
