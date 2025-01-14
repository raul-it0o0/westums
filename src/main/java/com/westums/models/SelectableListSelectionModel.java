package com.westums.models;

import javax.swing.*;

public class SelectableListSelectionModel extends DefaultListSelectionModel {
    @Override
    public void setSelectionInterval(int index0, int index1) {
        // Instead of clearing the selection, toggle the selection state
        if (isSelectedIndex(index0)) {
            removeSelectionInterval(index0, index0);
        } else {
            addSelectionInterval(index0, index0);
        }
    }
}
