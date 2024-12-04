package com.westums.controllers;

import com.westums.models.AccountManager;
import com.westums.views.LoginPanel;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class LoginPanelController implements ActionListener, CaretListener {

    LoginPanel view;
    Consumer<String> showCardMethod;

    // Helper fields
    private boolean userHadPassword;
    private boolean passwordFetched;
    private String hashedPassword;
    private AccountManager.UserType userType;

    public LoginPanelController(LoginPanel loginPanelInstance, Consumer<String> switchCard) {
        this.view = loginPanelInstance;
        this.showCardMethod = switchCard;

        // Helper fields
        userHadPassword = false;
        passwordFetched = false;
        hashedPassword = "";

        // Register this controller as a listener
        //  to the components which need to be listened to
        view.signInButton.addActionListener(this);
        view.signInButton.setActionCommand("Sign In Button Pressed");

        view.emailField.addActionListener(this);
        view.emailField.setActionCommand("Email Field Filled");

        view.passwordField.addActionListener(this);
        view.passwordField.setActionCommand("Password Field Filled");

        view.emailField.addCaretListener(this);
        view.passwordField.addCaretListener(this);

    }

    private void redirectView(AccountManager.UserType userType) {
        switch (userType) {
            case Admin:
                System.out.println("Admin Dashboard");
                showCardMethod.accept("Admin Dashboard");
                break;
            case Professor:
                showCardMethod.accept("Professor Dashboard");
                break;
            case Student:
                showCardMethod.accept("Student Dashboard");
                break;
        }
    }

    // Define behaviour with user interaction
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
            if (view.emailField.getText().isEmpty()) {
                // Display error message
                view.emailErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
                return;
            }

            if (view.passwordPanel.isVisible() && view.passwordField.getPassword().length == 0) {
                // Display error message
                view.passwordErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
                return;
            }

            if (!passwordFetched) {
                String enteredEmail = view.emailField.getText();
                ArrayList<Object> list;
                try {
                    list = AccountManager.isValidEmail(enteredEmail);
                    if (list != null) {
                        hashedPassword = (String) list.get(0);
                        userType = AccountManager.UserType.valueOf(String.valueOf(list.get(1)));
                    }
                }
                catch (java.sql.SQLException SQLException) {
                    new JOptionPane(SQLException.getMessage(), JOptionPane.ERROR_MESSAGE);
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

                view.passwordField.addActionListener(this);
                view.passwordField.requestFocus();
                view.passwordField.setActionCommand("Sign In");

                view.emailField.removeActionListener(this);

                // If no password set (found null in DB)
                // Prompt user to add password
                if (hashedPassword.equals("null")) {
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

            String enteredEmail = view.emailField.getText();
            String enteredPassword = new String(view.passwordField.getPassword());
            if (userHadPassword) {
                if (AccountManager.isValidPassword(enteredPassword, hashedPassword)) {
                    System.out.println("Correct Password");
                    redirectView(userType);
                    return;
                }

                // Display error message
                view.passwordErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
            }
            else {
                try {
                    AccountManager.insertPassword(enteredEmail, enteredPassword);
                    redirectView(userType);
                }
                catch (java.sql.SQLException SQLException) {
                    new JOptionPane(SQLException.getMessage(), JOptionPane.ERROR_MESSAGE);
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
