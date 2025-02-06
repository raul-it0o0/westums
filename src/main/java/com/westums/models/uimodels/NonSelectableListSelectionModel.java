package com.westums.models.uimodels;

import javax.swing.*;

public class NonSelectableListSelectionModel extends DefaultListSelectionModel {
    @Override
    public void setSelectionInterval(int index0, int index1) {
        return;
        // Disable selection
    }

    @Override
    public void addSelectionInterval(int index0, int index1) {
        return;
        // Disable selection
    }

    @Override
    public void removeSelectionInterval(int index0, int index1) {
        return;
        // Disable selection
    }
}
