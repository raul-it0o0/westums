/*
 * Created by JFormDesigner on Wed Dec 04 12:42:21 EET 2024
 */

package com.westums.views;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.*;
import com.jgoodies.forms.factories.*;
import com.toedter.calendar.*;
import com.westums.models.Course;
import com.westums.models.CourseAddEnrollmentListCellRenderer;
import com.westums.models.SelectableListSelectionModel;
import net.miginfocom.swing.*;
import org.jdesktop.swingx.*;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

/**
 * @author Raul
 */
public class AdminDashboard extends JPanel {

    public AdminDashboard() {
        initComponents();
    }

    public void showCard(String cardName) {
        cardLayout.show(formPanel, cardName);
    }
    public enum ButtonState {
        SUCCESS, NEUTRAL
    }

    /*
     * Method to set the state of a button to either "Added" or "Add"
     *
     * @param button The button whose state is to be modified
     * @param addedConfirmationState <b>true</b> if the button is to be set in a confirmation state, <b>false</b> otherwise
     * @param enabled <b>true</b> if the button is to be disabled, <b>false</b> otherwise (unaffected)
     */
    public void setButtonState(JButton button, ButtonState buttonState) {
        switch (buttonState) {
            case SUCCESS:
                button.setBackground(new Color(0x14ae5c));
                button.getParent().setBackground(new Color(0x14ae5c));
                button.setForeground(new Color(0xf5f5f5));
                button.setBorder(null);
                if (button.equals(addEnrollmentButton))
                    button.setText("Submitted");
                else
                    button.setText("Added");
                button.setEnabled(false);
                break;
            case NEUTRAL:
                button.setBackground(new Color(0x2c2c2c));
                button.getParent().setBackground(new Color(0x2c2c2c));
                button.setForeground(new Color(0xf5f5f5));
                button.setBorder(new LineBorder(new Color(0x757575)));
                if (button.equals(addEnrollmentButton))
                    button.setText("Submit");
                else
                    button.setText("Add");
                button.setEnabled(false);
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        menuPanel = new JPanel();
        menuHeader = new JLabel();
        separator1 = new JPopupMenu.Separator();
        optionPane = new JScrollPane();
        optionTree = new JTree();
        separator2 = new JPopupMenu.Separator();
        logoutPanel = new JPanel();
        FontIcon fontIcon = FontIcon.of(FontAwesome.ARROW_CIRCLE_LEFT);
        fontIcon.setIconSize(13);
        fontIcon.setIconColor(new Color(0x900B09));
        logoutIconLabel = new JLabel(fontIcon);
        logoutClickableLabel = new JLabel();
        formPanel = new JPanel();
        defaultCard = new JPanel();
        chooseOptionMessage = new JTextPane();
        addStudentScrollPane = new JScrollPane();
        addStudentCard = new JPanel();
        studentNamePanel = new JPanel();
        studentNameLabel = new JLabel();
        studentNameField = new JXFormattedTextField();
        studentNameErrorLabel = new JLabel();
        studentSurnamePanel = new JPanel();
        studentSurnameLabel = new JLabel();
        studentSurnameField = new JXFormattedTextField();
        studentSurnameErrorLabel = new JLabel();
        studentDatePanel = new JPanel();
        studentDateLabel = new JLabel();
        studentDateChooser = new JDateChooser();
        studentDateErrorLabel = new JLabel();
        generatedStudentEmailPanel = new JPanel();
        generatedStudentEmailLabel = new JLabel();
        generatedStudentEmailField = new JXFormattedTextField();
        addStudentButtonPanel = new JPanel();
        addStudentButton = new JButton();
        addProfessorScrollPane = new JScrollPane();
        addProfessorCard = new JPanel();
        professorNamePanel = new JPanel();
        professorNameLabel = new JLabel();
        professorNameField = new JXFormattedTextField();
        professorNameErrorLabel = new JLabel();
        professorSurnamePanel = new JPanel();
        professorSurnameLabel = new JLabel();
        professorSurnameField = new JXFormattedTextField();
        professorSurnameErrorLabel = new JLabel();
        professorDatePanel = new JPanel();
        professorDateLabel = new JLabel();
        professorDateChooser = new JDateChooser();
        professorDateErrorLabel = new JLabel();
        generatedProfessorEmailPanel = new JPanel();
        generatedProfessorEmailLabel = new JLabel();
        generatedProfessorEmailField = new JXFormattedTextField();
        addProfessorButtonPanel = new JPanel();
        addProfessorButton = new JButton();
        addCourseCard = new JPanel();
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
        addCourseButton = new JButton();
        addEnrollmentCard = new JPanel();
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
        addEnrollmentButton = new JButton();
        cardLayout = new CardLayout();

        //======== this ========
        setBackground(new Color(0x1e1e34));
        setMinimumSize(new Dimension(320, 206));
        setPreferredSize(new Dimension(320, 245));
        setLayout(new MigLayout(
                "fillx,insets 0,hidemode 3,align center center,gap 0 0",
                // columns
                "[fill]",
                // rows
                "[fill]" +
                        "[]"));

        //======== menuPanel ========
        {
            menuPanel.setBackground(new Color(0xf5f5f5));
            menuPanel.setLayout(new MigLayout(
                "fill,insets 8 8 4 8,novisualpadding,hidemode 3,gapy 0",
                // columns
                "[0,fill]",
                // rows
                "[shrink 0]" +
                "[22]" +
                "[]" +
                "[]" +
                "[]"));

            //---- menuHeader ----
            menuHeader.setText("Admin");
            menuHeader.setFont(new Font("Inter 18pt", Font.BOLD, 14));
            menuHeader.setForeground(new Color(0x1e1e1e));
            menuPanel.add(menuHeader, "pad 0 10 0 0,cell 0 0,aligny center,gapy null -4,wrap");
            menuPanel.add(separator1, "pad 0 8 0 -8,cell 0 1,aligny center,growy 0");

            //======== optionPane ========
            {
                optionPane.setForeground(new Color(0x1e1e1e));
                optionPane.setBackground(new Color(0x1e1e1e));
                optionPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                optionPane.setBorder(null);
                optionPane.setMaximumSize(new Dimension(32767, 400));
                optionPane.setPreferredSize(new Dimension(115, 250));

                //---- optionTree ----
                DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)optionTree.getCellRenderer();
                renderer.setForeground(new Color(0x1E1E1E));
                renderer.setTextSelectionColor(new Color(0x1e1e1e));
                renderer.setTextNonSelectionColor(new Color(0x1e1e1e));
                renderer.setOpaque(false);
                renderer.setBackgroundSelectionColor(new Color(0xD9D9D9));
                renderer.setBackgroundNonSelectionColor(new Color(0xF5F5F5));
                optionTree.setCellRenderer(renderer);
                optionTree.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                optionTree.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("(root)") {
                        {
                            add(new DefaultMutableTreeNode("Add Student"));
                            add(new DefaultMutableTreeNode("Add Professor"));
                            add(new DefaultMutableTreeNode("Add Course"));
                            add(new DefaultMutableTreeNode("Add Enrollment"));
                            add(new DefaultMutableTreeNode("View Students"));
                            add(new DefaultMutableTreeNode("View Professors"));
                            add(new DefaultMutableTreeNode("View Courses"));
                        }
                    }));
                optionTree.setBackground(new Color(0xf5f5f5));
                optionTree.setBorder(null);
                optionTree.setForeground(new Color(0x1e1e1e));
                optionTree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                optionTree.setRootVisible(false);
                optionTree.setRowHeight(48);
                optionTree.setPreferredSize(new Dimension(115, 100));
                optionPane.setViewportView(optionTree);
            }
            menuPanel.add(optionPane, "pad 0 5 0 0,cell 0 2,grow");
            menuPanel.add(separator2, "pad 0 8 0 -8,cell 0 3,aligny center,growy 0,wrap");

