package com.westums.views;

import com.westums.uimodels.CustomButton;
import com.westums.uimodels.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAsFrame extends LoginFrame implements ActionListener {

    public CustomButton studentButton;
    public CustomButton professorButton;
    public CustomButton registerButton;

    public LoginAsFrame() {
        super("Login Screen");

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JLabel label = new JLabel("Login as:");
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        studentButton = new CustomButton("Student");
        studentButton.addActionListener(this);
        professorButton = new CustomButton("Professor");
        professorButton.addActionListener(this);
        registerButton = new CustomButton("Register");
        registerButton.addActionListener(this);

        panel.add(label);
        panel.add(studentButton);
        panel.add(professorButton);
        panel.add(registerButton);
        super.add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentButton) {
            // If an action (i.e. pressing) was performed with the studentButton
            dispose();
            StudentLoginFrame studentLoginFrame = new StudentLoginFrame();
            studentLoginFrame.setVisible(true);
        }
        else if (e.getSource() == professorButton) {
            dispose();
            ProfessorLoginFrame professorPanel = new ProfessorLoginFrame();
            professorPanel.setVisible(true);
        }
        else if (e.getSource() == registerButton) {
            dispose();
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
        }
    }
}
