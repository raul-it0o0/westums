package com.westums.views.admindashboard;

import com.toedter.calendar.JDateChooser;
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

public class AddProfessorCard extends JScrollPane {

    public AddProfessorCard() {
        super();
        initComponents();
    }

    private void initComponents() {
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
        addProfessorButton = new JAddButton(JAddButton.ADD_TYPE);

        //======== this ========
        {
            this.getViewport().setBackground(new Color(0x1e1e34));
            this.setBorder(null);
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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
            this.setViewportView(addProfessorCard);
            this.getViewport().setSize(addProfessorCard.getPreferredSize());
        }
    }

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
    public JAddButton addProfessorButton;

}
