package com.westums.controllers;

import com.westums.models.Course;
import com.westums.models.Professor;
import com.westums.views.MainFrame;
import com.westums.views.ProfessorDashboard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.westums.views.View;

public class ProfessorDashboardController implements TreeSelectionListener, MouseListener {

    private static ProfessorDashboard view;
    private Professor professor;

    private static Object currentView = null;
    private static Object currentController = null;

    public static void show(String viewName, Course course) {

        // Remove the current view from the card layout
        if (currentView != null) {
            view.removeCurrentView();
            currentView = null;
        }

        // Remove the current controller
        if (currentController != null) {
            currentController = null;
        }

        // Instantiate the view from ProfessorDashboard
        // Exception when the viewName does not match
        // to a view class
        try {
            currentView = view.addView(viewName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Instatiate the view's controller and make the view visible
        try {
            currentController = View.getController(viewName)
                                    .getConstructor(Container.class, Course.class)
                                    .newInstance((Container) currentView, course);
            view.showView(viewName);
        } catch (Exception e) {
            // Exception will never be thrown
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ProfessorDashboardController(Container professorDashboardInstance, Object professor) {
        view = (ProfessorDashboard) professorDashboardInstance;
        this.professor = (Professor) professor;

        // Register as listener
        view.optionTree.addTreeSelectionListener(this);
        view.logoutPanel.addMouseListener(this);

        // Load data in the view
        // Load professor name and surname
        view.menuHeader.setText(String.format("%s %s", ((Professor)professor).getName(), ((Professor) professor).getSurname()));

        // Professor Courses into the tree
        try {
            for (String courseName : ((Professor) professor).getCourseNames()) {
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) view.optionTree.getModel().getRoot();
                DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(courseName);
                root.add(courseNode);
                courseNode.add(new DefaultMutableTreeNode("Overview"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    String.format("An error occurred while loading the courses: %s", e.getMessage()),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Reload model
        DefaultTreeModel model = (DefaultTreeModel) view.optionTree.getModel();
        model.reload();
    }

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        // Get the name of the selected node
        String selectedNodeName = event.getPath().getLastPathComponent().toString();

        switch (selectedNodeName) {
            case "Overview":
                // Get parent name (the name of the course)
                String courseName = event.getPath().getPathComponent(1).toString();
                show(View.COURSE_OVERVIEW_CARD, Course.builder(courseName, professor.getEmail()).build());
                break;
            default:
                view.showView("Default Card");
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == view.logoutPanel) {
            // Set the current view and controller to null
            // to force re-instantiation when the user logs back in
            currentView = null;
            currentController = null;
            MainController.show(View.LOGIN_PANEL, null);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
