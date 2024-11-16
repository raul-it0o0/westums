package com.westums.views.student;

import com.westums.DatabaseConnection;
import com.westums.uimodels.CustomButton;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class StudentPasswordCreationPanel extends JPanel {

    public StudentPasswordCreationPanel(String studentID, ActionListener back) {
        CustomButton btnBack = new CustomButton("<-");
        btnBack.addActionListener(back);
        add(btnBack);

        JLabel lbl1 = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);
        add(lbl1);
        add(passwordField);
        lbl1.setLabelFor(passwordField);

        JLabel lbl2 = new JLabel("Confirm Password: ");
        JPasswordField confirmPasswordField = new JPasswordField(20);
        add(lbl2);
        add(confirmPasswordField);
        lbl2.setLabelFor(confirmPasswordField);

        CustomButton confirmButton = new CustomButton("Confirm");
        add(confirmButton);
        confirmButton.addActionListener(e -> {
            String password = String.valueOf(passwordField.getPassword()).strip();
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword()).strip();
            // Make sure password fields are not empty
            if (password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Neither fields must not be empty!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Make sure fields are identical
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Passwords do not match!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                DatabaseConnection db = new DatabaseConnection();
                String query = "UPDATE students " +
                            "SET password = ? " +
                            "WHERE studentID = ?";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, password);
                stmt.setString(2, studentID);
                stmt.executeUpdate();
                back.actionPerformed(null);
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error updating database. Try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        });


    }
}
