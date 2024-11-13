package com.westums.models;

import com.westums.utils.ModelUpdateListener;

import java.util.ArrayList;

public class Professor {

    public String surname, name, password;
    public java.sql.Date dob;
    public ArrayList<Enrollable> taughtCourses;
    public ModelUpdateListener modelUpdateListener;

    public Professor(String surname, String name, java.sql.Date dob, String password) {
        this.surname = surname;
        this.name = name;
        this.dob = dob;
        this.password = password;
        taughtCourses = new ArrayList<>();
    }

    public void addTaughtCourse(Enrollable enrollable) {
        this.taughtCourses.add(enrollable);
        // NOTE: The model notifies the listener!
        if (modelUpdateListener != null)
            modelUpdateListener.modelUpdated();
    }

    public void addModelUpdateListener(ModelUpdateListener modelUpdateListener) {
        /*
            If a model has a ModelUpdateListener, that means that changes to its state (data members)
            require corresponding GUI (View) changes.
         */
        this.modelUpdateListener = modelUpdateListener;
    }



}
