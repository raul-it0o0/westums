package com.westums.controllers.admindashboard;

import com.westums.models.Student;
import com.westums.views.admindashboard.ViewStudentsDialog;

public class ViewStudentsDialogController {

    private ViewStudentsDialog view;

    public ViewStudentsDialogController(ViewStudentsDialog view) {
        this.view = view;
        view.setVisible(true);
    }

    private void loadData() {

    }

}
