package com.westums.controllers;

import com.westums.controllers.admindashboard.*;
import com.westums.views.AdminDashboard;
import com.westums.views.View;
import com.westums.views.admindashboard.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class AdminDashboardController implements TreeSelectionListener, MouseListener  {

    private static AdminDashboard view;
    ViewStudentsDialogController viewStudentsDialogController;
    boolean ignoreTreeSelection = false;

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

    /**
     * Handle unsaved changes when switching panels
     * or logging out
     * @return 0 if view can be switched, 1 if view cannot be switched
     */
    private int handleUnsavedChanges() {
        // Create new JOptionPane
        String message = "You have unsubmitted modifications. Do you want to submit them?";
        String title = "Unsaved Changes";
        String[] options = {"Yes", "No", "Cancel"};

        int option = JOptionPane.showOptionDialog(view, message, title,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[2]);

        switch (option) {
            case JOptionPane.YES_OPTION -> {
                // User wants to submit changes, and if successful, switch views
                try {
                    option = (int)currentController.getClass()
                            .getMethod("submitChanges")
                            .invoke(currentController);
                    if (option == 1) {
                        // A database error occured while trying to submit changes
                        // Do not switch views
                        return 1;
                    }

                    // Otherwise, display submitted changes dialog
                    JOptionPane.showMessageDialog(view, "Changes submitted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Switch views (by returning with 0)
                    return 0;

                } catch (Exception e) {
                    // Exception will never be thrown
                    return 1;
                }
            }
            case JOptionPane.NO_OPTION -> {
                // User does not want to submit changes, and simply just switch views
                return 0;
            }
            case JOptionPane.CANCEL_OPTION -> {
                // User wants to cancel the view switch
                return 1;
            }
            default -> {
                // User closes the dialog
                return 1;
            }
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        if (ignoreTreeSelection) {
            ignoreTreeSelection = false;
            return;
        }

        // Check for modified fields in the current view
        if (currentController != null) {
            try {
                // Check if the fields are modified and valid
                // NOTE: If fields are modified (filled) but not valid, changes are lost
                if ((boolean) currentController.getClass().getMethod("hasValidFields").invoke(currentController)) {
                    // Show unsaved changes dialog
                    if (handleUnsavedChanges() == 1) {
                        // User wants to cancel the view switch
                        // Restore the tree selection, ensuring the method that
                        // got triggered by the tree selection event does not run
                        ignoreTreeSelection = true;
                        view.optionTree.setSelectionPath(event.getOldLeadSelectionPath());
                        return;
                    }
                    // Otherwise, continue in the method, and switch views
                }
            } catch (Exception e) {
                // Exception will never be thrown
                JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Get the name of the selected node
        String selectedNodeName = event.getPath().getLastPathComponent().toString();

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

            // Check for modified fields in the current view
            if (currentController != null) {
                try {
                    // Check if the fields in the current view are modified and valid
                    // NOTE: If fields are modified (filled) but not valid, changes are lost
                    if ((boolean) currentController.getClass().getMethod("hasValidFields").invoke(currentController)) {
                        // Show unsaved changes dialog
                        if (handleUnsavedChanges() == 1) {
                            // User wants to cancel the view switch
                            // Return, do not switch views
                            return;
                        }
                        // Otherwise, continue in the method, and switch views
                    }
                } catch (Exception e) {
                    // Exception will never be thrown
                    JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Set the current view and controller to null
            // to force re-instantiation when the user logs back in
            currentView = null;
            currentController = null;
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
