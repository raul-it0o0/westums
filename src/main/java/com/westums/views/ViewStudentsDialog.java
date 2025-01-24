package com.westums.views;

import com.westums.models.StudentsTableModel;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Raul
 */
public class ViewStudentsDialog extends JDialog {
    public ViewStudentsDialog(Window owner) {
        super(owner, "View Students", ModalityType.MODELESS);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Raul Ariton (raul.ariton05)
    private JScrollPane tableScrollPane;
    public StudentsTableModel studentsTableModel;
    public JTable studentsTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

