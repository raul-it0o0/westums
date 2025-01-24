/*
 * Created by JFormDesigner on Fri Nov 29 19:56:29 EET 2024
 */

package com.westums.views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        initComponents();
    }

    /**
     * Switches the view in the card layout to the one with the given name.
     * @param viewName The name of the view to switch to (e.g. "Admin Dashboard").
     */
    public void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }

    /**
     * Instantiates the appropriate view based on the given view name,
     * adds it to the card layout, and returns the view object.
     * @param viewName The name of the view to add to the card layout (e.g. "Admin Dashboard").
     * @return The view object that was added to the card layout.
     */
    public Object addView(String viewName) {
        if (viewName.equals("Login Panel")) {
            // Instantiate view
            LoginPanel loginPanel = new LoginPanel();

            // Add view to card layout so it can be switched to
            mainPanel.add(loginPanel, "Login Panel");

            // Return the view so the controller can instantiate its controller
            return loginPanel;
        }
        else if (viewName.equals("Admin Dashboard")) {
            // Instantiate view
            AdminDashboard adminDashboard = new AdminDashboard();

            // Add view to card layout so it can be switched to
            mainPanel.add(adminDashboard, "Admin Dashboard");

            // Return the view so the controller can instantiate its controller
            return adminDashboard;
        }

        return null;
    }

    /**
     * Removes the current view from the card layout.
     * Visually, this will remove the view from the screen
     * and only leave the frame visible (with no content).
     */
    public void removeView() {
        // Only one view will be in the card layout at a time
        // Therefore the index of the card to remove is always 0
        mainPanel.remove(0);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        cardLayout = new CardLayout();

        //======== this ========
        setResizable(false);
        setTitle("WestUMS");
        setMinimumSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== mainPanel ========
        {
            mainPanel.setLayout(cardLayout);
        }
        contentPane.add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
    private JPanel mainPanel;
    private CardLayout cardLayout;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