            //======== logoutPanel ========
            {
                logoutPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                //======== logoutIconLabel ========
                {
                    logoutIconLabel.setHorizontalAlignment(JLabel.CENTER);
                    logoutIconLabel.setVerticalAlignment(JLabel.CENTER);
                    logoutIconLabel.setSize(16,16);
                }
                logoutPanel.setOpaque(false);
                logoutPanel.setLayout(new FlowLayout());
                logoutPanel.add(logoutIconLabel);

                //---- logoutClickableLabel ----
                logoutClickableLabel.setText("Logout");
                logoutClickableLabel.setFont(new Font("Inter 18pt", Font.PLAIN, 14));
                logoutClickableLabel.setForeground(new Color(0x900b09));
                logoutClickableLabel.setHorizontalAlignment(SwingConstants.CENTER);
                logoutPanel.add(logoutClickableLabel);
            }
            menuPanel.add(logoutPanel, "cell 0 4,align center bottom,grow 0 0");
        }
        add(menuPanel, "width 190:190:190");
        // add(menuPanel, "width 190:190:190,height 400:400:400");

        //======== formPanel ========
        {
            formPanel.setBackground(new Color(0x1e1e34));
            formPanel.setOpaque(false);
            formPanel.setMinimumSize(new Dimension(300, 300));
            formPanel.setPreferredSize(new Dimension(410, 400));
            formPanel.setLayout(cardLayout);

            //======== defaultCard ========
            {
                defaultCard.setOpaque(false);
                defaultCard.setMinimumSize(new Dimension(410, 400));
                defaultCard.setLayout(new MigLayout(
                        "fill,insets 0,hidemode 3,align center center",
                        // columns
                        "[fill]",
                        // rows
                        "[fill]"));

                //---- chooseOptionMessage ----
                chooseOptionMessage.setText("No option chosen. \nPlease choose one of the options from the " +
                        "menu on the left.");
                chooseOptionMessage.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                chooseOptionMessage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                StyledDocument doc = chooseOptionMessage.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);

                chooseOptionMessage.setEditable(false);
                chooseOptionMessage.setBorder(null);
                chooseOptionMessage.setEnabled(false);
                chooseOptionMessage.setDisabledTextColor(new Color(0xf3f3f3));
                chooseOptionMessage.setOpaque(false);
                chooseOptionMessage.setFont(new Font("Inter 18pt", Font.PLAIN, 14));
                chooseOptionMessage.setFocusable(false);
                defaultCard.add(chooseOptionMessage, "cell 0 0,align center center,grow 0 0,wmax 400");
            }
            formPanel.add(defaultCard, "Default Card");

            //======== addStudentScrollPane ========
            {
                addStudentScrollPane.getViewport().setBackground(new Color(0x1e1e34));
                addStudentScrollPane.setBorder(null);
                addStudentScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                addStudentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //======== addStudentCard ========
                {
                    addStudentCard.setBackground(new Color(0x1e1e34));
                    addStudentCard.setOpaque(false);
                    addStudentCard.setLayout(new MigLayout(
                        "fill,insets 12 45 12 45,novisualpadding,hidemode 3,align center top,gapy 10",
                        // columns
                        "[fill]",
                        // rows
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[fill]"));

                    //======== studentNamePanel ========
                    {
                        studentNamePanel.setOpaque(false);
                        studentNamePanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                        //---- studentNameLabel ----
                        studentNameLabel.setText("Name");
                        studentNameLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        studentNameLabel.setForeground(new Color(0xf5f5f5));
                        studentNameLabel.setBorder(null);
                        studentNameLabel.setMaximumSize(new Dimension(70, 15));
                        studentNameLabel.setMinimumSize(new Dimension(70, 15));
                        studentNameLabel.setPreferredSize(new Dimension(70, 15));
                        studentNamePanel.add(studentNameLabel, "cell 0 0,gapy null 3");

                        //---- studentNameField ----
                        studentNameField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                        studentNameField.setForeground(new Color(0x1e1e1e));
                        studentNameField.setBackground(new Color(0xf5f5f5));
                        studentNameField.setSelectedTextColor(Color.white);
                        studentNameField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                        studentNameField.setMinimumSize(new Dimension(320, 40));
                        studentNameField.setMaximumSize(new Dimension(320, 40));
                        studentNameField.setPreferredSize(new Dimension(320, 40));
                        studentNamePanel.add(studentNameField, "cell 0 2,grow");

                        //---- studentNameErrorLabel ----
                        studentNameErrorLabel.setText("Invalid name");
                        studentNameErrorLabel.setFont(new Font("Inter 18pt", studentNameErrorLabel.getFont().getStyle(), studentNameErrorLabel.getFont().getSize() + 1));
                        studentNameErrorLabel.setForeground(new Color(0xec221f));
                        studentNameErrorLabel.setBorder(null);
                        studentNameErrorLabel.setMaximumSize(new Dimension(70, 15));
                        studentNameErrorLabel.setMinimumSize(new Dimension(70, 15));
                        studentNameErrorLabel.setPreferredSize(new Dimension(70, 15));
                        studentNameErrorLabel.setVisible(false);
                        studentNamePanel.add(studentNameErrorLabel, "cell 0 3,grow,width 272:272:272");
                    }
                    addStudentCard.add(studentNamePanel, "cell 0 0");

                    //======== studentSurnamePanel ========
                    {
                        studentSurnamePanel.setOpaque(false);
                        studentSurnamePanel.setLayout(new MigLayout(
                                "insets 0,hidemode 3,gap 0 0",
                                // columns
                                "[grow,fill]",
                                // rows
                                "[grow,fill]" +
                                        "[0,grow,fill]" +
                                        "[grow,fill]" +
                                        "[0]"));

                        //---- studentSurnameLabel ----
                        studentSurnameLabel.setText("Surname");
                        studentSurnameLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        studentSurnameLabel.setForeground(new Color(0xf5f5f5));
                        studentSurnameLabel.setBorder(null);
                        studentSurnameLabel.setMaximumSize(new Dimension(70, 15));
                        studentSurnameLabel.setMinimumSize(new Dimension(70, 15));
                        studentSurnameLabel.setPreferredSize(new Dimension(70, 15));
                        studentSurnamePanel.add(studentSurnameLabel, "cell 0 0,gapy null 3");

                        //---- studentSurnameField ----
                        studentSurnameField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                        studentSurnameField.setForeground(new Color(0x1e1e1e));
                        studentSurnameField.setBackground(new Color(0xf5f5f5));
                        studentSurnameField.setSelectedTextColor(Color.white);
                        studentSurnameField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                        studentSurnameField.setMinimumSize(new Dimension(320, 40));
                        studentSurnameField.setMaximumSize(new Dimension(320, 40));
                        studentSurnameField.setPreferredSize(new Dimension(320, 40));
                        studentSurnamePanel.add(studentSurnameField, "cell 0 2,grow");

                        //---- studentSurnameErrorLabel ----
                        studentSurnameErrorLabel.setText("Invalid surname");
                        studentSurnameErrorLabel.setFont(new Font("Inter 18pt", studentSurnameErrorLabel.getFont().getStyle(), studentSurnameErrorLabel.getFont().getSize() + 1));
                        studentSurnameErrorLabel.setForeground(new Color(0xec221f));
                        studentSurnameErrorLabel.setBorder(null);
                        studentSurnameErrorLabel.setMaximumSize(new Dimension(70, 15));
                        studentSurnameErrorLabel.setMinimumSize(new Dimension(70, 15));
                        studentSurnameErrorLabel.setPreferredSize(new Dimension(70, 15));
                        studentSurnameErrorLabel.setVisible(false);
                        studentSurnamePanel.add(studentSurnameErrorLabel, "cell 0 3,grow,width 272:272:272");
                    }
                    addStudentCard.add(studentSurnamePanel, "cell 0 1");

                    //======== studentDatePanel ========
                    {
                        studentDatePanel.setOpaque(false);
                        studentDatePanel.setLayout(new MigLayout(
                                "insets 0,hidemode 3,gap 0 0",
                                // columns
                                "[grow,fill]",
                                // rows
                                "[grow,fill]" +
                                        "[0,grow,fill]" +
                                        "[grow,fill]" +
                                        "[0]"));

                        //---- studentDateLabel ----
                        studentDateLabel.setText("Date of Birth");
                        studentDateLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        studentDateLabel.setForeground(new Color(0xf5f5f5));
                        studentDateLabel.setBorder(null);
                        studentDateLabel.setMaximumSize(new Dimension(100, 15));
                        studentDateLabel.setMinimumSize(new Dimension(100, 15));
                        studentDateLabel.setPreferredSize(new Dimension(100, 15));
                        studentDatePanel.add(studentDateLabel, "cell 0 0,gapy null 3");

                        //---- studentDateChooser ----
                        studentDateChooser.setMinimumSize(new Dimension(320, 40));
                        studentDateChooser.setMaximumSize(new Dimension(320, 40));
                        studentDateChooser.setPreferredSize(new Dimension(320, 40));
                        studentDateChooser.setBackground(new Color(0xf5f5f5));
                        for( Component c : studentDateChooser.getComponents())
                            ((JComponent)c).setBackground(new Color(0xf5f5f5));
                        FontIcon fontIconDateChooser = FontIcon.of(FontAwesome.CALENDAR);
                        fontIconDateChooser.setIconSize(15);
                        fontIconDateChooser.setIconColor(new Color(0x101828));
                        studentDateChooser.getCalendarButton().setIcon(fontIconDateChooser);
                        studentDateChooser.getJCalendar().getYearChooser().setUI(new javax.swing.plaf.PanelUI() {
                            @Override
                            public void paint(Graphics g, JComponent c) {
                                super.paint(g, c);
                                c.setForeground(new Color(0xf5f5f5));
                            }
                        });

                        Calendar calendar = Calendar.getInstance();
                        Date today = calendar.getTime();

                        // Set minimum selectable date (today - 100 years)
                        calendar.setTime(today);
                        calendar.add(Calendar.YEAR, -100);
                        studentDateChooser.setMinSelectableDate(calendar.getTime());

                        // Set maximum selectable date (today - 18 years)
                        calendar.setTime(today);
                        calendar.add(Calendar.YEAR, -18);
                        studentDateChooser.setMaxSelectableDate(calendar.getTime());

                        // Set initial date displayed in calendar to 01/01/2000
                        calendar.set(2000, Calendar.JANUARY, 1);
                        studentDateChooser.getDateEditor().setDate(calendar.getTime());

                        studentDatePanel.add(studentDateChooser, "cell 0 2");

                        //---- studentDateErrorLabel ----
                        studentDateErrorLabel.setText("Invalid date");
                        studentDateErrorLabel.setFont(new Font("Inter", studentDateErrorLabel.getFont().getStyle(), studentDateErrorLabel.getFont().getSize() + 1));
                        studentDateErrorLabel.setForeground(new Color(0xec221f));
                        studentDateErrorLabel.setBorder(null);
                        studentDateErrorLabel.setMaximumSize(new Dimension(70, 15));
                        studentDateErrorLabel.setMinimumSize(new Dimension(70, 15));
                        studentDateErrorLabel.setPreferredSize(new Dimension(70, 15));
                        studentDateErrorLabel.setVisible(false);
                        studentDatePanel.add(studentDateErrorLabel, "cell 0 3,grow,width 272:272:272");
                    }
                    addStudentCard.add(studentDatePanel, "cell 0 2");

                    //======== generatedStudentEmailPanel ========
                    {
                        generatedStudentEmailPanel.setOpaque(false);
                        generatedStudentEmailPanel.setLayout(new MigLayout(
                                "insets 0,hidemode 3,gap 0 0",
                                // columns
                                "[grow,fill]",
                                // rows
                                "[grow,fill]" +
                                        "[0,grow,fill]" +
                                        "[grow,fill]" +
                                        "[0]"));

                        //---- generatedStudentEmailLabel ----
                        generatedStudentEmailLabel.setText("Generated Email");
                        generatedStudentEmailLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        generatedStudentEmailLabel.setForeground(new Color(0xf5f5f5));
                        generatedStudentEmailLabel.setBorder(null);
                        generatedStudentEmailLabel.setMaximumSize(new Dimension(600, 15));
                        generatedStudentEmailLabel.setMinimumSize(new Dimension(70, 15));
                        generatedStudentEmailLabel.setPreferredSize(new Dimension(70, 15));
                        generatedStudentEmailPanel.add(generatedStudentEmailLabel, "cell 0 0,growx,gapy null 3");

                        //---- generatedStudentEmailField ----
                        generatedStudentEmailField.setBorder(new LineBorder(new Color(0x757575)));
                        generatedStudentEmailField.setForeground(new Color(0xf3f3f3));
                        generatedStudentEmailField.setBackground(new Color(0x434343));
                        generatedStudentEmailField.setSelectedTextColor(Color.white);
                        generatedStudentEmailField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                        generatedStudentEmailField.setMinimumSize(new Dimension(320, 40));
                        generatedStudentEmailField.setMaximumSize(new Dimension(320, 40));
                        generatedStudentEmailField.setPreferredSize(new Dimension(320, 40));
                        generatedStudentEmailField.setEditable(false);
                        generatedStudentEmailField.setHorizontalAlignment(SwingConstants.CENTER);
                        generatedStudentEmailField.setEnabled(false);
                        generatedStudentEmailPanel.add(generatedStudentEmailField, "pad 0,cell 0 2,grow");
                    }
                    addStudentCard.add(generatedStudentEmailPanel, "cell 0 3");

                    //======== addStudentButtonPanel ========
                    {
                        addStudentButtonPanel.setPreferredSize(new Dimension(320, 35));
                        addStudentButtonPanel.setMaximumSize(new Dimension(320, 35));
                        addStudentButtonPanel.setLayout(new BorderLayout());

                        //---- addStudentButton ----
                        addStudentButton.setText("Add");
                        addStudentButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                        addStudentButton.setBorder(new LineBorder(new Color(0x757575)));
                        addStudentButton.setForeground(new Color(0xf5f5f5));
                        addStudentButton.setBackground(new Color(0x2c2c2c));
                        addStudentButton.setEnabled(false);
                        addStudentButtonPanel.add(addStudentButton, BorderLayout.CENTER);
                    }
                    addStudentCard.add(addStudentButtonPanel, "cell 0 4,gapy null 20");
                }
                addStudentScrollPane.setViewportView(addStudentCard);
                addStudentScrollPane.getViewport().setSize(addStudentCard.getPreferredSize());
            }
            formPanel.add(addStudentScrollPane, "Add Student Card");

            //======== addProfessorScrollPane ========
            {
                addProfessorScrollPane.getViewport().setBackground(new Color(0x1e1e34));
                addProfessorScrollPane.setBorder(null);
                addProfessorScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                addProfessorScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                //======== addProfessorCard ========
                {
                    addProfessorCard.setBackground(new Color(0x1e1e34));
                    addProfessorCard.setOpaque(false);
                    addProfessorCard.setLayout(new MigLayout(
                            "fill,insets 12 45 12 45,novisualpadding,hidemode 3,align center top,gapy 10",
                        // columns
                        "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]" +
                                "[]" +
                                "[fill]"));

                    //======== professorNamePanel ========
                    {
                        professorNamePanel.setOpaque(false);
                        professorNamePanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                    //---- professorNameLabel ----
                    professorNameLabel.setText("Name");
                    professorNameLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                    professorNameLabel.setForeground(new Color(0xf5f5f5));
                    professorNameLabel.setBorder(null);
                    professorNameLabel.setMaximumSize(new Dimension(70, 15));
                    professorNameLabel.setMinimumSize(new Dimension(70, 15));
                    professorNameLabel.setPreferredSize(new Dimension(70, 15));
                    professorNamePanel.add(professorNameLabel, "cell 0 0,gapy null 3");

                    //---- professorNameField ----
                    professorNameField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                    professorNameField.setForeground(new Color(0x1e1e1e));
                    professorNameField.setBackground(new Color(0xf5f5f5));
                    professorNameField.setSelectedTextColor(Color.white);
                    professorNameField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    professorNameField.setMinimumSize(new Dimension(320, 40));
                    professorNameField.setMaximumSize(new Dimension(320, 40));
                    professorNameField.setPreferredSize(new Dimension(320, 40));
                    professorNamePanel.add(professorNameField, "cell 0 2,grow");

                    //---- professorNameErrorLabel ----
                    professorNameErrorLabel.setText("Invalid name");
                    professorNameErrorLabel.setFont(new Font("Inter 18pt",
                            professorNameErrorLabel.getFont().getStyle(), professorNameErrorLabel.getFont().getSize() + 1));
                    professorNameErrorLabel.setForeground(new Color(0xec221f));
                    professorNameErrorLabel.setBorder(null);
                    professorNameErrorLabel.setMaximumSize(new Dimension(70, 15));
                    professorNameErrorLabel.setMinimumSize(new Dimension(70, 15));
                    professorNameErrorLabel.setPreferredSize(new Dimension(70, 15));
                    professorNameErrorLabel.setVisible(false);
                    professorNamePanel.add(professorNameErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                addProfessorCard.add(professorNamePanel, "cell 0 0");

                //======== professorSurnamePanel ========
                {
                    professorSurnamePanel.setOpaque(false);
                    professorSurnamePanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                    //---- professorSurnameLabel ----
                    professorSurnameLabel.setText("Surname");
                    professorSurnameLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                    professorSurnameLabel.setForeground(new Color(0xf5f5f5));
                    professorSurnameLabel.setBorder(null);
                    professorSurnameLabel.setMaximumSize(new Dimension(70, 15));
                    professorSurnameLabel.setMinimumSize(new Dimension(70, 15));
                    professorSurnameLabel.setPreferredSize(new Dimension(70, 15));
                    professorSurnamePanel.add(professorSurnameLabel, "cell 0 0,gapy null 3");

                    //---- professorSurnameField ----
                    professorSurnameField.setBorder(new LineBorder(new Color(0xd9d9d9)));
                    professorSurnameField.setForeground(new Color(0x1e1e1e));
                    professorSurnameField.setBackground(new Color(0xf5f5f5));
                    professorSurnameField.setSelectedTextColor(Color.white);
                    professorSurnameField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    professorSurnameField.setMinimumSize(new Dimension(320, 40));
                    professorSurnameField.setMaximumSize(new Dimension(320, 40));
                    professorSurnameField.setPreferredSize(new Dimension(320, 40));
                    professorSurnamePanel.add(professorSurnameField, "cell 0 2,grow");

                    //---- professorSurnameErrorLabel ----
                    professorSurnameErrorLabel.setText("Invalid surname");
                    professorSurnameErrorLabel.setFont(new Font("Inter 18pt",
                            professorSurnameErrorLabel.getFont().getStyle(), professorSurnameErrorLabel.getFont().getSize() + 1));
                    professorSurnameErrorLabel.setForeground(new Color(0xec221f));
                    professorSurnameErrorLabel.setBorder(null);
                    professorSurnameErrorLabel.setMaximumSize(new Dimension(70, 15));
                    professorSurnameErrorLabel.setMinimumSize(new Dimension(70, 15));
                    professorSurnameErrorLabel.setPreferredSize(new Dimension(70, 15));
                    professorSurnameErrorLabel.setVisible(false);
                    professorSurnamePanel.add(professorSurnameErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                addProfessorCard.add(professorSurnamePanel, "cell 0 1");

                //======== professorDatePanel ========
                {
                    professorDatePanel.setOpaque(false);
                    professorDatePanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                    //---- professorDateLabel ----
                    professorDateLabel.setText("Date of Birth");
                    professorDateLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                    professorDateLabel.setForeground(new Color(0xf5f5f5));
                    professorDateLabel.setBorder(null);
                    professorDateLabel.setMaximumSize(new Dimension(100, 15));
                    professorDateLabel.setMinimumSize(new Dimension(100, 15));
                    professorDateLabel.setPreferredSize(new Dimension(100, 15));
                    professorDatePanel.add(professorDateLabel, "cell 0 0,gapy null 3");

                    //---- professorDateChooser ----
                    professorDateChooser.setMinimumSize(new Dimension(320, 40));
                    professorDateChooser.setMaximumSize(new Dimension(320, 40));
                    professorDateChooser.setPreferredSize(new Dimension(320, 40));
                    professorDateChooser.setBackground(new Color(0xf5f5f5));
                    for( Component c : professorDateChooser.getComponents())
                        ((JComponent)c).setBackground(new Color(0xf5f5f5));
                    FontIcon fontIconDateChooser = FontIcon.of(FontAwesome.CALENDAR);
                    fontIconDateChooser.setIconSize(15);
                    fontIconDateChooser.setIconColor(new Color(0x101828));
                    professorDateChooser.getCalendarButton().setIcon(fontIconDateChooser);

                    professorDateChooser.getJCalendar().getYearChooser().setUI(new javax.swing.plaf.PanelUI() {
                        @Override
                        public void paint(Graphics g, JComponent c) {
                            super.paint(g, c);
                            c.setForeground(new Color(0xf5f5f5));
                        }
                    });

                    Calendar calendar = Calendar.getInstance();
                    Date today = calendar.getTime();

                    // Set minimum selectable date (today - 100 years)
                    calendar.setTime(today);
                    calendar.add(Calendar.YEAR, -100);
                    professorDateChooser.setMinSelectableDate(calendar.getTime());

                    // Set maximum selectable date (today - 18 years)
                    calendar.setTime(today);
                    calendar.add(Calendar.YEAR, -18);
                    professorDateChooser.setMaxSelectableDate(calendar.getTime());

                    // Set initial date displayed in calendar to 01/01/2000
                    calendar.set(2000, Calendar.JANUARY, 1);
                    professorDateChooser.getDateEditor().setDate(calendar.getTime());

                    professorDatePanel.add(professorDateChooser, "cell 0 2");

                    //---- professorDateChooserErrorLabel ----
                    professorDateErrorLabel.setText("Invalid date");
                    professorDateErrorLabel.setFont(new Font("Inter", professorDateErrorLabel.getFont().getStyle(), professorDateErrorLabel.getFont().getSize() + 1));
                    professorDateErrorLabel.setForeground(new Color(0xec221f));
                    professorDateErrorLabel.setBorder(null);
                    professorDateErrorLabel.setMaximumSize(new Dimension(70, 15));
                    professorDateErrorLabel.setMinimumSize(new Dimension(70, 15));
                    professorDateErrorLabel.setPreferredSize(new Dimension(70, 15));
                    professorDateErrorLabel.setVisible(false);
                    professorDatePanel.add(professorDateErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                addProfessorCard.add(professorDatePanel, "cell 0 2");

                //======== generatedProfessorEmailPanel ========
                {
                    generatedProfessorEmailPanel.setOpaque(false);
                    generatedProfessorEmailPanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                    //---- generatedProfessorEmailLabel ----
                    generatedProfessorEmailLabel.setText("Generated Email");
                    generatedProfessorEmailLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                    generatedProfessorEmailLabel.setForeground(new Color(0xf5f5f5));
                    generatedProfessorEmailLabel.setBorder(null);
                    generatedProfessorEmailLabel.setMaximumSize(new Dimension(600, 15));
                    generatedProfessorEmailLabel.setMinimumSize(new Dimension(70, 15));
                    generatedProfessorEmailLabel.setPreferredSize(new Dimension(70, 15));
                    generatedProfessorEmailPanel.add(generatedProfessorEmailLabel, "cell 0 0,growx,gapy null 3");

                    //---- generatedProfessorEmailField ----
                    generatedProfessorEmailField.setBorder(new LineBorder(new Color(0x757575)));
                    generatedProfessorEmailField.setForeground(new Color(0xf3f3f3));
                    generatedProfessorEmailField.setBackground(new Color(0x434343));
                    generatedProfessorEmailField.setSelectedTextColor(Color.white);
                    generatedProfessorEmailField.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    generatedProfessorEmailField.setMinimumSize(new Dimension(320, 40));
                    generatedProfessorEmailField.setMaximumSize(new Dimension(320, 40));
                    generatedProfessorEmailField.setPreferredSize(new Dimension(320, 40));
                    generatedProfessorEmailField.setEditable(false);
                    generatedProfessorEmailField.setHorizontalAlignment(SwingConstants.CENTER);
                    generatedProfessorEmailField.setEnabled(false);
                    generatedProfessorEmailPanel.add(generatedProfessorEmailField, "pad 0,cell 0 2,grow");
                }
                addProfessorCard.add(generatedProfessorEmailPanel, "cell 0 3");

                //======== addProfessorButtonPanel ========
                {
                    addProfessorButtonPanel.setPreferredSize(new Dimension(320, 35));
                    addProfessorButtonPanel.setMaximumSize(new Dimension(320, 35));
                    addProfessorButtonPanel.setLayout(new BorderLayout());

                    //---- addProfessorButton ----
                    addProfessorButton.setText("Add");
                    addProfessorButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    addProfessorButton.setBorder(new LineBorder(new Color(0x757575)));
                    addProfessorButton.setForeground(new Color(0xf5f5f5));
                    addProfessorButton.setBackground(new Color(0x2c2c2c));
                    addProfessorButton.setEnabled(false);
                    addProfessorButtonPanel.add(addProfessorButton, BorderLayout.CENTER);
                }
                addProfessorCard.add(addProfessorButtonPanel, "cell 0 4,gapy null 20");
                }
                addProfessorScrollPane.setViewportView(addProfessorCard);
                addProfessorScrollPane.getViewport().setSize(addProfessorCard.getPreferredSize());
            }
            formPanel.add(addProfessorScrollPane, "Add Professor Card");

            //======== addCourseCard ========
            {
                addCourseCard.setOpaque(false);
                addCourseCard.setMinimumSize(new Dimension(410, 400));
                addCourseCard.setLayout(new MigLayout(
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
                addCourseCard.add(courseNamePanel, "cell 0 0,gapy 0");

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
                addCourseCard.add(courseProfessorEmailPanel, "cell 0 1");

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
                addCourseCard.add(courseTypePanel, "cell 0 2");

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
                addCourseCard.add(addCourseButtonPanel, "cell 0 4,gapy null 20");
            }
            formPanel.add(addCourseCard, "Add Course Card");

            //======== addEnrollmentCard ========
            {
                addEnrollmentCard.setOpaque(false);
                addEnrollmentCard.setMinimumSize(new Dimension(410, 400));
                addEnrollmentCard.setLayout(new MigLayout(
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
                addEnrollmentCard.add(studentEnrollmentEmailPanel, "cell 0 0,gapy 0");

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
                addEnrollmentCard.add(refreshCourseListButtonPanel, "pad 0,cell 0 1,gapy 0");

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
                addEnrollmentCard.add(courseScrollPane, "cell 0 2,grow");

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
                addEnrollmentCard.add(addEnrollmentButtonPanel, "cell 0 3,gapy 0");
            }
            formPanel.add(addEnrollmentCard, "Add Enrollment Card");
        }
        add(formPanel, "cell 1 0,width 410:410:410");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
    private JPanel menuPanel;
    private JLabel menuHeader;
    private JPopupMenu.Separator separator1;
    private JScrollPane optionPane;
    public JTree optionTree;
    private JPopupMenu.Separator separator2;
    public JPanel logoutPanel;
    private Icon logoutIcon;
    private JLabel logoutIconLabel;
    private JLabel logoutClickableLabel;
    public JPanel formPanel;
    public CardLayout cardLayout;
    private JPanel defaultCard;
    private JTextPane chooseOptionMessage;
    public JScrollPane addStudentScrollPane;
    public JPanel addStudentCard;
    public JPanel studentNamePanel;
    private JLabel studentNameLabel;
    public JXFormattedTextField studentNameField;
    public JLabel studentNameErrorLabel;
    private JPanel studentSurnamePanel;
    private JLabel studentSurnameLabel;
    public JXFormattedTextField studentSurnameField;
    public JLabel studentSurnameErrorLabel;
    private JPanel studentDatePanel;
    private JLabel studentDateLabel;
    public JDateChooser studentDateChooser;
    public JLabel studentDateErrorLabel;
    private JPanel generatedStudentEmailPanel;
    private JLabel generatedStudentEmailLabel;
    public JXFormattedTextField generatedStudentEmailField;
    public JPanel addStudentButtonPanel;
    public JButton addStudentButton;
    private JScrollPane addProfessorScrollPane;
    public JPanel addProfessorCard;
    private JPanel professorNamePanel;
    private JLabel professorNameLabel;
    public JXFormattedTextField professorNameField;
    public JLabel professorNameErrorLabel;
    private JPanel professorSurnamePanel;
    private JLabel professorSurnameLabel;
    public JXFormattedTextField professorSurnameField;
    public JLabel professorSurnameErrorLabel;
    private JPanel professorDatePanel;
    private JLabel professorDateLabel;
    public JDateChooser professorDateChooser;
    public JLabel professorDateErrorLabel;
    private JPanel generatedProfessorEmailPanel;
    private JLabel generatedProfessorEmailLabel;
    public JXFormattedTextField generatedProfessorEmailField;
    private JPanel addProfessorButtonPanel;
    public JButton addProfessorButton;
    private JPanel addCourseCard;
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
    public JButton addCourseButton;
    public JPanel addEnrollmentCard;
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
    public JButton addEnrollmentButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
