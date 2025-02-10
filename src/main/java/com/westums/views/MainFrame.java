package com.westums.views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        initComponents();
    }

    /**
     * Switches the view in the card layout to the one with the given name.
     */
    public void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
        revalidate();
        repaint();
    }

    /**
     * Instantiates the appropriate view based on the given view name,
     * adds it to the card layout, and returns the view object.
     * @param viewName The name of the view to add to the card layout (e.g. "Admin Dashboard").
     * @return The view object that was added to the card layout.
     */
    public Object addView(String viewName) throws Exception {
        Object view;

        try {
            view = View.getViewClass(viewName).getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mainPanel.add((Component) view, viewName);
        return view;
    }

    /**
     * Removes the current view from the card layout.
     * Visually, this will remove the view from the screen
     * and only leave the frame visible (with no content).
     */
    public void removeCurrentView() {
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
    }

    private JPanel mainPanel;
    private CardLayout cardLayout;
}
