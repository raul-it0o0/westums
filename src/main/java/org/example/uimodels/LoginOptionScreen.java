package org.example.uimodels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginOptionScreen extends JFrame implements ActionListener {

    public JButton studentButton;
    public JButton professorButton;

    public LoginOptionScreen() {

        super("Login Screen");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(0x082941));

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JLabel label = new JLabel("Login as:");
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);

        studentButton = new JButton("Student");
        studentButton.addActionListener(this);
        professorButton = new JButton("Professor");
        professorButton.addActionListener(this);

        panel.add(label);
        panel.add(studentButton);
        panel.add(professorButton);
        super.add(panel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentButton) {
            // If an action (i.e. pressing) was performed with the studentButton
            setVisible(false);
            StudentLogin studentLogin = new StudentLogin();
            studentLogin.setVisible(true);
        }
        else if (e.getSource() == professorButton) {
            setVisible(false);
            ProfessorPanel professorPanel = new ProfessorPanel();
            professorPanel.setVisible(true);
        }
    }
}
