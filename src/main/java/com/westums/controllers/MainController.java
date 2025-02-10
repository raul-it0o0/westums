package com.westums.controllers;

import com.westums.models.utils.DatabaseConnection;
import com.westums.views.MainFrame;
import com.westums.views.View;

import javax.swing.*;
import java.awt.*;

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
        show(View.LOGIN_PANEL, null);
    }

    public static void show(String viewName, Object object) {

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
            return;
        }

        // Instantiate the view's controller
        // make the view visible
        try {
            if (object != null) {
                currentController = View.getController(viewName)
                        .getConstructor(Container.class, Object.class)
                        .newInstance(((Container) currentView), object);
            } else {
                currentController = View.getController(viewName)
                        .getConstructor(Container.class)
                        .newInstance(((Container) currentView));
            }
            view.showView(viewName);
        } catch (Exception e) {
            // Exception will never be thrown
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
