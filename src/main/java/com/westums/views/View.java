package com.westums.views;

public class View {

    public static final String MAIN_FRAME = "Main Frame";
    public static final String LOGIN_PANEL = "Login Panel";
    public static final String ADMIN_DASHBOARD = "Admin Dashboard";

    public static String getControllerName(String viewName) {
        if (viewName.equals(LOGIN_PANEL)) {
            return "LoginPanelController";
        }
        else if (viewName.equals(ADMIN_DASHBOARD)) {
            return "AdminDashboardController";
        }
        return null;
    }

}
