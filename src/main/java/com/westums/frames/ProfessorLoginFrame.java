package com.westums.frames;
import com.westums.DatabaseConnection;
import com.westums.models.Professor;
import com.westums.uimodels.CustomButton;
import com.westums.uimodels.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProfessorLoginFrame extends LoginFrame implements ActionListener {
    JTextField nameField, surnameField;
    JPasswordField passwordField;
    CustomButton loginButton;

    public ProfessorLoginFrame() {
        super("Professor Login");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        add(panel);

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel);
        nameField = new JTextField(20);
        panel.add(nameField);

        JLabel surnameLabel = new JLabel("Surname:");
        panel.add(surnameLabel);
        surnameField = new JTextField(20);
        panel.add(surnameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField(20);
        passwordField.addActionListener(this);
        panel.add(passwordField);

        loginButton = new CustomButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);
    }

    Professor searchForMatch(String name, String surname) {
        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "SELECT * " +
                    "FROM professors " +
                    "WHERE name = ? " +
                        "AND surname = ? ";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, surname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5)
                );
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Invalid credentials.\n" +
                                "Note that name and surname fields " +
                        "are case-sensitive.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
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
        String name = nameField.getText().strip();
        String surname = surnameField.getText().strip();
        String password = String.valueOf(passwordField.getPassword()).strip();

        if (e.getSource() == passwordField || e.getSource() == loginButton) {
            // Make sure fields are not empty
            if (name.isEmpty() || surname.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Fields must not be empty!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Professor professor = searchForMatch(name, surname);

            if (professor == null) {
                resetFields();
                return;
            } else if (professor.name.equals(name) && professor.surname.equals(surname)) {
                if (professor.password.equals(password)) {
                    System.out.println("Logged in!");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid password.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                }
                return;
            }
        }
    }

    void resetFields() {
        nameField.setText("");
        surnameField.setText("");
        passwordField.setText("");
    }


}
