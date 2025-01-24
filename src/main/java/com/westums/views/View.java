package com.westums.views;

public class View {

    public static final String MAIN_FRAME = "Main Frame";
    public static final String LOGIN_PANEL = "Login Panel";
    public static final String ADMIN_DASHBOARD = "Admin Dashboard";

    public static final String ADD_STUDENT_CARD = "Add Student Card";
    public static final String ADD_PROFESSOR_CARD = "Add Professor Card";
    public static final String ADD_COURSE_CARD = "Add Course Card";
    public static final String ADD_ENROLLMENT_CARD = "Add Enrollment Card";

    public static Class<?> getController(String viewName) {
        switch (viewName) {
            case "Login Panel" -> {
                return com.westums.controllers.LoginPanelController.class;
            }
            case "Admin Dashboard" -> {
                return com.westums.controllers.AdminDashboardController.class;
            }
            case "Add Student Card" -> {
                return com.westums.controllers.admindashboard.AddStudentController.class;
            }
            case "Add Professor Card" -> {
                return com.westums.controllers.admindashboard.AddProfessorController.class;
            }
            case "Add Course Card" -> {
                return com.westums.controllers.admindashboard.AddCourseController.class;
            }
            case "Add Enrollment Card" -> {
                return com.westums.controllers.admindashboard.AddEnrollmentController.class;
            }
            default -> {
                throw new IllegalArgumentException("View not found: " + viewName);
            }
        }
    }

    public static Class<?> getViewClass(String viewName) {
        switch (viewName) {
            case "Login Panel" -> {
                return com.westums.views.LoginPanel.class;
            }
            case "Admin Dashboard" -> {
                return com.westums.views.AdminDashboard.class;
            }
            case "Add Student Card" -> {
                return com.westums.views.admindashboard.AddStudentCard.class;
            }
            case "Add Professor Card" -> {
                return com.westums.views.admindashboard.AddProfessorCard.class;
            }
            case "Add Course Card" -> {
                return com.westums.views.admindashboard.AddCourseCard.class;
            }
            case "Add Enrollment Card" -> {
                return com.westums.views.admindashboard.AddEnrollmentCard.class;
            }
            default -> {
                throw new IllegalArgumentException("View not found: " + viewName);
            }
        }
    }

}
