package com.westums.models.uimodels;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private JPanel panel;
    private JTextPane textPane;
    private JLabel iconLabel;

    public CustomTableCellRenderer() {
        panel = new JPanel(new MigLayout(
                "fill,insets 10 12 10 12,hidemode 3,align center center,gapx 10",
                // columns
                "[fill]" +
                        "[fill]",
                // rows
                "[]"));
        textPane = new JTextPane();
        iconLabel = new JLabel();

        //======== panel ========
        panel.setBorder(new LineBorder(new Color(0x5b5b5b)));

        //---- textPane ----
        textPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setText("Abc");
        textPane.setFont(new Font("Inter", Font.PLAIN, 12));
        textPane.setForeground(new Color(0xf3f3f3));
        add(textPane, "cell 0 0,growy");

        //---- iconLabel ----
        iconLabel.setVisible(false);
        add(iconLabel, "cell 1 0,growy");
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) {
            setText(String.valueOf(value));
        } else {
            super.setValue(value);
        }
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        textPane.setText("this is a cell.");

        panel.setBackground(new Color(0x5B5B5B));

        return this;
    }
}
