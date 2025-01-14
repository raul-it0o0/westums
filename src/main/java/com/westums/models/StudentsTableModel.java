package com.westums.models;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.ArrayList;

public class StudentsTableModel extends AbstractTableModel {

    private String[] columnNames = {"Email", "Name", "Surname", "Courses Enrolled"};
    private ArrayList<Student> data;

    DefaultTableModel dtm = new DefaultTableModel();

    public StudentsTableModel() {
        super();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> student.getEmail();
            case 1 -> student.getName();
            case 2 -> student.getSurname();
            case 3 -> student.getNumberOfCoursesEnrolled();
            default -> null;
        };
    }
}
