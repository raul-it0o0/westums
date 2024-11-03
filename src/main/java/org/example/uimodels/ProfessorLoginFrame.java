package org.example.uimodels;

import javax.swing.*;
import java.awt.*;

public class ProfessorLoginFrame extends LoginFrame {
    public ProfessorLoginFrame() {
        super("Professor Login");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        add(panel);

        JLabel label = new JLabel("You are in professor panel!");
        panel.add(label);
        label.setForeground(Color.red);
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(label);
    }
}
