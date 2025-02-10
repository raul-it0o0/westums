package com.westums.views.admindashboard;

import com.westums.views.customui.JAddButton;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXFormattedTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AddCourseCard extends JPanel {

    public AddCourseCard() {
        super();
        initComponents();
    }

    private void initComponents() {
        courseNamePanel = new JPanel();
        courseNameLabel = new JLabel();
        courseNameField = new JXFormattedTextField();
        courseNameErrorLabel = new JLabel();
        courseProfessorEmailPanel = new JPanel();
        courseProfessorEmailLabel = new JLabel();
        courseProfessorEmailField = new JXFormattedTextField();
        courseProfessorEmailInvalidLabel = new JLabel();
        courseProfessorEmailNotFoundLabel = new JLabel();
        courseProfessorEmailFoundLabel = new JLabel();
        courseTypePanel = new JPanel();
        courseTypeLabel = new JLabel();
        courseTypeComboBox = new JComboBox<String>();
        courseTypeErrorLabel = new JLabel();
        addCourseButtonPanel = new JPanel();
        addCourseButton = new JAddButton(JAddButton.ADD_TYPE);

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
                            "[fill]"));

            //======== courseNamePanel ========
            {
                courseNamePanel.setOpaque(false);
                courseNamePanel.setLayout(new MigLayout(
                        "insets 0,hidemode 3,gap 0 0",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                                "[0,grow,fill]" +
                                "[grow,fill]" +
                                "[0]"));

                //---- courseNameLabel ----
                courseNameLabel.setText("Course Name");
                courseNameLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                courseNameLabel.setForeground(new Color(0xf5f5f5));
                courseNameLabel.setBorder(null);
                courseNameLabel.setMinimumSize(new Dimension(70, 15));
                courseNameLabel.setPreferredSize(new Dimension(70, 15));
                courseNamePanel.add(courseNameLabel, "cell 0 0,growx,gapy null 3");

                //---- courseNameField ----
                courseNameField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                courseNameField.setForeground(new Color(0x1e1e1e));
                courseNameField.setBackground(new Color(0xf5f5f5));
                courseNameField.setSelectedTextColor(Color.white);
                courseNameField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                courseNameField.setMinimumSize(new Dimension(320, 40));
                courseNameField.setMaximumSize(new Dimension(320, 40));
                courseNameField.setPreferredSize(new Dimension(320, 40));
                courseNamePanel.add(courseNameField, "cell 0 2,grow");

                //---- courseNameErrorLabel ----
                courseNameErrorLabel.setText("Invalid name");
                courseNameErrorLabel.setFont(new Font("Inter 18pt",
                        courseNameErrorLabel.getFont().getStyle(), courseNameErrorLabel.getFont().getSize() + 1));
                courseNameErrorLabel.setForeground(new Color(0xec221f));
                courseNameErrorLabel.setBorder(null);
                courseNameErrorLabel.setMaximumSize(new Dimension(70, 15));
                courseNameErrorLabel.setMinimumSize(new Dimension(70, 15));
                courseNameErrorLabel.setPreferredSize(new Dimension(70, 15));
                courseNameErrorLabel.setVisible(false);
                courseNamePanel.add(courseNameErrorLabel, "cell 0 3,grow,width 272:272:272");
            }
            this.add(courseNamePanel, "cell 0 0,gapy 0");

            //======== courseProfessorEmailPanel ========
            {
                courseProfessorEmailPanel.setOpaque(false);
                courseProfessorEmailPanel.setLayout(new MigLayout(
                        "insets 0,hidemode 3,gap 0 0",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                                "[0,grow,fill]" +
                                "[grow,fill]" +
                                "[0]" +
                                "[]" +
                                "[]"));

                //---- courseProfessorEmailLabel ----
                courseProfessorEmailLabel.setText("Professor Email");
                courseProfessorEmailLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                courseProfessorEmailLabel.setForeground(new Color(0xf5f5f5));
                courseProfessorEmailLabel.setBorder(null);
                courseProfessorEmailLabel.setMinimumSize(new Dimension(70, 15));
                courseProfessorEmailLabel.setPreferredSize(new Dimension(70, 15));
                courseProfessorEmailPanel.add(courseProfessorEmailLabel, "cell 0 0,gapy null 3");

                //---- courseProfessorEmailField ----
                courseProfessorEmailField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                courseProfessorEmailField.setForeground(new Color(0x1e1e1e));
                courseProfessorEmailField.setBackground(new Color(0xf5f5f5));
                courseProfessorEmailField.setSelectedTextColor(Color.white);
                courseProfessorEmailField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                courseProfessorEmailField.setMinimumSize(new Dimension(320, 40));
                courseProfessorEmailField.setMaximumSize(new Dimension(320, 40));
                courseProfessorEmailField.setPreferredSize(new Dimension(320, 40));
                courseProfessorEmailPanel.add(courseProfessorEmailField, "cell 0 2,grow");

                //---- courseProfessorEmailErrorLabel ----
                courseProfessorEmailInvalidLabel.setText("Invalid email");
                courseProfessorEmailInvalidLabel.setFont(new Font("Inter 18pt",
                        courseProfessorEmailInvalidLabel.getFont().getStyle(), courseProfessorEmailInvalidLabel.getFont().getSize() + 1));
                courseProfessorEmailInvalidLabel.setForeground(new Color(0xec221f));
                courseProfessorEmailInvalidLabel.setBorder(null);
                courseProfessorEmailInvalidLabel.setMaximumSize(new Dimension(70, 15));
                courseProfessorEmailInvalidLabel.setMinimumSize(new Dimension(70, 15));
                courseProfessorEmailInvalidLabel.setPreferredSize(new Dimension(70, 15));
                courseProfessorEmailInvalidLabel.setVisible(false);
                courseProfessorEmailPanel.add(courseProfessorEmailInvalidLabel, "cell 0 3,grow,width 272:272:272");

                //---- courseProfessorEmailNotFoundLabel ----
                courseProfessorEmailNotFoundLabel.setText("Professor not found");
                courseProfessorEmailNotFoundLabel.setFont(new Font("Inter 18pt",
                        courseProfessorEmailNotFoundLabel.getFont().getStyle(), courseProfessorEmailNotFoundLabel.getFont().getSize() + 1));
                courseProfessorEmailNotFoundLabel.setForeground(new Color(0xec221f));
                courseProfessorEmailNotFoundLabel.setBorder(null);
                courseProfessorEmailNotFoundLabel.setMaximumSize(new Dimension(70, 15));
                courseProfessorEmailNotFoundLabel.setMinimumSize(new Dimension(70, 15));
                courseProfessorEmailNotFoundLabel.setPreferredSize(new Dimension(70, 15));
                courseProfessorEmailNotFoundLabel.setVisible(false);
                courseProfessorEmailPanel.add(courseProfessorEmailNotFoundLabel, "cell 0 4,grow,width 272:272:272");

                //---- courseProfessorEmailFoundLabel ----
                courseProfessorEmailFoundLabel.setText("Found");
                courseProfessorEmailFoundLabel.setFont(new Font("Inter 18pt",
                        courseProfessorEmailFoundLabel.getFont().getStyle(), courseProfessorEmailFoundLabel.getFont().getSize() + 1));
                courseProfessorEmailFoundLabel.setForeground(new Color(0x14ae5c));
                courseProfessorEmailFoundLabel.setBorder(null);
                courseProfessorEmailFoundLabel.setMaximumSize(new Dimension(70, 15));
                courseProfessorEmailFoundLabel.setMinimumSize(new Dimension(70, 15));
                courseProfessorEmailFoundLabel.setPreferredSize(new Dimension(70, 15));
                courseProfessorEmailFoundLabel.setVisible(false);
                courseProfessorEmailPanel.add(courseProfessorEmailFoundLabel, "cell 0 5,grow,width 272:272:272");
            }
            this.add(courseProfessorEmailPanel, "cell 0 1");

            //======== courseTypePanel ========
            {
                courseTypePanel.setOpaque(false);
                courseTypePanel.setLayout(new MigLayout(
                        "insets 0,hidemode 3,gap 0 0",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                                "[0,grow,fill]" +
                                "[grow,fill]" +
                                "[0]"));

                //---- courseTypeLabel ----
                courseTypeLabel.setText("Course Type");
                courseTypeLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                courseTypeLabel.setForeground(new Color(0xf5f5f5));
                courseTypeLabel.setBorder(null);
                courseTypeLabel.setMinimumSize(new Dimension(70, 15));
                courseTypeLabel.setPreferredSize(new Dimension(70, 15));
                courseTypePanel.add(courseTypeLabel, "cell 0 0,growx,gapy null 3");

                //---- courseTypeComboBox ----
                courseTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                        "Lecture",
                        "Seminar",
                        "Laboratory"
                }));
                courseTypeComboBox.setBorder(new LineBorder(new Color(0x757575)));
                courseTypeComboBox.setForeground(new Color(0x1e1e1e));
                courseTypeComboBox.setBackground(new Color(0xf5f5f5));
                courseTypeComboBox.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                courseTypeComboBox.setMinimumSize(new Dimension(320, 40));
                courseTypeComboBox.setMaximumSize(new Dimension(320, 40));
                courseTypeComboBox.setPreferredSize(new Dimension(320, 40));
                courseTypeComboBox.setSelectedItem(null);
                courseTypePanel.add(courseTypeComboBox, "pad 0,cell 0 2,grow");

                //---- courseTypeErrorLabel ----
                courseTypeErrorLabel.setText("Please choose a course type!");
                courseTypeErrorLabel.setFont(new Font("Inter 18pt",
                        courseTypeErrorLabel.getFont().getStyle(), courseTypeErrorLabel.getFont().getSize() + 1));
                courseTypeErrorLabel.setForeground(new Color(0xec221f));
                courseTypeErrorLabel.setBorder(null);
                courseTypeErrorLabel.setMaximumSize(new Dimension(70, 15));
                courseTypeErrorLabel.setMinimumSize(new Dimension(70, 15));
                courseTypeErrorLabel.setPreferredSize(new Dimension(70, 15));
                courseTypeErrorLabel.setVisible(false);
                courseTypePanel.add(courseTypeErrorLabel, "cell 0 3,grow,width 272:272:272");
            }
            this.add(courseTypePanel, "cell 0 2");

            //======== addCourseButtonPanel ========
            {
                addCourseButtonPanel.setMinimumSize(new Dimension(320, 54));
                addCourseButtonPanel.setLayout(new BorderLayout());

                //---- addCourseButton ----
                addCourseButton.setText("Add");
                addCourseButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                addCourseButton.setBorder(new LineBorder(new Color(0x757575)));
                addCourseButton.setForeground(new Color(0xf5f5f5));
                addCourseButton.setBackground(new Color(0x2c2c2c));
                addCourseButton.setEnabled(false);
                addCourseButtonPanel.add(addCourseButton, BorderLayout.CENTER);
            }
            this.add(addCourseButtonPanel, "cell 0 4,gapy null 20");
        }
    }

    private JPanel courseNamePanel;
    private JLabel courseNameLabel;
    public JXFormattedTextField courseNameField;
    public JLabel courseNameErrorLabel;
    private JPanel courseProfessorEmailPanel;
    private JLabel courseProfessorEmailLabel;
    public JXFormattedTextField courseProfessorEmailField;
    public JLabel courseProfessorEmailInvalidLabel;
    public JLabel courseProfessorEmailNotFoundLabel;
    public JLabel courseProfessorEmailFoundLabel;
    private JPanel courseTypePanel;
    private JLabel courseTypeLabel;
    public JComboBox<String> courseTypeComboBox;
    public JLabel courseTypeErrorLabel;
    private JPanel addCourseButtonPanel;
    public JAddButton addCourseButton;

}
