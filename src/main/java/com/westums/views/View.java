package com.westums.views;

public class View {

    public static final String MAIN_FRAME = "Main Frame";
    public static final String LOGIN_PANEL = "Login Panel";
    public static final String ADMIN_DASHBOARD = "Admin Dashboard";
    public static final String PROFESSOR_DASHBOARD = "Professor Dashboard";

    public static final String ADD_STUDENT_CARD = "Add Student Card";
    public static final String ADD_PROFESSOR_CARD = "Add Professor Card";
    public static final String ADD_COURSE_CARD = "Add Course Card";
    public static final String ADD_ENROLLMENT_CARD = "Add Enrollment Card";
    public static final String IMPORT_DIALOG = "Import Dialog";

    public static final String COURSE_OVERVIEW_CARD = "Course Overview Card";

    public static Class<?> getController(String viewName) {
        switch (viewName) {
            case "Login Panel" -> {
                return com.westums.controllers.LoginPanelController.class;
            }
            case "Admin Dashboard" -> {
                return com.westums.controllers.AdminDashboardController.class;
            }
            case "Professor Dashboard" -> {
                return com.westums.controllers.ProfessorDashboardController.class;
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
            case "Import Dialog" -> {
                return com.westums.controllers.admindashboard.ImportDialogController.class;
            }
            case "Course Overview Card" -> {
                return com.westums.controllers.professordashboard.CourseOverviewController.class;
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
            case "Professor Dashboard" -> {
                return com.westums.views.ProfessorDashboard.class;
            }
            case "Course Overview Card" -> {
                return com.westums.views.professordashboard.CourseOverviewCard.class;
            }
            default -> {
                throw new IllegalArgumentException("View not found: " + viewName);
            }
        }
    }

    /**
     * Used to restore tree selection in the AdminDashboard
     * @param viewClass
     * @return
     */
    public static String getViewDisplayName(Class<?> viewClass) {
        switch (viewClass.getName()) {
            case "com.westums.views.LoginPanel" -> {
                return "Login Panel";
            }
            case "com.westums.views.AdminDashboard" -> {
                return "Admin Dashboard";
            }
            case "com.westums.views.ProfessorDashboard" -> {
                return "Professor Dashboard";
            }
            case "com.westums.views.admindashboard.AddStudentCard" -> {
                return "Add Student";
            }
            case "com.westums.views.admindashboard.AddProfessorCard" -> {
                return "Add Professor";
            }
            case "com.westums.views.admindashboard.AddCourseCard" -> {
                return "Add Course";
            }
            case "com.westums.views.admindashboard.AddEnrollmentCard" -> {
                return "Add Enrollment";
            }
            case "com.westums.views.professordashboard.CourseOverviewCard" -> {
                return "Course Overview";
            }
            default -> {
                throw new IllegalArgumentException("View not found: " + viewClass.getName());
            }
        }
    }
}