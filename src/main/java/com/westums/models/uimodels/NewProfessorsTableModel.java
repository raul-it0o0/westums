package com.westums.models.uimodels;

import com.westums.models.Professor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NewProfessorsTableModel extends AbstractTableModel {

    private String[] columnNames = {"Name", "Surname", "Generated Email"};
    private ArrayList<Professor> data;

    public NewProfessorsTableModel() {
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

    public ArrayList<Professor> getData() {
        return data;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Professor professor = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> professor.getName();
            case 1 -> professor.getSurname();
            case 2 -> professor.getEmail();
            default -> null;
        };
    }

    public void add(Professor professor) {
        data.add(professor);
    }
}
