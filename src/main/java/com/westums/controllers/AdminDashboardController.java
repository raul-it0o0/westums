package com.westums.controllers;

import com.westums.controllers.admindashboard.*;
import com.westums.views.AdminDashboard;
import com.westums.views.View;
import com.westums.views.admindashboard.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class AdminDashboardController implements TreeSelectionListener, MouseListener  {

    private static AdminDashboard view;
    ViewStudentsDialogController viewStudentsDialogController;

    // TODO: This field will hold the current subview controller
    //  This is to access the controller's "fields modified" field
    //  To display the "unsaved changes" dialog when user wants to switch views
    //  or log out
    private static Object currentView = null;
    private static Object currentController = null;

    public static void show(String viewName) {

        // Remove the current view from the card layout
        if (currentView != null) {
            view.removeCurrentView();
            currentView = null;
        }

        // Remove the current controller
        if (currentController != null) {
            currentController = null;
        }

        // Instantiate the view from AdminDashboard
        // Exception when the viewName does not match
        // to a view class
        try {
            currentView = view.addView(viewName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Instantiate the view's controller
        // and make the view visible
        try {
            currentController = View.getController(viewName)
                                    .getConstructor(Container.class)
                                    .newInstance(((Container) currentView));
            view.showView(viewName);
        } catch (Exception e) {
            // Exception will never be thrown
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public AdminDashboardController(Container adminDashboardInstance) {
        view = (AdminDashboard) adminDashboardInstance;

        // Register as listener
        view.optionTree.addTreeSelectionListener(this);
        view.logoutPanel.addMouseListener(this);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        // Get the name of the selected node
        String selectedNodeName = e.getPath().getLastPathComponent().toString();

        switch (selectedNodeName) {
            case "Add Student" -> {
                show(View.ADD_STUDENT_CARD);
            }
            case "Add Professor" -> {
                show(View.ADD_PROFESSOR_CARD);
            }
            case "Add Course" -> {
                show(View.ADD_COURSE_CARD);
            }
            case "Add Enrollment" -> {
                show(View.ADD_ENROLLMENT_CARD);
            }
            default -> {
                view.showCard("Default Card");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == view.logoutPanel) {
            // NOTE: The method sets the AdminDashboard view (and controller) to null
            //  and therefore the currently visible card view (and controller) to null
            MainController.show(View.LOGIN_PANEL);
        }
    }

    // Unused methods
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
