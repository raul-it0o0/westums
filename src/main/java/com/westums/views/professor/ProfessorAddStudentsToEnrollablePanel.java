package com.westums.views.professor;

import com.westums.DatabaseConnection;
import com.westums.models.Enrollable;
import com.westums.models.Student;
import com.westums.uimodels.CustomButton;
import com.westums.utils.ModelUpdateListener;
import com.westums.utils.utils;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ProfessorAddStudentsToEnrollablePanel extends JPanel {

    Student studentFoundDuringSearch;
    JTable tbEnrolledStudents;
    DefaultTableModel tbModel;

    public ProfessorAddStudentsToEnrollablePanel(Enrollable enrollable, ActionListener goBack) {
        CustomButton btnBack = new CustomButton("<-");
        btnBack.addActionListener(goBack);
        this.add(btnBack);

        JLabel lbl = new JLabel();
        lbl.setText("Since no students are enrolled in this course," +
                    " you must add the students to take attendance with.");
        this.add(lbl);

        JLabel lblStudentID = new JLabel("Student ID: ");
        JTextField tfStudentID = new JTextField(7);
        this.add(lblStudentID);
        this.add(tfStudentID);

        CustomButton btnSearch = new CustomButton("Search");
        this.add(btnSearch);

        JLabel lblFound = new JLabel("Found: ");
        this.add(lblFound);
        JLabel lblStudentFound = new JLabel();
        this.add(lblStudentFound);

        CustomButton btnAdd = new CustomButton("Add");
        this.add(btnAdd);
        btnAdd.setVisible(false);

        // Get today's date in SQL format
        final Date attDate = Date.valueOf(LocalDate.now());
        this.tbModel = new DefaultTableModel(new Object[]
                {"Student ID", "Name", "Surname"}, 0);
        tbEnrolledStudents = new JTable();
        tbEnrolledStudents.setModel(tbModel);
        tbEnrolledStudents.setAutoCreateColumnsFromModel(true);
        JScrollPane scrollPane = new JScrollPane(tbEnrolledStudents);
        this.add(scrollPane);
        scrollPane.setVisible(false);

        CustomButton btnDone = new CustomButton("Done");
        this.add(btnDone);
        btnBack.addActionListener(goBack);
        btnDone.setVisible(false);
        btnDone.addActionListener(goBack);

        btnAdd.addActionListener(e -> {
            try {
                int courseID = 0;
                DatabaseConnection db = new DatabaseConnection();
                // Get course ID
                String query = "SELECT courseID " +
                        "FROM courses " +
                        "WHERE courseName = ? AND " +
                        "courseType = ?";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, enrollable.getName());
                stmt.setString(2, enrollable.getClass().getSimpleName());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    courseID = rs.getInt("courseID");
                }

                // Make sure attendance record doesn't exist already
                query = "SELECT * " +
                        "FROM attendances "
                        + "WHERE studentID = ? "
                        + "AND courseID = ? "
                        + "AND attendanceDate = ?";
                stmt = db.connection.prepareStatement(query);
                stmt.setString(1, studentFoundDuringSearch.ID);
                stmt.setInt(2, courseID);
                stmt.setDate(3, attDate);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Cannot add the same attendance twice!",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                query = "INSERT INTO attendances(studentID, courseID, attendanceDate) " +
                        "VALUES(?, ?, ?)";
                stmt = db.connection.prepareStatement(query);
                stmt.setString(1, studentFoundDuringSearch.ID);
                stmt.setInt(2, courseID);
                stmt.setDate(3, attDate);
                stmt.executeUpdate();

                tbModel.addRow(new Object[]
                        {studentFoundDuringSearch.ID, studentFoundDuringSearch.name,
                                studentFoundDuringSearch.surname});
                scrollPane.setVisible(true);
                this.revalidate();
                this.repaint();

                // Change model
                enrollable.setEnrolledStudents(true);

                btnDone.setVisible(true);
                return;
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error interacting with database. Try again. Error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            catch (Exception ex2) {
                JOptionPane.showMessageDialog(
                        this,
                        "An error occurred. Try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSearch.addActionListener(e -> {
            // Make sure student ID field is not empty
            if (tfStudentID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a student ID.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            try {
                DatabaseConnection db = new DatabaseConnection();
                String query = "SELECT * " +
                        "FROM students " +
                        "WHERE studentID = ?";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, tfStudentID.getText());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    studentFoundDuringSearch = new Student(
                            rs.getString("studentID"),
                            rs.getString("surname"),
                            rs.getString("name"),
                            rs.getDate("dob"),
                            rs.getString("specialization"),
                            rs.getInt("year"),
                            rs.getString("password")
                    );
                    lblStudentFound.setText(String.format("%s %s", studentFoundDuringSearch.name,
                            studentFoundDuringSearch.surname));
                    btnAdd.setVisible(true);
                }
                else {
                    lblStudentFound.setText("No Student Found!");
                    btnAdd.setVisible(false);
                }

            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error searching database. Try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        });



    }

//    @Override
//    public void tableChanged(TableModelEvent e) {
//        tbEnrolledStudents.setModel(this.tbModel);
//        tbEnrolledStudents.repaint();
//    }
}
