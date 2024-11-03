package org.example.uimodels;

import javax.swing.*;
import java.awt.*;

public class StudentLogin extends JFrame {
    public StudentLogin() {
        super("Student Login");

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(0x082941));

        JLabel label = new JLabel("You are in student panel!");
        add(label);
    }
}
