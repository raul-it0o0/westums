package com.westums;

import com.westums.controllers.MainController;
import com.westums.views.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Locale;

public class WestUMS {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initUISettings();
                MainController.initialize();
            }
        });
    }

    private static void initUISettings() {
        // These settings must be set before initializing the main frame
        try {
            Locale.setDefault(Locale.ENGLISH);
            // Set scaling and DPI awareness
            System.setProperty("sun.java2d.uiScale", "2");
            System.setProperty("sun.java2d.dpiaware", "true");

            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme");

            InputStream fontStream = WestUMS.class.getResourceAsStream("/fonts/Inter_18pt-Regular.ttf");
            Font Inter_18pt_Regular = Font.createFont(Font.TRUETYPE_FONT,
                    fontStream).deriveFont(12f);
            UIManager.getLookAndFeelDefaults().put("defaultFont", Inter_18pt_Regular);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Inter_18pt_Regular);

            fontStream = WestUMS.class.getResourceAsStream("/fonts/Inter_18pt-Bold.ttf");
            Font Inter_18pt_Bold = Font.createFont(Font.TRUETYPE_FONT,
                    fontStream).deriveFont(12f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Inter_18pt_Bold);

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error initializing UI settings.\n" +
                    "Detailed error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }




}
