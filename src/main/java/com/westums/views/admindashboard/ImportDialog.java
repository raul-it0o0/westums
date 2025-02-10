package com.westums.views.admindashboard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import com.westums.views.customui.JFileStatusPanel;
import net.miginfocom.swing.*;

public class ImportDialog extends JDialog {

    public ImportDialog(Window owner) {
        super(owner, ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    private void initComponents() {
        importDialogPanel = new JPanel();
        headerPanel = new JPanel();
        headerText = new JLabel();
        instructionsScrollablePanel = new JScrollPane();
        instructionsTextPane = new JTextPane();
        openFilePanel = new JPanel();
        openFileButton = new JButton();
        selectedFileTextPanel = new JFileStatusPanel();
        cancelSubmitButtonsPanel = new JPanel();
        cancelButton = new JButton();
        submitButton = new JButton();

        //======== this ========
        setTitle("Import Data");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== importDialogPanel ========
        {
            importDialogPanel.setBackground(new Color(0x1e1e34));
            importDialogPanel.setLayout(new MigLayout(
                    "insets 10 20 10 20,hidemode 3,align center top",
                    // columns
                    "[grow]",
                    // rows
                    "[]" +
                            "[]" +
                            "[]" +
                            "[]"));

            //======== headerPanel ========
            {
                headerPanel.setOpaque(false);
                headerPanel.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[fill]",
                        // rows
                        "[]"));

                //---- headerText ----
                headerText.setText("Import data through a file");
                headerText.setFont(new Font("Inter 18pt", Font.BOLD, 16));
                headerText.setForeground(new Color(0xf5f5f5));
                headerPanel.add(headerText, "cell 0 0");
            }
            importDialogPanel.add(headerPanel, "cell 0 0,alignx center,grow 0 100");

            //======== instructionsScrollablePanel ========
            {
                instructionsScrollablePanel.setPreferredSize(new Dimension(64, 210));
                instructionsScrollablePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                instructionsScrollablePanel.setFocusable(false);

                //---- instructionsTextPane ----
                instructionsTextPane.setContentType("text/html");
                instructionsTextPane.setText("Instructions");
                instructionsTextPane.setForeground(new Color(0x1e1e1e));
                instructionsTextPane.setBackground(new Color(0xf5f5f5));
                instructionsTextPane.setFont(new Font("Inter 18pt", Font.PLAIN, 12));
                instructionsTextPane.setEditable(false);
                instructionsTextPane.setFocusable(false);
                instructionsScrollablePanel.setViewportView(instructionsTextPane);
            }
            importDialogPanel.add(instructionsScrollablePanel, "cell 0 1,grow");

            //======== openFilePanel ========
            {
                openFilePanel.setOpaque(false);
                openFilePanel.setPreferredSize(new Dimension(219, 40));
                openFilePanel.setLayout(new MigLayout(
                        "fillx,insets 0,hidemode 3,gapx 10",
                        // columns
                        "[fill]" +
                                "[fill]",
                        // rows
                        "[]"));

                //---- openFileButton ----
                openFileButton.setText("Open file...");
                openFileButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                openFileButton.setForeground(new Color(0xf5f5f5));
                openFileButton.setBackground(new Color(0x2c2c2c));
                openFileButton.setBorder(new LineBorder(new Color(0x757575)));
                openFileButton.setHorizontalTextPosition(SwingConstants.CENTER);
                openFilePanel.add(openFileButton, "cell 0 0,grow,width 125,height :40:40");
                openFileButton.setRequestFocusEnabled(true);

                //---- selectedFileTextPanel ----
                selectedFileTextPanel.setState(JFileStatusPanel.STATE_NO_FILE_SELECTED);

                openFilePanel.add(selectedFileTextPanel, "cell 1 0,growx,wmax 275,height :40:40");
            }
            importDialogPanel.add(openFilePanel, "cell 0 2,growx,height 40");

            //======== cancelSubmitButtonsPanel ========
            {
                cancelSubmitButtonsPanel.setOpaque(false);
                cancelSubmitButtonsPanel.setLayout(new MigLayout(
                        "insets 20 0 0 0,hidemode 3,align center bottom,gapx 48",
                        // columns
                        "[fill]" +
                                "[fill]",
                        // rows
                        "[]"));

                //---- cancelButtonPanel ----
                cancelButton.setText("Cancel");
                cancelButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                cancelButton.setForeground(new Color(0xf5f5f5));
                cancelButton.setBackground(new Color(0x2c2c2c));
                cancelButton.setBorder(new LineBorder(new Color(0x757575)));
                cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
                cancelButton.setPreferredSize(new Dimension(125, 40));
                cancelSubmitButtonsPanel.add(cancelButton, "cell 0 0,width 125,height 40:40:40");

                //---- openFileButton3 ----
                submitButton.setText("Submit");
                submitButton.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
                submitButton.setForeground(new Color(0xf5f5f5));
                submitButton.setBackground(new Color(0x2c2c2c));
                submitButton.setBorder(new LineBorder(new Color(0x757575)));
                submitButton.setHorizontalTextPosition(SwingConstants.CENTER);
                submitButton.setEnabled(false); // Initially disabled
                cancelSubmitButtonsPanel.add(submitButton, "cell 1 0,grow,width 125,height :40:40");
            }
            importDialogPanel.add(cancelSubmitButtonsPanel, "cell 0 3,grow");
        }
        contentPane.add(importDialogPanel, BorderLayout.CENTER);
        setSize(450, 400);
        setLocationRelativeTo(getOwner());
    }

    private JPanel importDialogPanel;
    private JPanel headerPanel;
    private JLabel headerText;
    private JScrollPane instructionsScrollablePanel;
    public JTextPane instructionsTextPane;
    private JPanel openFilePanel;
    public JButton openFileButton;
    public JFileStatusPanel selectedFileTextPanel;
    private JPanel cancelSubmitButtonsPanel;
    public JButton cancelButton;
    public JButton submitButton;
}
