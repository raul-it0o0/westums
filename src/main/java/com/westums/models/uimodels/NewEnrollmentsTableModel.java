package com.westums.models.uimodels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class NewEnrollmentsTableModel extends AbstractTableModel {

    public class Enrollment {
        private String courseName;
        private String courseType;
        private String studentEmail;

        public Enrollment(String courseName, String courseType, String studentEmail) {
            this.courseName = courseName;
            this.courseType = courseType;
            this.studentEmail = studentEmail;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getCourseType() {
            return courseType;
        }

        public String getStudentEmail() {
            return studentEmail;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof Enrollment)) return false;
            Enrollment enrollment = (Enrollment) obj;
            return courseName.equals(enrollment.courseName) &&
                    courseType.equals(enrollment.courseType) &&
                    studentEmail.equals(enrollment.studentEmail);
        }
    }

    private String[] columnNames = {"Course Name", "Course Type", "Student Email"};
    private ArrayList<Enrollment> data;

    public NewEnrollmentsTableModel() {
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

    public ArrayList<Enrollment> getData() {
        return data;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Enrollment enrollment = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> enrollment.getCourseName();
            case 1 -> enrollment.getCourseType();
            case 2 -> enrollment.getStudentEmail();
            default -> null;
        };
    }

    public void add(String courseName, String courseType, String studentEmail) {
        data.add(new Enrollment(courseName, courseType, studentEmail));
    }
}
