package com.westums.controllers;

import com.westums.views.AdminDashboard;

import javax.swing.event.MouseInputListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.function.Consumer;

public class AdminDashboardController implements TreeSelectionListener, MouseListener {

    AdminDashboard view;
    Consumer<String> showCardMethod;


    AdminDashboardController(AdminDashboard adminDashboardInstance, Consumer<String> switchCard) {
        this.view = adminDashboardInstance;
        this.showCardMethod = switchCard;

        view.optionTree.addTreeSelectionListener(this);
        view.logoutPanel.addMouseListener(this);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        /*
        1. Check previously selected node (e.getOldLeadSelectionPath()) or by having two string
        variables oldNode, newNode, updated accordingly in this valueChanged method.
        2. If previously selected node is one of the three nodes, invoke a method (defined in the
        controller) to check if there is any unsaved data in each node card.
        3. If there is any unsaved data, display a JOptionDialog to get confirmation from user.
        (Yes, proceed changing and lose unsaved data
        No, stay on this node and keep editing
        Cancel, stay on this node and keep editing)
         */

        String selectedNodeName = e.getPath().getLastPathComponent().toString();
        if (selectedNodeName.equals("Add Student")) {
            view.showCard("Add Student Card");
            return;
        }
        else if (selectedNodeName.equals("Add Professor")) {
            view.showCard("Add Professor Card");
            return;
        }
        else if (selectedNodeName.equals("Add Course")) {
            view.showCard("Add Course Card");
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.logoutPanel) {
            showCardMethod.accept("Login Panel");
            return;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
}
