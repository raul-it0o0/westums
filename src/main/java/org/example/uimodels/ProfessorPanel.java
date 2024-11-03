package org.example.uimodels;

import javax.swing.*;
import java.awt.*;

public class ProfessorPanel extends JFrame {
    public ProfessorPanel() {
        super("Professor Login");

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(0x082941));

        JLabel label = new JLabel("You are in professor panel!");
        add(label);
    }
}
