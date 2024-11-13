package com.westums.views.professor;

import com.westums.DatabaseConnection;
import com.westums.models.Enrollable;
import com.westums.models.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class ProfessorAddStudentDialog extends JDialog {

    public ProfessorAddStudentDialog(JFrame parentFrame, DefaultTableModel students, Enrollable enrollable,
                                     Date dateOfAttendance) {
        super(parentFrame, "Add New Student", true);
        this.setSize(400,300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);

        JPanel searchPanel = new JPanel();
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        JTextField tfSearch = new JTextField("Student ID",7);
        searchPanel.add(tfSearch);
        JButton btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> dispose());
        searchPanel.add(btnExit);

        JPanel foundPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblFound = new JLabel();
        JButton btnAdd = new JButton("Add");
        btnAdd.setVisible(false);
        // when pressed change to Added and make unclickable until search is clicked
        // search cannot be clicked until the textfield content is changed
        foundPanel.add(lblFound);
        foundPanel.add(btnAdd);
        mainPanel.add(foundPanel, BorderLayout.CENTER);

        // Cannot make studentID a simple string because
        // it is used in a lambda expression thus it has to be final
        AtomicReference<String>
                studentName = new AtomicReference<>(""),
                studentSurname = new AtomicReference<>(""),
                studentID = new AtomicReference<>("");
        btnSearch.addActionListener(e -> {
            // Make sure text field is not empty
            if (tfSearch.getText().isEmpty()) {
                return;
            }

            try {
                // Get student name, surname
                studentID.set(tfSearch.getText());

                DatabaseConnection db = new DatabaseConnection();
                // Get student fields
                String query = "SELECT name, surname " +
                        "FROM students " +
                        "WHERE studentID = ?";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, tfSearch.getText());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    studentName.set(rs.getString("name"));
                    studentSurname.set(rs.getString("surname"));
                }
                else {
                    lblFound.setText("No student found");
                    return;
                }

                lblFound.setText(String.format("%s %s", studentName.get(), studentSurname.get()));
                // Change look of Add button and enable
                btnAdd.setText("Add");
                btnAdd.setBackground(Color.LIGHT_GRAY);
                btnAdd.setForeground(Color.BLACK);
                btnAdd.setVisible(true);
                btnAdd.setEnabled(true);

            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error connecting to database",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        });

        btnAdd.addActionListener(e -> {
            // Check if student is not already in the table
            for (int i = 0; i < students.getRowCount(); i++) {
                if (students.getValueAt(i, 0).equals(studentID.get())) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Student is already in the attendances list!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
            }

            // Add to attendances table
            try {
                enrollable.addAttendance(studentID.get(), dateOfAttendance);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error connecting to database",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            // Add to table model
            students.addRow(new Object[]
                    {studentID.get(), studentName.get(), studentSurname.get(), Boolean.FALSE}
            );
            students.fireTableDataChanged();

            btnAdd.setText("Added");
            btnAdd.setBackground(Color.GREEN);
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setEnabled(false);
        });

    }
}
