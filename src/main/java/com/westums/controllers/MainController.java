package com.westums.controllers;

import com.westums.models.AccountManager;
import com.westums.models.DatabaseConnection;
import com.westums.views.MainFrame;

import javax.swing.*;

public class MainController {

    MainFrame view;
    LoginPanelController loginPanelController;
    AdminDashboardController adminDashboardController;

    private boolean userHadPassword;
    private boolean passwordFetched;
    private String hashedPassword;
    private AccountManager.UserType userType;

    public MainController(MainFrame mainFrameInstance) {
        this.view = mainFrameInstance;

        // Test database connection
        try {
            DatabaseConnection db = new DatabaseConnection();
        }
        catch (Exception e) {
            new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Initialize all other card panel controllers,
        // using the instances created in the view
        // (MainFrame, the parent of the card layout)
        this.loginPanelController = new LoginPanelController(view.loginPanel, view::showCard);
        this.adminDashboardController = new AdminDashboardController(view.adminDashboard, view::showCard);
    }

}
