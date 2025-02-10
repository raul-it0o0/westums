package com.westums.models.uimodels;

import com.westums.models.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NewStudentsTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Surname", "Generated Email"};
    private ArrayList<Student> data;

    public NewStudentsTableModel() {
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

    public ArrayList<Student> getData() {
        return data;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> student.getName();
            case 1 -> student.getSurname();
            case 2 -> student.getEmail();
            default -> null;
        };
    }

    public void add(Student student) {
        data.add(student);
    }
}
