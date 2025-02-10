package com.westums.controllers.professordashboard;

import com.westums.models.Course;
import com.westums.views.professordashboard.CourseOverviewCard;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CourseOverviewController {

    CourseOverviewCard view;
    Course course;

    public CourseOverviewController(Container courseOverviewCardInstance, Course course) {

        this.view = (CourseOverviewCard) courseOverviewCardInstance;
        this.course = course;

        // Load course data
        try {
            // Number of enrolled students
            view.enrolledStudentsStat.setText(String.valueOf(course.getEnrolledStudents()));

            // Average attendance
            view.attendancePercentageStat.setText(String.format("%.2f%%", course.getAverageAttendance()));

            // Average grade from all evaluations
            view.averageGradeStat.setText(String.format("%.2f", course.getAverageGrade()));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
