package com.westums.views;
import com.westums.DatabaseConnection;
import com.westums.views.professor.ProfessorDashboardFrame;
import com.westums.models.*;
import com.westums.uimodels.CustomButton;
import com.westums.uimodels.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorLoginFrame extends LoginFrame implements ActionListener {
    JTextField nameField, surnameField;
    JPasswordField passwordField;
    CustomButton loginButton, backButton;

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

        backButton = new CustomButton("<-");
        backButton.addActionListener(this);
        panel.add(backButton);
    }

    Professor searchForMatch(String name, String surname) {
        int professorID;
        Professor professor;

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
                professor = new Professor(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5));
                professorID = rs.getInt(1);
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

        // Load the professor's taught courses into the Professor object
        try {
            DatabaseConnection db = new DatabaseConnection();
            // function that returns an enrollable implementation based on the enrollableType variable
            String query = "SELECT courseName, courseType, availableSpots, credits, semester, year " +
                    "FROM courses " +
                    "WHERE professorID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setInt(1, professorID);
            ResultSet rs = stmt.executeQuery();

            String enrollableName, enrollableType;
            int spots, credits, semester, year;
            while (rs.next()) {
                enrollableName = rs.getString(1);
                enrollableType = rs.getString(2);
                spots = rs.getInt(3);
                credits = rs.getInt(4);
                // TODO: semester and year are enum fields,
                //  (i.e. stored as strings)
                //  therefore they need to be
                //  made into strings, then into int
                semester = Integer.parseInt(rs.getString(5));
                year = Integer.parseInt(rs.getString(6));
                professor.addTaughtCourse(createEnrollableInstance(
                        enrollableName,
                        enrollableType,
                        spots,
                        credits,
                        semester,
                        year,
                        professor
                ));
            }

            return professor;
        }
        catch (SQLException e) {
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
            }
            else if (professor.name.equals(name) && professor.surname.equals(surname)) {
                if (professor.password.equals(password)) {
                    dispose();
                    ProfessorDashboardFrame dashboard = new ProfessorDashboardFrame(professor);
                    dashboard.setVisible(true);
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

    Enrollable createEnrollableInstance(
            String enrollableName,
            String courseType,
            int spots,
            int credits,
            int semester,
            int year,
            Professor teachingProfessor
    ) {
        try {
            return switch (courseType) {
                case "Lecture" -> new Lecture(
                        enrollableName,
                        spots,
                        credits,
                        semester,
                        year,
                        teachingProfessor
                );
                case "Seminar" -> new Seminar(
                        enrollableName,
                        spots,
                        credits,
                        semester,
                        year,
                        teachingProfessor
                );
                case "Laboratory" -> new Laboratory(
                        enrollableName,
                        spots,
                        credits,
                        semester,
                        year,
                        teachingProfessor
                );
                default -> null;
            };
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

}
