package com.westums.views.customui;

import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JFileStatusPanel extends JPanel {

    public static final int STATE_NO_FILE_SELECTED = 0;
    public static final int STATE_VALID_FILE_SELECTED = 1;
    public static final int STATE_PROCESSING = 2;
    public static final int STATE_REJECTED = 3;
    public static final int STATE_ACCEPTED = 4;

    public JFileStatusPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        selectedFileTextLabel = new JLabel();
        selectedFileIcon = new JLabel();
        cancelSubmitButtonsPanel = new JPanel();
        cancelButton = new JButton();
        submitButton = new JButton();

        // ======== this ========
        setBackground(new Color(0xe6e6e6));
        setBorder(new LineBorder(new Color(0xd9d9d9)));
        setPreferredSize(null);
        setLayout(new MigLayout(
                "fillx,insets 10 12 10 12,hidemode 3,align leading center,gapx 0",
                // columns
                "[fill]" +
                        "[fill]",
                // rows
                "[]"));

        //---- selectedFileTextLabel ----
        selectedFileTextLabel.setFont(new Font("Inter 18pt", selectedFileTextLabel.getFont().getStyle(), 16));
        selectedFileTextLabel.setPreferredSize(new Dimension(231, 20));
        add(selectedFileTextLabel, "cell 0 0,grow,wmax 231");

        //---- selectedFileIcon ----
        selectedFileIcon.setVisible(false); // Initially hidden
        add(selectedFileIcon, "cell 1 0,alignx right,grow 0 100,wmax 20");
    }

    public void setState(int state) {
        switch (state) {
            case STATE_NO_FILE_SELECTED: {
                selectedFileTextLabel.setText("No file selected");
                selectedFileTextLabel.setForeground(new Color(0x757575));
                selectedFileIcon.setVisible(false);
                break;
            }
            case STATE_VALID_FILE_SELECTED: {
                selectedFileTextLabel.setForeground(new Color(0x000000));
                selectedFileIcon.setVisible(false);
                break;
            }
            case STATE_PROCESSING: {
                selectedFileTextLabel.setForeground(new Color(0x000000));
                FontIcon icon = FontIcon.of(FontAwesome.HOURGLASS_2);
                icon.setIconSize(12);
                icon.setIconColor(new Color(0x434343));
                selectedFileIcon.setIcon(icon);
                selectedFileIcon.setVisible(true);
                break;
            }
            case STATE_REJECTED: {
                selectedFileTextLabel.setForeground(new Color(0x000000));
                FontIcon icon = FontIcon.of(FontAwesome.EXCLAMATION_CIRCLE);
                icon.setIconSize(12);
                icon.setIconColor(new Color(0xC00F0C));
                selectedFileIcon.setIcon(icon);
                selectedFileIcon.setVisible(true);
                break;
            }
            case STATE_ACCEPTED: {
                selectedFileTextLabel.setForeground(new Color(0x000000));
                FontIcon icon = FontIcon.of(FontAwesome.CHECK);
                icon.setIconSize(12);
                icon.setIconColor(new Color(0x009951));
                selectedFileIcon.setIcon(icon);
                selectedFileIcon.setVisible(true);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid state: " + state);
        }
    }

    public void setText(String text) {
        selectedFileTextLabel.setText(text);
    }

    private JLabel selectedFileTextLabel;
    private JLabel selectedFileIcon;
    private JPanel cancelSubmitButtonsPanel;
    private JButton cancelButton;
    private JButton submitButton;
}
