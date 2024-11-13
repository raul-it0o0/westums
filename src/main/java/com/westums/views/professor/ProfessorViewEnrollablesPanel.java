package com.westums.views.professor;

import com.westums.models.*;
import com.westums.uimodels.CustomButton;
import com.westums.utils.ModelUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProfessorViewEnrollablesPanel extends JPanel implements ModelUpdateListener {

    Professor loggedProfessor;
    ActionListener backToDashboard, back;
    JPanel pnlEnrollableButtons;
    CardLayout cardLayout;

    public ProfessorViewEnrollablesPanel(Professor loggedProfessor, ActionListener backToDashboard) {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        // Add cardLayout panels
        JPanel pnlEnrollables = new JPanel();
        this.add(pnlEnrollables, "View Enrollables");
        cardLayout.show(this, "View Enrollables");

        // Create ActionListener to return to previous (this) screen
        back = (e -> cardLayout.show(this, "View Enrollables"));

        super.setOpaque(false);
        this.loggedProfessor = loggedProfessor;
        this.backToDashboard = backToDashboard;

        JLabel lbl1 = new JLabel("Click on a course to take actions:");
        lbl1.setForeground(Color.red);
        lbl1.setFont(new Font("Arial", Font.ITALIC, 20));
        pnlEnrollables.add(lbl1);

        pnlEnrollableButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlEnrollableButtons.setPreferredSize(new Dimension(800,200));
        pnlEnrollables.add(pnlEnrollableButtons);
        showEnrollableButtons();


        // 1. panel on top of panel
        // 2. this.cardlayout
        CustomButton btnBack = new CustomButton("<-");
        btnBack.addActionListener(backToDashboard);
        pnlEnrollables.add(btnBack);
    }

    void showEnrollableButtons() {
        /*
            This method adds the buttons into view, using the current model
            (loggedProfessor.taughtCourse) state.
         */

        for (Enrollable course : loggedProfessor.taughtCourses) {
            CustomButton btn = new CustomButton(course.getName());
            btn.addActionListener(e -> {
                ProfessorViewEnrollableOptionsPanel pnlViewEnrollable = new ProfessorViewEnrollableOptionsPanel(course,
                        back);
                this.add(pnlViewEnrollable, "View Enrollable");
                cardLayout.show(this, "View Enrollable");
            });
            pnlEnrollableButtons.add(btn);
        }
    }

    @Override
    public void modelUpdated() {
        /*
            This method gets called when any changes happen
            to a model's state.
            In this case, the user (professor) may choose
            to add a new enrollable during their session.
            This means the model for the professor changes
            (the professor teaches a new course)
            These changes need to be reflected in the view (the GUI)
            therefore, with this method,
            we need to force the view to be updated to reflect the
            model's changes.
         */

        // We know what view is dynamic (i.e. needs to be changed
        //  with model state changes), and that is the pnlEnrollableButtons.
        // 1. Remove all outdated view data
        pnlEnrollableButtons.removeAll();
        this.revalidate();
        this.repaint();
        // 2. Add the components to the view, which will now contain the new data.
        showEnrollableButtons();
    }
}
