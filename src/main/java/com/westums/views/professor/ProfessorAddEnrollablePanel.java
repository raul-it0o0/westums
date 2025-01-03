package com.westums.views.professor;

import com.westums.DatabaseConnection;
import com.westums.models.Laboratory;
import com.westums.models.Lecture;
import com.westums.models.Professor;
import com.westums.models.Seminar;
import com.westums.uimodels.CustomButton;
import com.westums.utils.ModelUpdateListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ProfessorAddEnrollablePanel extends JPanel implements ActionListener {

    Professor loggedProfessor;
    JLabel lblCourseName, lblCourseType, lblSpots, lblCredits, lblSemester, lblYear;
    JTextField txtCourseName, txtSpots, txtCredits, txtSemester, txtYear;
    CustomButton btnOK;
    ActionListener backToDashboard;
    JComboBox<String> cmbxCourseType;
    ModelUpdateListener modelUpdateListener;

    public ProfessorAddEnrollablePanel(Professor loggedProfessor, ActionListener backToDashboard, ModelUpdateListener modelUpdateListener) {
        super.setOpaque(false);
        this.loggedProfessor = loggedProfessor;
        this.backToDashboard = backToDashboard;
        this.modelUpdateListener = modelUpdateListener;

        lblCourseType = new JLabel("Course Type");
        lblCourseType.setLabelFor(cmbxCourseType);
        cmbxCourseType = new JComboBox<>(new String[]{"Lecture", "Seminar", "Laboratory"});
        super.add(lblCourseType);
        super.add(cmbxCourseType);

        lblCourseName = new JLabel("Course Name: ");
        txtCourseName = new JTextField(20);
        super.add(lblCourseName);
        super.add(txtCourseName);

        lblSpots = new JLabel("Spots: ");
        txtSpots = new JTextField(3);
        super.add(lblSpots);
        super.add(txtSpots);

        lblCredits = new JLabel("Credits: ");
        txtCredits = new JTextField(2);
        super.add(lblCredits);
        super.add(txtCredits);

        lblSemester = new JLabel("Semester (1 or 2): ");
        txtSemester = new JTextField(1);
        super.add(lblSemester);
        super.add(txtSemester);

        lblYear = new JLabel("Year: ");
        txtYear = new JTextField(1);
        super.add(lblYear);
        super.add(txtYear);

        btnOK = new CustomButton("OK");
        btnOK.addActionListener(this);
        super.add(btnOK);

        CustomButton btnBack = new CustomButton("<-");
        btnBack.addActionListener(backToDashboard);
        super.add(btnBack);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOK) {
            // Make sure fields are not empty
            if (txtCourseName.getText().isEmpty() || txtSpots.getText().isEmpty() || txtCredits.getText().isEmpty() || txtSemester.getText().isEmpty() || txtYear.getText().isEmpty() || cmbxCourseType.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Fields must not be empty!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Integer spots, credits, semester, year;
            String courseType = cmbxCourseType.getSelectedItem().toString();
            String courseName = txtCourseName.getText();

            try {
                spots = Integer.parseInt(txtSpots.getText());
                credits = Integer.parseInt(txtCredits.getText());
                semester = Integer.parseInt(txtSemester.getText());
                year = Integer.parseInt(txtYear.getText());
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Number fields are not in number format!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Make sure spots are a positive number
            if (spots <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Spots must be greater than zero!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Make sure credits are a positive number
            if (credits <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Credits must be greater than zero!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Make sure semester field is within range
            if (!semester.equals(1) && !semester.equals(2)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Semester must be 1 or 2!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Make sure year field is within range
            if (!year.equals(1) && !year.equals(2) && !year.equals(3) && !year.equals(4)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Year must be 1, 2, 3 or 4!",
                        "Error Message",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                DatabaseConnection db = new DatabaseConnection();

                // 1. Find ProfessorID
                int professorID;
                String query = "SELECT professorID " +
                        "FROM professors " +
                        "WHERE surname = ? AND name = ? AND DOB = ?";
                PreparedStatement stmt = db.connection.prepareStatement(query);
                stmt.setString(1, loggedProfessor.surname);
                stmt.setString(2, loggedProfessor.name);
                stmt.setDate(3, loggedProfessor.dob);
                ResultSet rs = stmt.executeQuery();



                if (rs.next()) {
                    professorID = rs.getInt("professorID");
                }
                else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Error searching database. Try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 2. Insert course into courses table
                query = "INSERT INTO courses(courseName, courseType, availableSpots, professorID, " +
                        "credits, " +
                        "semester, year) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                stmt = db.connection.prepareStatement(query);
                stmt.setString(1, txtCourseName.getText());
                stmt.setString(2, courseType);
                stmt.setString(3, txtSpots.getText());
                stmt.setInt(4, professorID);
                stmt.setInt(5, credits);
                // TODO: semester and year are enum fields,
                //  (i.e. stored as strings)
                //  therefore they need to be
                //  made into strings
                stmt.setString(6, semester.toString());
                stmt.setString(7, year.toString());
                stmt.executeUpdate();


                // Add course into professor's taught courses collection
                if (courseType.equals("Lecture"))
                    loggedProfessor.addTaughtCourse(new Lecture(courseName, spots, credits, semester,
                                year, loggedProfessor));
                else if (courseType.equals("Seminar"))
                    loggedProfessor.addTaughtCourse(new Seminar(courseName, spots, credits, semester,
                            year, loggedProfessor));
                else
                    loggedProfessor.addTaughtCourse(new Laboratory(courseName, spots, credits, semester,
                            year, loggedProfessor));

                // Return back to dashboard
                backToDashboard.actionPerformed(null);

            }
            catch (SQLIntegrityConstraintViolationException ex2) {
                // This exception is thrown when
                // the unique constraint is violated
                // In courses table there is a
                // composite unique constraint for the rows
                // courseName and courseType
                JOptionPane.showMessageDialog(this,
                        "There already exists such a " + courseType +
                                " with the same name in the database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error searching database. Try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }

}

/*
ftfCourseName = new JFormattedTextField();
        ftfCourseName.setColumns(20);
        ftfCourseName.setInputVerifier(new InputStringNotEmptyVerifier());
        super.add(lblCourseName);
        super.add(ftfCourseName);

        lblSpots = new JLabel("Available Spots: ");
        ftfSpots = new JFormattedTextField(NumberFormat.getInstance());
        lblSpots.setLabelFor(ftfSpots);
        // TODO: ^ add this to everything else that is a label to something
        ftfSpots.setColumns(4);
        super.add(lblSpots);
        super.add(ftfSpots);

        lblCredits = new JLabel("Credits: ");
        spnCredits = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        super.add(lblCredits);
        super.add(spnCredits);

        btnOK = new CustomButton("OK");
        super.add(btnOK);
        btnOK.addActionListener(e -> {
            // Make sure fields are not empty
        });
 */
