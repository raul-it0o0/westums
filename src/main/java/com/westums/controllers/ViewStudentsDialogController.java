package com.westums.controllers;

import com.westums.models.Student;
import com.westums.views.ViewStudentsDialog;

import javax.swing.*;

public class ViewStudentsDialogController {

    private ViewStudentsDialog view;

    public ViewStudentsDialogController(ViewStudentsDialog view) {
        this.view = view;
        view.setVisible(true);
    }

    private void loadData() {
        Student s = Student.builder("a","b","c")
                .build();

    }

}
