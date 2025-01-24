package com.westums.views.admindashboard;

import com.toedter.calendar.JDateChooser;
import com.westums.views.AdminDashboard;
import com.westums.views.customui.JAddButton;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXFormattedTextField;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class AddStudentCard extends JScrollPane {

    public AddStudentCard() {
        super();
        initComponents();
    }

    private void initComponents() {
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
        addStudentButton = new JAddButton(JAddButton.ADD_TYPE);

        //======== this ========
        {
            this.getViewport().setBackground(new Color(0x1e1e34));
            this.setBorder(null);
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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
            this.setViewportView(addStudentCard);
            this.getViewport().setSize(addStudentCard.getPreferredSize());
        }
    }

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
    public JAddButton addStudentButton;
}
