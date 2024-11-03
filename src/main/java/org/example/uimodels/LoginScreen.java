package org.example.uimodels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel implements ActionListener {

    public JButton studentButton;
    public JButton professorButton;

    public LoginScreen() {
        super();
        setOpaque(false);
        JLabel label = new JLabel("Login as:");
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
//        label.setHorizontalAlignment(SwingConstants.RIGHT);
        add(label);

        studentButton = new JButton("Student");
        studentButton.addActionListener(this);
        professorButton = new JButton("Professor");
        professorButton.addActionListener(this);

        add(studentButton);
        add(professorButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentButton) {
            // If an action (i.e. pressing) was performed with the studentButton
            System.out.println("Student Button pressed");
            this.setVisible(false);
            StudentLogin studentLogin = new StudentLogin();
            studentLogin.setVisible(true);
        }
        else if (e.getSource() == professorButton) {
            System.out.println("Professor Button pressed");
            this.setVisible(false);
            ProfessorPanel professorPanel = new ProfessorPanel();
            professorPanel.setVisible(true);
        }
    }
}
