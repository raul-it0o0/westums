package com.westums.frames;

import com.westums.DatabaseConnection;
import com.westums.models.Student;
import com.westums.uimodels.CustomButton;
import com.westums.uimodels.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentLoginFrame extends LoginFrame implements ActionListener {

    JTextField studentIDField;
    JPasswordField passwordField;
    CustomButton loginButton, noPasswordButton, backButton;

    public StudentLoginFrame() {
        super("Student Login");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        add(panel);

        JLabel studentIDLabel = new JLabel("Student ID:");
        panel.add(studentIDLabel);
        studentIDField = new JTextField(7);
        panel.add(studentIDField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField(20);
        passwordField.addActionListener(this);
        panel.add(passwordField);

        loginButton = new CustomButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        noPasswordButton = new CustomButton("I don't have a password");
        noPasswordButton.addActionListener(this);
        panel.add(noPasswordButton);

        backButton = new CustomButton("<-");
        backButton.addActionListener(this);
        panel.add(backButton);
    }

    Student searchForMatch(String studentID, String password) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "SELECT * " +
                    "FROM students " +
                    "WHERE studentID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, studentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)
                );
            }
            else {
                JOptionPane.showMessageDialog(this, "Student ID not found.");
                return null;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error searching database. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            LoginAsFrame previousFrame = new LoginAsFrame();
            previousFrame.setVisible(true);
            return;
        }

        String studentID = studentIDField.getText().strip();
        String password = String.valueOf(passwordField.getPassword()).strip();

        if (e.getSource() == passwordField || e.getSource() == loginButton) {
            // Make sure fields are not empty
            if (password.isEmpty() || studentID.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Fields must not be empty!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Student student = searchForMatch(studentID, password);

            if (student == null) {
                resetFields();
                return;
            }
            else if (student.password.equals(password)) {
                System.out.println("Logged in!");
            }
        }
        else if (e.getSource() == noPasswordButton) {

            // Make sure Student ID field is not empty
            if (studentID.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Student ID field must not be empty!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Student student = searchForMatch(studentID, password);

            if (student == null) {
                resetFields();
                return;
            }

            if (student.password != null) {
                JOptionPane.showMessageDialog(
                        this,
                        "The Student ID has a password.",
                        "Information Message",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            else {
                System.out.println("Password creation frame");
                // StudentPasswordCreationFrame
            }



            // Search the database to check if student has no password

            // Case 1. StudentID not found in DB
            // Case 2. Student ID found, with password
            // Case 3. Student ID found, no password

        }


    }

    void resetFields() {
        studentIDField.setText("");
        passwordField.setText("");
    }
}
