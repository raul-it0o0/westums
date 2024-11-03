package com.westums.uimodels;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setFocusable(false);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBackground(new Color(0x6A7E8D));
        setForeground(new Color(0xEFEFEF));

    }
}
