package com.westums.controllers;

import com.westums.models.DatabaseConnection;
import com.westums.views.AdminDashboard;
import com.westums.views.LoginPanel;
import com.westums.views.MainFrame;
import com.westums.views.View;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class MainController {

    private static MainFrame view;
    private static Object currentView = null;
    private static Object currentController = null;

    public static void initialize() {
        view = new MainFrame();

        // Test database connection
        try {
            DatabaseConnection db = new DatabaseConnection();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Show the login panel as the first view
        show(View.LOGIN_PANEL);
    }

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

        // Instantiate the view from MainFrame
        // Exception when the viewName does not match
        // to a view class
        try {
            currentView = view.addView(viewName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Instantiate the view's controller
        // make the view visible
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

}
