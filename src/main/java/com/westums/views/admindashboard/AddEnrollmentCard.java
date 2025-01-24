package com.westums.views.admindashboard;

import com.westums.models.*;
import com.westums.views.customui.JAddButton;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXFormattedTextField;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEnrollmentCard extends JPanel {

    public AddEnrollmentCard() {
        super();
        initComponents();
    }

    private void initComponents() {
        studentEnrollmentEmailPanel = new JPanel();
        studentEnrollmentEmailLabel = new JLabel();
        studentEnrollmentEmailField = new JXFormattedTextField();
        studentEnrollmentEmailInvalidLabel = new JLabel();
        studentEnrollmentEmailNotFoundLabel = new JLabel();
        studentEnrollmentEmailFoundLabel = new JLabel();
        refreshCourseListButtonPanel = new JPanel();
        refreshCourseListButton = new JButton();
        courseScrollPane = new JScrollPane();
        courseListModel = new DefaultListModel<Course>();
        courseList = new JList<Course>(courseListModel);
        addEnrollmentButtonPanel = new JPanel();
        addEnrollmentButton = new JAddButton(JAddButton.SUBMIT_TYPE);

        //======== this ========
        {
            this.setOpaque(false);
            this.setMinimumSize(new Dimension(410, 400));
            this.setLayout(new MigLayout(
                    "fill,insets 12 45 20 45,hidemode 3,align center top,gapy 16",
                    // columns
                    "[fill]",
                    // rows
                    "[]" +
                            "[]" +
                            "[]" +
                            "[]0" +
                            "[]" +
                            "[]"));

            //======== studentEnrollmentEmailPanel ========
            {
                studentEnrollmentEmailPanel.setOpaque(false);
                studentEnrollmentEmailPanel.setLayout(new MigLayout(
                        "insets 0,hidemode 3,gap 0 0",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                                "[0,grow,fill]" +
                                "[grow,fill]" +
                                "[0]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //---- studentEnrollmentEmailLabel ----
                studentEnrollmentEmailLabel.setText("Student Email");
                studentEnrollmentEmailLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                studentEnrollmentEmailLabel.setForeground(new Color(0xf5f5f5));
                studentEnrollmentEmailLabel.setBorder(null);
                studentEnrollmentEmailLabel.setMinimumSize(new Dimension(70, 15));
                studentEnrollmentEmailLabel.setPreferredSize(new Dimension(70, 15));
                studentEnrollmentEmailPanel.add(studentEnrollmentEmailLabel, "cell 0 0,growx,gapy null 3");

                //---- studentEnrollmentEmailField ----
                studentEnrollmentEmailField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                studentEnrollmentEmailField.setForeground(new Color(0x1e1e1e));
                studentEnrollmentEmailField.setBackground(new Color(0xf5f5f5));
                studentEnrollmentEmailField.setSelectedTextColor(Color.white);
                studentEnrollmentEmailField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                studentEnrollmentEmailField.setMinimumSize(new Dimension(320, 40));
                studentEnrollmentEmailField.setMaximumSize(new Dimension(320, 40));
                studentEnrollmentEmailField.setPreferredSize(new Dimension(320, 40));
                studentEnrollmentEmailPanel.add(studentEnrollmentEmailField, "cell 0 2,grow");

                //---- studentEnrollmentEmailInvalidLabel ----
                studentEnrollmentEmailInvalidLabel.setText("Invalid email");
                studentEnrollmentEmailInvalidLabel.setFont(new Font("Inter 18pt",
                        studentEnrollmentEmailInvalidLabel.getFont().getStyle(), studentEnrollmentEmailInvalidLabel.getFont().getSize() + 1));
                studentEnrollmentEmailInvalidLabel.setForeground(new Color(0xec221f));
                studentEnrollmentEmailInvalidLabel.setBorder(null);
                studentEnrollmentEmailInvalidLabel.setMaximumSize(new Dimension(70, 15));
                studentEnrollmentEmailInvalidLabel.setMinimumSize(new Dimension(70, 15));
                studentEnrollmentEmailInvalidLabel.setPreferredSize(new Dimension(70, 15));
                studentEnrollmentEmailInvalidLabel.setVisible(false);
                studentEnrollmentEmailPanel.add(studentEnrollmentEmailInvalidLabel, "cell 0 3,grow,width 272:272:272");

                //---- studentEnrollmentEmailNotFoundLabel ----
                studentEnrollmentEmailNotFoundLabel.setText("Student not found");
                studentEnrollmentEmailNotFoundLabel.setFont(new Font("Inter 18pt",
                        studentEnrollmentEmailNotFoundLabel.getFont().getStyle(), studentEnrollmentEmailNotFoundLabel.getFont().getSize() + 1));
                studentEnrollmentEmailNotFoundLabel.setForeground(new Color(0xec221f));
                studentEnrollmentEmailNotFoundLabel.setBorder(null);
                studentEnrollmentEmailNotFoundLabel.setMaximumSize(new Dimension(70, 15));
                studentEnrollmentEmailNotFoundLabel.setMinimumSize(new Dimension(70, 15));
                studentEnrollmentEmailNotFoundLabel.setPreferredSize(new Dimension(70, 15));
                studentEnrollmentEmailNotFoundLabel.setVisible(false);
                studentEnrollmentEmailPanel.add(studentEnrollmentEmailNotFoundLabel, "cell 0 4,grow,width 272:272:272");

                //---- studentEnrollmentEmailFoundLabel ----
                studentEnrollmentEmailFoundLabel.setText("Found");
                studentEnrollmentEmailFoundLabel.setFont(new Font("Inter 18pt",
                        studentEnrollmentEmailFoundLabel.getFont().getStyle(), studentEnrollmentEmailFoundLabel.getFont().getSize() + 1));
                studentEnrollmentEmailFoundLabel.setForeground(new Color(0x14ae5c));
                studentEnrollmentEmailFoundLabel.setBorder(null);
                studentEnrollmentEmailFoundLabel.setMaximumSize(new Dimension(70, 15));
                studentEnrollmentEmailFoundLabel.setMinimumSize(new Dimension(70, 15));
                studentEnrollmentEmailFoundLabel.setPreferredSize(new Dimension(70, 15));
                studentEnrollmentEmailFoundLabel.setVisible(false);
                studentEnrollmentEmailPanel.add(studentEnrollmentEmailFoundLabel, "cell 0 5,grow,width 272:272:272");
            }
            this.add(studentEnrollmentEmailPanel, "cell 0 0,gapy 0");

            //======== refreshCourseListButtonPanel ========
            {
                refreshCourseListButtonPanel.setMinimumSize(new Dimension(320, 27));
                refreshCourseListButtonPanel.setPreferredSize(new Dimension(320, 27));
                refreshCourseListButtonPanel.setToolTipText("Fetch any newly created courses from the database.");
                refreshCourseListButtonPanel.setLayout(new BorderLayout());

                //---- refreshCourseListButton ----
                refreshCourseListButton.setText("Refresh");
                // Refresh button icon
                FontIcon refreshIcon = FontIcon.of(FontAwesome.REFRESH);
                refreshIcon.setIconSize(15);
                refreshIcon.setIconColor(new Color(0xf5f5f5));
                refreshCourseListButton.setIcon(refreshIcon);
                refreshCourseListButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                refreshCourseListButton.setBorder(new LineBorder(new Color(0x757575)));
                refreshCourseListButton.setForeground(new Color(0xf5f5f5));
                refreshCourseListButton.setBackground(new Color(0x2c2c2c));
                refreshCourseListButtonPanel.add(refreshCourseListButton, BorderLayout.CENTER);
            }
            this.add(refreshCourseListButtonPanel, "pad 0,cell 0 1,gapy 0");

            //======== courseScrollPane ========
            {
                // ======== courseList ========
                {
                    courseList.setBackground(new Color(0xf5f5f5));
                    courseList.setSelectionModel(new SelectableListSelectionModel());
                    courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    courseList.setFixedCellHeight(-1);
                    courseList.setCellRenderer(CourseAddEnrollmentListCellRenderer.getInstance());
                }
                courseScrollPane.setViewportView(courseList);
            }
            this.add(courseScrollPane, "cell 0 2,grow");

            //======== addEnrollmentButtonPanel ========
            {
                addEnrollmentButtonPanel.setMinimumSize(new Dimension(320, 54));
                addEnrollmentButtonPanel.setLayout(new BorderLayout());

                //---- addEnrollmentButton ----
                addEnrollmentButton.setText("Submit");
                addEnrollmentButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                addEnrollmentButton.setBorder(new LineBorder(new Color(0x757575)));
                addEnrollmentButton.setForeground(new Color(0xf5f5f5));
                addEnrollmentButton.setBackground(new Color(0x2c2c2c));
                addEnrollmentButton.setEnabled(false);
                addEnrollmentButtonPanel.add(addEnrollmentButton, BorderLayout.CENTER);
            }
            this.add(addEnrollmentButtonPanel, "cell 0 3,gapy 0");
        }
    }

    private JPanel studentEnrollmentEmailPanel;
    private JLabel studentEnrollmentEmailLabel;
    public JXFormattedTextField studentEnrollmentEmailField;
    public JLabel studentEnrollmentEmailInvalidLabel;
    public JLabel studentEnrollmentEmailNotFoundLabel;
    public JLabel studentEnrollmentEmailFoundLabel;
    private JPanel refreshCourseListButtonPanel;
    public JButton refreshCourseListButton;
    public JScrollPane courseScrollPane;
    public DefaultListModel<Course> courseListModel;
    public JList<Course> courseList;
    private JPanel addEnrollmentButtonPanel;
    public JAddButton addEnrollmentButton;

}
