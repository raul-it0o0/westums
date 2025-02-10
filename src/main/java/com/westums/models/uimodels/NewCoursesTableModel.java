package com.westums.models.uimodels;

import com.westums.models.Course;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NewCoursesTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Course Name", "Course Type", "Professor Email"};
    private ArrayList<Course> data;

    public NewCoursesTableModel() {
        super();
        this.data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public ArrayList<Course> getData() {
        return data;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Course course = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> course.getName();
            case 1 -> course.getType();
            case 2 -> course.getProfessorEmail();
            default -> null;
        };
    }

    public void add(Course course) {
        data.add(course);
    }
}
