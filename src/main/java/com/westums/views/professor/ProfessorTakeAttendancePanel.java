package com.westums.views.professor;

import com.westums.DatabaseConnection;
import com.westums.models.Enrollable;
import com.westums.uimodels.CustomButton;
import com.westums.utils.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProfessorTakeAttendancePanel extends JPanel {

    private Enrollable enrollable;
    DefaultTableModel students;
    AtomicBoolean tableModified;
    CustomButton btnSubmit;

    public ProfessorTakeAttendancePanel(Enrollable enrollable, JPanel parentContainer, CardLayout cardLayout) {
        this.enrollable = enrollable;

        // --
        CardLayout cardLayout2 = new CardLayout();
        this.setLayout(cardLayout2);

        JPanel thisPanel = new JPanel();
        this.add(thisPanel, "Attendance Panel");

        CustomButton btnBack = new CustomButton("<-");
        btnBack.addActionListener(e -> cardLayout.show(parentContainer, "View Enrollable") );
        thisPanel.add(btnBack);

        CustomButton btnAddNewStudent = new CustomButton("Add New Enrollment");
        btnAddNewStudent.setVisible(false);
        thisPanel.add(btnAddNewStudent);

        JLabel lblDateOfAttendance = new JLabel("Date of Attendance (dd-MM-yyyy)");
        JTextField tfDateOfAttendance = new JTextField(10);
        lblDateOfAttendance.setLabelFor(tfDateOfAttendance);
        thisPanel.add(lblDateOfAttendance);
        thisPanel.add(tfDateOfAttendance);

        CustomButton btnSetToday = new CustomButton("Today");
        thisPanel.add(btnSetToday);
        btnSetToday.addActionListener(e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            tfDateOfAttendance.setText(LocalDate.now().format(formatter));
        });
        
        // When text in textfield is changed:
        // If the initial state of the JTable has changed (i.e. at least one checkbox has been ticked)
        //  spawn a dialog that asks if you want to apply those changes (i.e. register those attendances)
        // After ensuring any unsaved changes are taken care of,
        //  First make sure it is a valid date, if not, don't display anything
        //  If it is a valid date, display the table with all checkboxes unticked.

        this.students = new DefaultTableModel(new Object[]
                {"Student ID", "Name", "Surname", "Present"}, 0) {
            // Make only the 4th column (checkboxes) editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        this.tableModified = new AtomicBoolean(false);
        students.addTableModelListener(e -> tableModified.set(true));

        JTable enrolledStudents = new JTable(students) {
            // Make JTable render a checkbox for the 4th column
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        thisPanel.add(enrolledStudents);

        tfDateOfAttendance.addActionListener(e -> {
            // When enter is pressed
            Date dateOfAttendance = getInputtedAttendanceDate(tfDateOfAttendance);
            if (dateOfAttendance == null) return;
            if (tableModified.get() == true) {
                switch(unsubmittedDataDialog()) {
                    case JOptionPane.YES_OPTION:
                        // Submit changes to DB
                    case JOptionPane.CANCEL_OPTION:
                        // Do nothing
                        return;
                    case JOptionPane.NO_OPTION:
                        // Continue with the inputted date
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }
            }
            btnAddNewStudent.setVisible(true);
            getEnrollments(enrolledStudents);
            tableModified.set(false); // reset variable because we have a new table
            enrolledStudents.setVisible(true);
            this.btnSubmit.setVisible(true);

        });

        btnAddNewStudent.addActionListener(e -> {
            Date dateOfAttendance = getInputtedAttendanceDate(tfDateOfAttendance);
            if (dateOfAttendance == null) {
                return;
            }
            ProfessorAddStudentDialog dialogAddStudent = new ProfessorAddStudentDialog(
                    (JFrame)SwingUtilities.getWindowAncestor(this),
                    students,
                    enrollable,
                    dateOfAttendance);
            dialogAddStudent.setLocationRelativeTo(null);
            dialogAddStudent.setVisible(true);
        });

        this.btnSubmit = new CustomButton("Submit");
        btnSubmit.addActionListener(e -> {
            if (tableModified.get() == false) {
                JOptionPane.showMessageDialog(
                        this,
                        "Attendance(s) already submitted or nothing to submit",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Changes made, need to submit to DB
            addAttendances(getInputtedAttendanceDate(tfDateOfAttendance));
            JOptionPane.showMessageDialog(
                    this,
                    "Attendance(s) submitted",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        thisPanel.add(btnSubmit);
        btnSubmit.setVisible(false);

        CustomButton btnDone = new CustomButton("Done");
        btnDone.addActionListener(e -> {
            if (tableModified.get() == true) {
                switch(unsubmittedDataDialog()) {
                    case JOptionPane.YES_OPTION:
                        // Submit attendances to DB
                        addAttendances(getInputtedAttendanceDate(tfDateOfAttendance));
                    case JOptionPane.CANCEL_OPTION:
                        // Do nothing
                        return;
                    case JOptionPane.NO_OPTION:
                        // Go back to previous screen
                        cardLayout.show(parentContainer, "View Enrollable");
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        // Do nothing
                        return;
                }
            }
        });
        thisPanel.add(btnDone);

    }

    private void addAttendances(Date dateOfAttendance) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "INSERT INTO attendances " +
                    "VALUES(?, ?, ?)";
            PreparedStatement stmt = db.connection.prepareStatement(query);

            for (int i = 0; i < students.getRowCount(); i++) {
                if (students.getValueAt(i, 3).equals(true)) {
                    // if checkbox is ticked (i.e. present student)
                    stmt.setString(1, (String) students.getValueAt(i, 0));
                    stmt.setInt(2, enrollable.getID());
                    stmt.setDate(3, dateOfAttendance);
                    stmt.executeUpdate();
                }
            }
            tableModified.set(false);
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error interacting with database. Try again. Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }


    public Date getInputtedAttendanceDate(JTextField tf) {
        try {
            return utils.getDateFromString(tf.getText());
        }
        catch (Exception e) {
            return null;
        }
    }

    public int unsubmittedDataDialog() {
        return JOptionPane.showOptionDialog(this,
                "You have unsubmitted attendances! Submit?",
                "Unsubmitted Data",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new Object[]{"Yes", "No", "Cancel"},
                null);

        // gets invoked when done button is clicked OR enter button in datetextfield and any change has
        // happened
        // to
        // jtable
        // i.e. this function gives the option to submit, cancel, or don't submit.
    }

    public void getEnrollments(JTable enrolledStudents) {
        // Load enrolled students in table
        DefaultTableModel tbModel = (DefaultTableModel)enrolledStudents.getModel();
        // Clear model of any existing rows
        tbModel.setRowCount(0);
        ArrayList<String> studentIDs = new ArrayList<>();

        try {
            DatabaseConnection db = new DatabaseConnection();
            String query = "SELECT DISTINCT studentID " +
                    "FROM attendances " +
                    "WHERE courseID = ?";
            PreparedStatement stmt = db.connection.prepareStatement(query);
            stmt.setInt(1, enrollable.getID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                studentIDs.add(rs.getString("studentID"));
            }

            // Fill JTable with students
            for (String studentID : studentIDs) {
                query = "SELECT name, surname " +
                        "FROM students " +
                        "WHERE studentID = ?";
                stmt = db.connection.prepareStatement(query);
                stmt.setString(1, studentID);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    tbModel.addRow(new Object[]{studentID, rs.getString("name"), rs.getString("surname"),
                            Boolean.FALSE});
                }
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error interacting with database. Try again. Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}
