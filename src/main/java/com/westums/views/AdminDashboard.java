/*
 * Created by JFormDesigner on Wed Dec 04 12:42:21 EET 2024
 */

package com.westums.views;

import java.awt.*;
import java.awt.image.ComponentColorModel;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.PanelUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.*;
import com.jgoodies.forms.factories.*;
import com.toedter.calendar.*;
import jiconfont.icons.typicons.Typicons;
import jiconfont.swing.IconFontSwing;
import net.miginfocom.swing.*;
import org.jdesktop.swingx.*;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
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
        generatedStudentEmailPanel = new JPanel();
        generatedStudentEmailLabel = new JLabel();
        generatedStudentEmailField = new JXFormattedTextField();
        addStudentButtonPanel = new JPanel();
        addStudentButton = new JButton();
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
        courseProfessorEmailErrorLabel = new JLabel();
        courseTypePanel = new JPanel();
        courseTypeLabel = new JLabel();
        courseTypeComboBox = new JComboBox();
        addCourseButtonPanel = new JPanel();
        addCourseButton = new JButton();
        cardLayout = new CardLayout();

        //======== this ========
        setBackground(new Color(0x1e1e34));
        setMinimumSize(new Dimension(320, 206));
        setPreferredSize(new Dimension(320, 245));
        /*
        setSize(new Dimension(600, 400));
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
         */
        setLayout(new MigLayout(
                "fillx,insets 0,hidemode 3,align center center,gap 0 0",
                // columns
                "[fill]",
                // rows
                "[fill]" +
                        "[]"));
        /*
        setLayout(new MigLayout(
                "fill,insets 0,hidemode 3,align center center,gap 0 0",
                // columns
                "[190:319,fill]" +
                        "[191,fill]",
                // rows
                "[]"));
         */

        //======== menuPanel ========
        {
            menuPanel.setBackground(new Color(0xf5f5f5));
//            menuPanel.setMinimumSize(new Dimension(190, 400));
//            menuPanel.setMaximumSize(new Dimension(190, 400));
//            menuPanel.setPreferredSize(new Dimension(190, 400));
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
            formPanel.setPreferredSize(new Dimension(410, 390));
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

            //======== addStudentCard ========
            {
                addStudentCard.setOpaque(false);
                addStudentCard.setMinimumSize(new Dimension(410, 400));
                addStudentCard.setLayout(new MigLayout(
                    "fill,insets 12 45 20 45,hidemode 3,align center center,gapy 16",
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
                    studentNameErrorLabel.setForeground(new Color(0x900b09));
                    studentNameErrorLabel.setBorder(null);
                    studentNameErrorLabel.setMaximumSize(new Dimension(70, 15));
                    studentNameErrorLabel.setMinimumSize(new Dimension(70, 15));
                    studentNameErrorLabel.setPreferredSize(new Dimension(70, 15));
                    studentNameErrorLabel.setVisible(false);
                    studentNamePanel.add(studentNameErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                addStudentCard.add(studentNamePanel, "cell 0 0,gapy 12");

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
                    studentSurnameErrorLabel.setForeground(new Color(0x900b09));
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
                    studentDatePanel.add(studentDateChooser, "cell 0 2");
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
                    addStudentButtonPanel.setMinimumSize(new Dimension(272, 40));
                    addStudentButtonPanel.setLayout(new BorderLayout());

                    //---- addStudentButton ----
                    addStudentButton.setText("Add");
                    addStudentButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    addStudentButton.setBorder(new LineBorder(new Color(0x757575)));
                    addStudentButton.setForeground(new Color(0xf5f5f5));
                    addStudentButton.setBackground(new Color(0x2c2c2c));
                    addStudentButtonPanel.add(addStudentButton, BorderLayout.CENTER);
                }
                addStudentCard.add(addStudentButtonPanel, "cell 0 4,gapy null 20");
            }
            formPanel.add(addStudentCard, "Add Student Card");

            //======== addProfessorCard ========
            {
                addProfessorCard.setOpaque(false);
                addProfessorCard.setMinimumSize(new Dimension(410, 400));
                addProfessorCard.setLayout(new MigLayout(
                        "fill,insets 12 45 20 45,hidemode 3,align center center,gapy 16",
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
                    professorNameErrorLabel.setForeground(new Color(0x900b09));
                    professorNameErrorLabel.setBorder(null);
                    professorNameErrorLabel.setMaximumSize(new Dimension(70, 15));
                    professorNameErrorLabel.setMinimumSize(new Dimension(70, 15));
                    professorNameErrorLabel.setPreferredSize(new Dimension(70, 15));
                    professorNameErrorLabel.setVisible(false);
                    professorNamePanel.add(professorNameErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                addProfessorCard.add(professorNamePanel, "cell 0 0,gapy 12");

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
                    professorSurnameErrorLabel.setForeground(new Color(0x900b09));
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
                    professorDatePanel.add(professorDateChooser, "cell 0 2");
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
                    addProfessorButtonPanel.setMinimumSize(new Dimension(272, 40));
                    addProfessorButtonPanel.setLayout(new BorderLayout());

                    //---- addProfessorButton ----
                    addProfessorButton.setText("Add");
                    addProfessorButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    addProfessorButton.setBorder(new LineBorder(new Color(0x757575)));
                    addProfessorButton.setForeground(new Color(0xf5f5f5));
                    addProfessorButton.setBackground(new Color(0x2c2c2c));
                    addProfessorButtonPanel.add(addProfessorButton, BorderLayout.CENTER);
                }
                addProfessorCard.add(addProfessorButtonPanel, "cell 0 4,gapy null 20");
            }
            formPanel.add(addProfessorCard, "Add Professor Card");

            //======== addCourseCard ========
            {
                addCourseCard.setOpaque(false);
                addCourseCard.setMinimumSize(new Dimension(410, 400));
                addCourseCard.setLayout(new MigLayout(
                        "fill,insets 12 45 20 45,hidemode 3,align center center,gapy 16",
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
                    courseNameErrorLabel.setForeground(new Color(0x900b09));
                    courseNameErrorLabel.setBorder(null);
                    courseNameErrorLabel.setMaximumSize(new Dimension(70, 15));
                    courseNameErrorLabel.setMinimumSize(new Dimension(70, 15));
                    courseNameErrorLabel.setPreferredSize(new Dimension(70, 15));
                    courseNameErrorLabel.setVisible(false);
                    courseNamePanel.add(courseNameErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                addCourseCard.add(courseNamePanel, "cell 0 0,gapy 12");

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
                                    "[0]"));

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
                    courseProfessorEmailErrorLabel.setText("Invalid email");
                    courseProfessorEmailErrorLabel.setFont(new Font("Inter 18pt",
                            courseProfessorEmailErrorLabel.getFont().getStyle(), courseProfessorEmailErrorLabel.getFont().getSize() + 1));
                    courseProfessorEmailErrorLabel.setForeground(new Color(0x900b09));
                    courseProfessorEmailErrorLabel.setBorder(null);
                    courseProfessorEmailErrorLabel.setMaximumSize(new Dimension(70, 15));
                    courseProfessorEmailErrorLabel.setMinimumSize(new Dimension(70, 15));
                    courseProfessorEmailErrorLabel.setPreferredSize(new Dimension(70, 15));
                    courseProfessorEmailErrorLabel.setVisible(false);
                    courseProfessorEmailPanel.add(courseProfessorEmailErrorLabel, "cell 0 3,grow,width 272:272:272");
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
                    courseTypeComboBox.setBorder(new LineBorder(new Color(0x757575)));
                    courseTypeComboBox.setForeground(new Color(0x1e1e1e));
                    courseTypeComboBox.setBackground(new Color(0xf5f5f5));
                    courseTypeComboBox.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                    courseTypeComboBox.setMinimumSize(new Dimension(320, 40));
                    courseTypeComboBox.setMaximumSize(new Dimension(320, 40));
                    courseTypeComboBox.setPreferredSize(new Dimension(320, 40));
                    courseTypePanel.add(courseTypeComboBox, "pad 0,cell 0 2,grow");
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
                    addCourseButtonPanel.add(addCourseButton, BorderLayout.CENTER);
                }
                addCourseCard.add(addCourseButtonPanel, "cell 0 4,gapy null 20");
            }
            formPanel.add(addCourseCard, "Add Course Card");
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
    private JPanel formPanel;
    public CardLayout cardLayout;
    private JPanel defaultCard;
    private JTextPane chooseOptionMessage;
    private JPanel addStudentCard;
    // new todo: delete this comment
    private JPanel studentNamePanel;
    private JLabel studentNameLabel;
    private JXFormattedTextField studentNameField;
    private JLabel studentNameErrorLabel;
    private JPanel studentSurnamePanel;
    private JLabel studentSurnameLabel;
    private JXFormattedTextField studentSurnameField;
    private JLabel studentSurnameErrorLabel;
    private JPanel studentDatePanel;
    private JLabel studentDateLabel;
    private JDateChooser studentDateChooser;
    private JPanel generatedStudentEmailPanel;
    private JLabel generatedStudentEmailLabel;
    private JXFormattedTextField generatedStudentEmailField;
    private JPanel addStudentButtonPanel;
    private JButton addStudentButton;
    private JPanel addProfessorCard;
    private JPanel professorNamePanel;
    private JLabel professorNameLabel;
    private JXFormattedTextField professorNameField;
    private JLabel professorNameErrorLabel;
    private JPanel professorSurnamePanel;
    private JLabel professorSurnameLabel;
    private JXFormattedTextField professorSurnameField;
    private JLabel professorSurnameErrorLabel;
    private JPanel professorDatePanel;
    private JLabel professorDateLabel;
    private JDateChooser professorDateChooser;
    private JPanel generatedProfessorEmailPanel;
    private JLabel generatedProfessorEmailLabel;
    private JXFormattedTextField generatedProfessorEmailField;
    private JPanel addProfessorButtonPanel;
    private JButton addProfessorButton;
    private JPanel addCourseCard;
    private JPanel courseNamePanel;
    private JLabel courseNameLabel;
    private JXFormattedTextField courseNameField;
    private JLabel courseNameErrorLabel;
    private JPanel courseProfessorEmailPanel;
    private JLabel courseProfessorEmailLabel;
    private JXFormattedTextField courseProfessorEmailField;
    private JLabel courseProfessorEmailErrorLabel;
    private JPanel courseTypePanel;
    private JLabel courseTypeLabel;
    private JComboBox courseTypeComboBox;
    private JPanel addCourseButtonPanel;
    private JButton addCourseButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
