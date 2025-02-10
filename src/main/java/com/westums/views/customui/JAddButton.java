package com.westums.views.customui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JAddButton extends JButton {

    public static final int SUCCESS_STATE = 1;
    public static final int DEFAULT_STATE = 0;

    public static final int ADD_TYPE = 0;
    public static final int SUBMIT_TYPE = 1;

    private int state;
    private final int type;

    public JAddButton(int type) {
        super();
        this.type = type;
    }

    public void setState(int state) {
        // TODO: Fix button to change its color
        switch (state) {
            case SUCCESS_STATE:
                setBackground(new Color(0x14ae5c));
                getParent().setBackground(new Color(0x14ae5c));
                setForeground(new Color(0xf5f5f5));
                setBorder(null);
                if (type == ADD_TYPE) {
                    setText("Added");
                } else if (type == SUBMIT_TYPE) {
                    setText("Submitted");
                }
                setEnabled(false);
                break;
            case DEFAULT_STATE:
                setBackground(new Color(0x2c2c2c));
                getParent().setBackground(new Color(0x2c2c2c));
                setForeground(new Color(0xf5f5f5));
                setBorder(new LineBorder(new Color(0x757575)));
                if (type == ADD_TYPE) {
                    setText("Add");
                } else if (type == SUBMIT_TYPE) {
                    setText("Submit");
                }
                setEnabled(false);
                break;
            default:
                throw new IllegalArgumentException("Invalid state: " + state);
        }

        this.state = state;
    }

    public int getState() {
        return state;
    }
}
