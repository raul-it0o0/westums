package com.westums.controllers;

import com.westums.models.DatabaseManager;
import com.westums.views.LoginPanel;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.function.Consumer;

public class LoginPanelController implements ActionListener, CaretListener, ComponentListener {

    LoginPanel view;
    Consumer<String> showCardMethod;

    // Helper fields
    private boolean userHadPassword;
    private boolean passwordFetched;
    private String hashedPassword;
    private DatabaseManager.AccountType accountType;

    public LoginPanelController(LoginPanel loginPanelInstance, Consumer<String> switchCard) {
        this.view = loginPanelInstance;
        this.showCardMethod = switchCard;

        // Helper fields
        userHadPassword = false;
        passwordFetched = false;
        hashedPassword = "";

        registerAsListener();
    }

    private void registerAsListener() {
        // Register this controller as a listener
        //  to the components which need to be listened to
        if (!Controller.isListenerOf(this, view.signInButton, ActionListener.class)) {
            view.signInButton.addActionListener(this);
            view.signInButton.setActionCommand("Sign In Button Pressed");
        }

        if (!Controller.isListenerOf(this, view.passwordField, ActionListener.class)) {
            view.passwordField.addActionListener(this);
            view.passwordField.setActionCommand("Password Field Filled");
        }

        if (!Controller.isListenerOf(this, view.emailField, ActionListener.class)) {
            view.emailField.addActionListener(this);
            view.emailField.setActionCommand("Email Field Filled");
        }

        if (!Controller.isListenerOf(this, view.emailField, CaretListener.class)) {
            view.emailField.addCaretListener(this);
        }

        if (!Controller.isListenerOf(this, view.passwordField, CaretListener.class)) {
            view.passwordField.addCaretListener(this);
        }

        if (!Controller.isListenerOf(this, view, ComponentListener.class)) {
            view.addComponentListener(this);
        }
    }

    private void redirectView(DatabaseManager.AccountType accountType) {
        switch (accountType) {
            case ADMIN:
                showCardMethod.accept("Admin Dashboard");
                break;
            case PROFESSOR:
                showCardMethod.accept("Professor Dashboard");
                System.out.println("Professor Dashboard");
                break;
            case STUDENT:
                showCardMethod.accept("Student Dashboard");
                System.out.println("Student Dashboard");
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
            // Trim any whitespace
            view.emailField.setText(view.emailField.getText().trim());
            view.passwordField.setText(new String(view.passwordField.getPassword()).trim());
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
                    list = DatabaseManager.isValidEmail(enteredEmail);
                    if (list != null) {
                        hashedPassword = (String) list.get(0);
                        accountType = DatabaseManager.AccountType.valueOf(String.valueOf(list.get(1)));
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

                view.passwordField.requestFocus();

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
                if (DatabaseManager.isValidPassword(enteredPassword, hashedPassword)) {
                    redirectView(accountType);
                    return;
                }

                // Display error message
                view.passwordErrorLabel.setVisible(true);
                view.revalidate();
                view.repaint();
            }
            else {
                try {
                    DatabaseManager.insertPassword(enteredEmail, enteredPassword);
                    redirectView(accountType);
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

    @Override
    public void componentResized(ComponentEvent e) {
        return;
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        return;
    }

    @Override
    public void componentShown(ComponentEvent e) {
        return;
    }

    @Override
    public void componentHidden(ComponentEvent event) {
        if (event.getSource() == view) {
            resetViewFields();
            passwordFetched = false;
        }
    }

    private void resetViewFields() {
        view.passwordPanel.setVisible(false);
        view.inputDescriptionLabel.setVisible(false);
        view.emailErrorLabel.setVisible(false);
        view.passwordErrorLabel.setVisible(false);
        view.emailField.setText(null);
        view.passwordField.setText(null);
        view.emailField.setEditable(true);

        registerAsListener();

        view.revalidate();
        view.repaint();
    }

}
