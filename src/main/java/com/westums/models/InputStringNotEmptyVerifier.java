package com.westums.models;

import javax.swing.*;

public class InputStringNotEmptyVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent txtField) {
        JFormattedTextField field = (JFormattedTextField)txtField;
        String text = field.getText();
        return !text.isEmpty();
    }
}

/*
JOptionPane.showMessageDialog(
                    null,
                    "Fields cannot be empty!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return false;
 */
