package org.example.uimodels;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        setFocusable(false);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBackground(new Color(0x6A7E8D));
        setForeground(new Color(0xEFEFEF));

    }
}
