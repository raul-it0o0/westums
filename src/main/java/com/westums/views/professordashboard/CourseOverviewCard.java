package com.westums.views.professordashboard;

import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class CourseOverviewCard extends JPanel {

    public CourseOverviewCard() {
        super();
        initComponents();
    }

    private void initComponents() {
        statsPanelScrollPane = new JScrollPane();
        statsPanel = new JPanel();
        enrolledStudentsStatPanel = new JPanel();
        enrolledStudentsStat = new JLabel();
        enrolledStudentsLabel = new JLabel();
        attendancePercentageStatPanel = new JPanel();
        attendancePercentageStat = new JLabel();
        attendancePercentageLabel = new JLabel();
        averageGradeStatPanel = new JPanel();
        averageGradeStat = new JLabel();
        averageGradeStatLabel = new JLabel();
        evaluationsPanelScrollPane = new JScrollPane();
        evaluationsList = new JList();
        attendancePanelScrollPane = new JScrollPane();
        attendanceList = new JList();
        noEvaluationsPanel = new JPanel();
        noEvaluationsText = new JLabel();

        //======== this ========
        {
            this.setOpaque(false);
            this.setLayout(new MigLayout(
                    "fillx,insets 12,hidemode 3,aligny top,gapy 10",
                    // columns
                    "[fill]",
                    // rows
                    "[112]" +
                            "[73]" +
                            "[73]"));

            //======== statsPanelScrollPane ========
            {
                statsPanelScrollPane.setOpaque(false);
                statsPanelScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                statsPanelScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                statsPanelScrollPane.setBorder(null);
                statsPanelScrollPane.setPreferredSize(new Dimension(494, 112));

                //======== statsPanel ========
                {
                    statsPanel.setBackground(new Color(0xe6e6e6));
                    statsPanel.setLayout(new MigLayout(
                            "fill,insets 10,hidemode 3,align center center,gapx 10",
                            // columns
                            "[fill]",
                            // rows
                            "[]"));

                    //======== enrolledStudentsStatPanel ========
                    {
                        enrolledStudentsStatPanel.setBackground(new Color(0x55828b));
                        enrolledStudentsStatPanel.setLayout(new MigLayout(
                                "fill,insets 13 0 13 0,hidemode 3,align center center",
                                // columns
                                "[fill]",
                                // rows
                                "[]" +
                                        "[]"));

                        //---- enrolledStudentsStat ----
                        enrolledStudentsStat.setText("null");
                        FontIcon enrolledStudentsIcon = FontIcon.of(FontAwesome.USERS);
                        enrolledStudentsIcon.setIconColor(Color.white);
                        enrolledStudentsIcon.setIconSize(39);
                        enrolledStudentsStat.setIcon(enrolledStudentsIcon);
                        enrolledStudentsStat.setHorizontalAlignment(SwingConstants.CENTER);
                        enrolledStudentsStat.setFont(new Font("Inter 18pt", Font.BOLD, 24));
                        enrolledStudentsStat.setForeground(Color.white);
                        enrolledStudentsStatPanel.add(enrolledStudentsStat, "cell 0 0");

                        //---- enrolledStudentsLabel ----
                        enrolledStudentsLabel.setText("Enrolled Students");
                        enrolledStudentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        enrolledStudentsLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        enrolledStudentsLabel.setForeground(Color.white);
                        enrolledStudentsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                        enrolledStudentsStatPanel.add(enrolledStudentsLabel, "cell 0 1,aligny bottom,growy 0");
                    }
                    statsPanel.add(enrolledStudentsStatPanel, "cell 0 0,aligny center,grow,width :147:147");

                    //======== attendancePercentageStatPanel ========
                    {
                        attendancePercentageStatPanel.setBackground(new Color(0xfc9e4f));
                        attendancePercentageStatPanel.setLayout(new MigLayout(
                                "fill,insets 13 null 13 null,hidemode 3,align center center",
                                // columns
                                "[fill]",
                                // rows
                                "[]" +
                                        "[]"));

                        //---- attendancePercentageStat ----
                        attendancePercentageStat.setText("null");
                        attendancePercentageStat.setHorizontalAlignment(SwingConstants.CENTER);
                        attendancePercentageStat.setFont(new Font("Inter 18pt", Font.BOLD, 24));
                        attendancePercentageStat.setForeground(Color.black);
                        attendancePercentageStatPanel.add(attendancePercentageStat, "cell 0 0");

                        //---- attendancePercentageLabel ----
                        attendancePercentageLabel.setText("Average Attendance");
                        attendancePercentageLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        attendancePercentageLabel.setForeground(Color.black);
                        attendancePercentageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        attendancePercentageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                        attendancePercentageStatPanel.add(attendancePercentageLabel, "cell 0 1,aligny bottom,grow 100 0");
                    }
                    statsPanel.add(attendancePercentageStatPanel, "cell 1 0,grow,width 147");

                    //======== averageGradeStatPanel ========
                    {
                        averageGradeStatPanel.setBackground(new Color(0xbc2c1a));
                        averageGradeStatPanel.setLayout(new MigLayout(
                                "fill,insets 13 null 13 null,hidemode 3,align center center",
                                // columns
                                "[fill]",
                                // rows
                                "[]" +
                                        "[]"));

                        //---- averageGradeStat ----
                        averageGradeStat.setText("null");
                        averageGradeStat.setHorizontalAlignment(SwingConstants.CENTER);
                        averageGradeStat.setFont(new Font("Inter 18pt", Font.BOLD, 24));
                        averageGradeStat.setForeground(Color.white);
                        averageGradeStatPanel.add(averageGradeStat, "cell 0 0");

                        //---- averageGradeStatLabel ----
                        averageGradeStatLabel.setText("Average Grade");
                        averageGradeStatLabel.setFont(new Font("Inter 18pt", Font.BOLD, 14));
                        averageGradeStatLabel.setForeground(Color.white);
                        averageGradeStatLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        averageGradeStatLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                        averageGradeStatPanel.add(averageGradeStatLabel, "cell 0 1,aligny bottom,grow 100 0");
                    }
                    statsPanel.add(averageGradeStatPanel, "cell 2 0,grow,width 147");
                }
                statsPanelScrollPane.setViewportView(statsPanel);
            }
            this.add(statsPanelScrollPane, "cell 0 0");

            //======== evaluationsPanelScrollPane ========
            {
                evaluationsPanelScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                evaluationsPanelScrollPane.setViewportBorder(null);
                evaluationsPanelScrollPane.setBorder(null);
                evaluationsPanelScrollPane.setMaximumSize(new Dimension(386, 73));
                evaluationsPanelScrollPane.setViewportView(evaluationsList);
                // NOTE: Unimplemented, so setting to invisible
                evaluationsPanelScrollPane.setVisible(false);
            }
            this.add(evaluationsPanelScrollPane, "cell 0 1,height 73:73:73");

            //======== attendancePanelScrollPane ========
            {
                attendancePanelScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                attendancePanelScrollPane.setViewportBorder(null);
                attendancePanelScrollPane.setBorder(null);
                attendancePanelScrollPane.setMaximumSize(new Dimension(386, 73));
                attendancePanelScrollPane.setViewportView(attendanceList);
                // NOTE: Unimplemented, so setting to invisible
                attendancePanelScrollPane.setVisible(false);
            }
            this.add(attendancePanelScrollPane, "cell 0 2,height 73:73:73");
        }

    }

    private JScrollPane statsPanelScrollPane;
    private JPanel statsPanel;
    private JPanel enrolledStudentsStatPanel;
    public JLabel enrolledStudentsStat;
    private JLabel enrolledStudentsLabel;
    private JPanel attendancePercentageStatPanel;
    public JLabel attendancePercentageStat;
    private JLabel attendancePercentageLabel;
    private JPanel averageGradeStatPanel;
    public JLabel averageGradeStat;
    private JLabel averageGradeStatLabel;
    private JScrollPane evaluationsPanelScrollPane;
    private JList evaluationsList;
    private JScrollPane attendancePanelScrollPane;
    private JList attendanceList;
    private JPanel noEvaluationsPanel;
    private JLabel noEvaluationsText;
}
