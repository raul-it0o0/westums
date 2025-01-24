package com.westums.controllers;

import com.westums.models.DatabaseConnection;
import com.westums.views.AdminDashboard;
import com.westums.views.LoginPanel;
import com.westums.views.MainFrame;
import com.westums.views.View;

import javax.swing.*;
import java.util.Arrays;

public class MainController {

    private static MainFrame view;
    private static String currentViewName = null;
    private static Object currentView = null;
    private static String currentControllerName = null;
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
        if (currentViewName != null) {
            view.removeView();
            currentView = null;
            System.out.println("Removing current view: " + currentViewName);
        }

        // Remove the current controller
        if (currentControllerName != null) {
            currentController = null;
            System.out.println("Removing current controller: " + currentControllerName);
        }

        // Instantiate the view from MainFrame
        currentView = view.addView(viewName);

        // Instantiate the view's controller
        // and show the card with the given name
        // TODO: A dictionary of view names and their controllers
        if (viewName.equals("Login Panel")) {
            currentController = new LoginPanelController((LoginPanel) currentView);
            view.showView(viewName);
            System.out.println("Showing Login Panel");
        }
        else if (viewName.equals("Admin Dashboard")) {
            currentController = new AdminDashboardController((AdminDashboard) currentView);
            view.showView(viewName);
            System.out.println("Showing Admin Dashboard");
        }

        if (currentView == null) {
            System.out.println("View requested not found: " + viewName);
            return;
        }

        // Keep track of the current view and controller
        currentViewName = viewName;
        currentControllerName = View.getControllerName(viewName);
    }

}
