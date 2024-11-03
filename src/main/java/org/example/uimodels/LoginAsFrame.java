package org.example.uimodels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAsFrame extends LoginFrame implements ActionListener {

    public JButton studentButton;
    public JButton professorButton;

    public LoginAsFrame() {
        super("Login Screen");

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
            StudentLoginFrame studentLoginFrame = new StudentLoginFrame();
            studentLoginFrame.setVisible(true);
        }
        else if (e.getSource() == professorButton) {
            setVisible(false);
            ProfessorLoginFrame professorPanel = new ProfessorLoginFrame();
            professorPanel.setVisible(true);
        }
    }
}
