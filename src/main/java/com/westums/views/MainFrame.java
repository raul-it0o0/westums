/*
 * Created by JFormDesigner on Fri Nov 29 19:56:29 EET 2024
 */

package com.westums.views;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

public class MainFrame extends JFrame {

    public MainFrame() {
        initComponents();
    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        loginPanel = new LoginPanel();
        adminDashboard = new AdminDashboard();
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
            mainPanel.add(loginPanel, "Login Panel");
            mainPanel.add(adminDashboard, "Admin Dashboard");

            // Show the login panel as the first card
            cardLayout.show(mainPanel, "Login Panel");

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
    public LoginPanel loginPanel;
    public AdminDashboard adminDashboard;
    private CardLayout cardLayout;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
