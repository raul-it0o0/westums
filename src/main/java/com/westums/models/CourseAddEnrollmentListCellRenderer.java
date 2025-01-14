package com.westums.models;

import com.westums.controllers.Controller;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public class CourseAddEnrollmentListCellRenderer extends JPanel implements ListCellRenderer<Course> {
    private JTextPane courseName;
    private JLabel courseType;

    CourseAddEnrollmentListCellRenderer() {
        //======== this ========
        {
            setLayout(new MigLayout(
                    "fill,insets 5 10 5 10,hidemode 3,align center center",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));
            setBorder(null);

            //======== courseName ========
            courseName = new JTextPane();
            courseName.setEditable(false);
            courseName.setOpaque(false);
            courseName.setFont(new Font("Inter 18pt", Font.PLAIN, 16));
            courseName.setForeground(new Color(0x1e1e1e));
            courseName.setMaximumSize(new Dimension(240, courseName.getPreferredSize().height));

            add(courseName, "cell 0 0,aligny center,growx 0,");

            //======== courseType ========
            courseType = new JLabel();
            courseType.setFont(new Font("Inter 18pt", Font.PLAIN, 14));
            courseType.setForeground(new Color(0x5a5a5a));
            add(courseType, "cell 1 0,align right center,grow 0 0");
        }


    }

    public static CourseAddEnrollmentListCellRenderer getInstance() {
        return new CourseAddEnrollmentListCellRenderer();
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Course> list,
            Course value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        courseName.setText(value.getName());
        courseType.setText(value.getType());

        if (isSelected) {
            setBackground(new Color(0xD9D9D9));
        }
        else {
            setBackground(new Color(0xf5f5f5));
        }

        return this;
    }
}
