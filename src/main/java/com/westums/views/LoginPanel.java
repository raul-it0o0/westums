package com.westums.views;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXFormattedTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel() {initComponents();}

    private void initComponents() {
        optionContainer = new JPanel();
        welcomeText = new JPanel();
        welcomeLabel = new JLabel();
        emailPanel = new JPanel();
        emailLabel = new JLabel();
        emailField = new JXFormattedTextField();
        emailErrorLabel = new JLabel();
        passwordPanel = new JPanel();
        passwordLabel = new JLabel();
        inputDescriptionLabel = new JLabel();
        passwordField = new JPasswordField();
        passwordErrorLabel = new JLabel();
        buttonPanel = new JPanel();
        signInButton = new JButton();

        //======== this ========
        {
            this.setBackground(new Color(0x1e1e35));
            this.setLayout(new MigLayout(
                    "insets 0,hidemode 3,align center center,gap 0 0",
                    // columns
                    "[fill]",
                    // rows
                    "[fill]" +
                            "[]"));

            //======== optionContainer ========
            {
                optionContainer.setBackground(Color.white);
                optionContainer.setMinimumSize(new Dimension(320, 206));
                optionContainer.setMaximumSize(new Dimension(320, 2147483647));
                optionContainer.setLayout(new MigLayout(
                        "insets 24,hidemode 3,align center top,gap 0 8",
                        // columns
                        "[fill]",
                        // rows
                        "[fill]" +
                                "[fill]" +
                                "[]" +
                                "[]" +
                                "[]"));

                //======== welcomeText ========
                {
                    welcomeText.setOpaque(false);
                    welcomeText.setMinimumSize(new Dimension(272, 22));
                    welcomeText.setLayout(new FlowLayout());

                    //---- welcomeLabel ----
                    welcomeLabel.setText("Welcome!");
                    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    welcomeLabel.setFont(new Font("Inter 18pt", welcomeLabel.getFont().getStyle(),
                            welcomeLabel.getFont().getSize() + 4));
                    welcomeLabel.setForeground(new Color(0x1e1e1e));
                    welcomeText.add(welcomeLabel);
                }
                optionContainer.add(welcomeText, "cell 0 0");

                //======== emailPanel ========
                {
                    emailPanel.setOpaque(false);
                    emailPanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                    //---- emailLabel ----
                    emailLabel.setText("Email");
                    emailLabel.setFont(new Font("Inter 18pt", emailLabel.getFont().getStyle(), emailLabel.getFont().getSize() + 3));
                    emailLabel.setForeground(new Color(0x1e1e1e));
                    emailLabel.setBorder(null);
                    emailLabel.setMaximumSize(new Dimension(70, 15));
                    emailLabel.setMinimumSize(new Dimension(70, 15));
                    emailLabel.setPreferredSize(new Dimension(70, 15));
                    emailPanel.add(emailLabel, "cell 0 0,gapy null 3");

                    //---- emailField ----
                    emailField.setBorder(new LineBorder(new Color(0x5a5a5a)));
                    emailField.setForeground(new Color(0xb3b3b3));
                    emailField.setBackground(new Color(0x5a5a5a));
                    emailField.setSelectedTextColor(Color.white);
                    emailField.setFont(new Font("Inter 18pt", Font.PLAIN, 15));
                    emailField.setMinimumSize(new Dimension(100, 40));
                    emailField.setMaximumSize(new Dimension(272, 40));
                    emailPanel.add(emailField, "cell 0 2");

                    //---- emailErrorLabel ----
                    emailErrorLabel.setText("Invalid email");
                    emailErrorLabel.setFont(new Font("Inter 18pt", emailErrorLabel.getFont().getStyle(), emailErrorLabel.getFont().getSize() + 1));
                    emailErrorLabel.setForeground(new Color(0x900b09));
                    emailErrorLabel.setBorder(null);
                    emailErrorLabel.setMaximumSize(new Dimension(70, 15));
                    emailErrorLabel.setMinimumSize(new Dimension(70, 15));
                    emailErrorLabel.setPreferredSize(new Dimension(70, 15));
                    emailErrorLabel.setVisible(false);
                    emailPanel.add(emailErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                optionContainer.add(emailPanel, "cell 0 1");

                //======== passwordPanel ========
                {
                    passwordPanel.setOpaque(false);
                    passwordPanel.setVisible(false);
                    passwordPanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3,gap 0 0",
                            // columns
                            "[grow,fill]",
                            // rows
                            "[grow,fill]" +
                                    "[0,grow,fill]" +
                                    "[grow,fill]" +
                                    "[0]"));

                    //---- passwordLabel ----
                    passwordLabel.setText("Password");
                    passwordLabel.setFont(new Font("Inter 18pt", passwordLabel.getFont().getStyle(), passwordLabel.getFont().getSize() + 3));
                    passwordLabel.setForeground(new Color(0x1e1e1e));
                    passwordLabel.setBorder(null);
                    passwordLabel.setMaximumSize(new Dimension(70, 15));
                    passwordLabel.setMinimumSize(new Dimension(70, 15));
                    passwordLabel.setPreferredSize(new Dimension(70, 15));
                    passwordPanel.add(passwordLabel, "cell 0 0,gapy null 3");

                    //---- inputDescriptionLabel ----
                    inputDescriptionLabel.setText("Set a new password for your account");
                    inputDescriptionLabel.setFont(new Font("Inter 18pt", inputDescriptionLabel.getFont().getStyle(), inputDescriptionLabel.getFont().getSize() + 1));
                    inputDescriptionLabel.setForeground(new Color(0x757575));
                    inputDescriptionLabel.setBorder(null);
                    inputDescriptionLabel.setMaximumSize(new Dimension(70, 15));
                    inputDescriptionLabel.setMinimumSize(new Dimension(70, 15));
                    inputDescriptionLabel.setPreferredSize(new Dimension(70, 15));
                    inputDescriptionLabel.setVisible(false);
                    passwordPanel.add(inputDescriptionLabel, "cell 0 1,grow,width 272:272:272,gapy null 2");

                    //---- passwordField ----
                    passwordField.setBorder(new LineBorder(new Color(0x5a5a5a)));
                    passwordField.setForeground(new Color(0xb3b3b3));
                    passwordField.setBackground(new Color(0x5a5a5a));
                    passwordField.setSelectedTextColor(Color.white);
                    passwordField.setFont(new Font("Inter 18pt", Font.PLAIN, 15));
                    passwordField.setMinimumSize(new Dimension(100, 40));
                    passwordField.setMaximumSize(new Dimension(272, 40));
                    passwordPanel.add(passwordField, "cell 0 2");

                    //---- passwordErrorLabel ----
                    passwordErrorLabel.setText("Invalid password");
                    passwordErrorLabel.setFont(new Font("Inter 18pt", passwordErrorLabel.getFont().getStyle(), passwordErrorLabel.getFont().getSize() + 1));
                    passwordErrorLabel.setForeground(new Color(0x900b09));
                    passwordErrorLabel.setBorder(null);
                    passwordErrorLabel.setMaximumSize(new Dimension(70, 15));
                    passwordErrorLabel.setMinimumSize(new Dimension(70, 15));
                    passwordErrorLabel.setPreferredSize(new Dimension(70, 15));
                    passwordErrorLabel.setVisible(false);
                    passwordPanel.add(passwordErrorLabel, "cell 0 3,grow,width 272:272:272");
                }
                optionContainer.add(passwordPanel, "cell 0 2,gapy 0 0");

                //======== buttonPanel ========
                {
                    buttonPanel.setMinimumSize(new Dimension(272, 40));
                    buttonPanel.setLayout(new BorderLayout());

                    //---- button1 ----
                    signInButton.setText("Sign In");

                    signInButton.setFont(new Font("Inter 18pt", Font.PLAIN, 15));
                    buttonPanel.add(signInButton, BorderLayout.CENTER);
                }
                optionContainer.add(buttonPanel, "cell 0 3,gapy 17");
            }
            this.add(optionContainer, "cell 0 0");
        }
    }

    private JPanel optionContainer;
    private JPanel welcomeText;
    private JLabel welcomeLabel;
    private JPanel emailPanel;
    private JLabel emailLabel;
    public JXFormattedTextField emailField;
    public JLabel emailErrorLabel;
    public JPanel passwordPanel;
    private JLabel passwordLabel;
    public JLabel inputDescriptionLabel;
    public JPasswordField passwordField;
    public JLabel passwordErrorLabel;
    private JPanel buttonPanel;
    public JButton signInButton;

}
