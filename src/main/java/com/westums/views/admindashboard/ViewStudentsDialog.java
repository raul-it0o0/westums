package com.westums.views.admindashboard;

import com.westums.models.uimodels.StudentsTableModel;

import java.awt.*;
import javax.swing.*;

public class ViewStudentsDialog extends JDialog {
    public ViewStudentsDialog(Window owner) {
        super(owner, "View Students", ModalityType.MODELESS);
        initComponents();
    }

    private void initComponents() {
        tableScrollPane = new JScrollPane();
        studentsTableModel = new StudentsTableModel();
        studentsTable = new JTable();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tableScrollPane ========
        {
            //---- studentsTable ----
            studentsTable.setFont(new Font("Inter 18pt", Font.PLAIN, 12));
            studentsTable.setForeground(Color.white);
            studentsTable.setFillsViewportHeight(true);
            studentsTable.setModel(studentsTableModel);
//            studentsTable.setDefaultRenderer(Object.class, new StudentsTableCellRenderer());
//            studentsTable.getTableHeader().setDefaultRenderer(new StudentsTableCellRenderer(studentsTable));
            tableScrollPane.setViewportView(studentsTable);
        }
        contentPane.add(tableScrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JScrollPane tableScrollPane;
    public StudentsTableModel studentsTableModel;
    public JTable studentsTable;
}

