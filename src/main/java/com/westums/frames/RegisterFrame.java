package com.westums.frames;

import com.westums.DatabaseConnection;
import com.westums.models.Professor;
import com.westums.uimodels.CustomButton;
import com.westums.uimodels.LoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterFrame extends LoginFrame implements ActionListener {

    private JTextField tfName, tfSurname, tfDOB;
    private JPasswordField pfPassword;
    private CustomButton btnRegister, btnBack;

    public RegisterFrame() {
        super("Register");

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JLabel lblName = new JLabel("Name:");
        tfName = new JTextField(30);
        panel.add(lblName);
        panel.add(tfName);

        JLabel lblSurname = new JLabel("Surname:");
        tfSurname = new JTextField(30);
        panel.add(lblSurname);
        panel.add(tfSurname);

        JLabel lblDOB = new JLabel("DOB (dd-MM-yyyy):");
        tfDOB = new JTextField(10);
        panel.add(lblDOB);
        panel.add(tfDOB);

        JLabel lblPassword = new JLabel("Password:");
        pfPassword = new JPasswordField(30);
        pfPassword.addActionListener(this);
        panel.add(lblPassword);
        panel.add(pfPassword);

        btnRegister = new CustomButton("Register");
        btnRegister.addActionListener(this);
        panel.add(btnRegister);

        btnBack = new CustomButton("<-");
        btnBack.addActionListener(this);
        panel.add(btnBack);

        JLabel lblDisclaimer = new JLabel();
        lblDisclaimer.setText("Note for students: Please login with your Student ID sent to you by email.");
        panel.add(lblDisclaimer);

        super.add(panel);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            dispose();
            LoginAsFrame previousFrame = new LoginAsFrame();
            previousFrame.setVisible(true);
            return;
        }

        if (e.getSource() == pfPassword || e.getSource() == btnRegister) {
            // Make sure fields are not empty
            String name = tfName.getText();
            String surname = tfSurname.getText();
            String dobString = tfDOB.getText();
            String password = String.valueOf(pfPassword.getPassword());
            if (name.isEmpty() || surname.isEmpty() || dobString.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Fields must not be empty!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            java.sql.Date dob;
            try {
                dob = getDateFromString(tfDOB.getText());
            }
            catch (ParseException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Date is not in correct format (dd-mm-yyyy)!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Professor professor = createProfessor(name, surname, dob, password);

            if (professor == null) {
                resetFields();
            }
            else {
                System.out.println("Registered successfully!");
            }
        }
    }

    java.sql.Date getDateFromString(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);
        return new java.sql.Date(formatter.parse(date).getTime());
    }

    void resetFields() {
        tfName.setText("");
        tfSurname.setText("");
        tfDOB.setText("");
    }

    // TODO: Clean code: Receive parameters as an ArrayList<Object> instead of writing all these down
    // TODO: Also do this on other object ctors
    Professor createProfessor(String name, String surname, java.sql.Date dob, String password) {

        Professor professor = new Professor(surname, name, dob, password);

        try {
            DatabaseConnection db = new DatabaseConnection();

            String query = "INSERT INTO professors(surname, name, DOB, password) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setString(1, professor.surname);
            stmt.setString(2, professor.name);
            stmt.setDate(3, professor.dob);
            stmt.setString(4, password);
            stmt.execute();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error searching database. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return professor;
    }
}
