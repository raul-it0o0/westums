package org.example.uimodels;

import javax.swing.*;
import java.awt.*;

public class StudentLoginFrame extends LoginFrame {
    public StudentLoginFrame() {
        super("Student Login");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        add(panel);

        JLabel label = new JLabel("You are in student panel!");
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(label);
    }
}
