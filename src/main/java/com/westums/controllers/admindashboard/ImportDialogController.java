package com.westums.controllers.admindashboard;

import com.westums.controllers.AdminDashboardController;
import com.westums.models.Admin;
import com.westums.models.utils.JSONParser;
import com.westums.views.admindashboard.ImportDialog;
import com.westums.views.customui.JFileStatusPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImportDialogController implements ActionListener {

    ImportDialog view;
    File jsonFile;
    JSONParser jsonParser;
    SwingWorker<Void, Void> parseThread;

    boolean parsingFailed = false;

    public ImportDialogController(ImportDialog importDialogInstance) {
        this.view = importDialogInstance;

        // Register as listener
        view.openFileButton.addActionListener(this);
        view.cancelButton.addActionListener(this);
        view.submitButton.addActionListener(this);

        view.instructionsTextPane.setText("<html>\n" +
                "    <h1> How to structure the JSON file </h1>\n" +
                "    <ul>\n" +
                "        <li>\n" +
                "            If adding both new professors and students, write professors first, then students.\n" +
                "            <pre \n" +
                "            style=\"background-color: #f0f0f0;\n" +
                "            font-weight: bolder;\n" +
                "            font-family: 'JetBrains Mono', 'Courier New', Courier, monospace;\">\n" +
                "    \"professors\": [\n" +
                "        {\n" +
                "            \"name\": \"Name1\",\n" +
                "            \"surname\": \"Surname1\",\n" +
                "            \"dateofbirth\": \"01/02/2023\" // should match specified dateformat\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": // ... ,\n" +
                "            \"email\": // ... ,\n" +
                "            \"dateofbirth\": // ...\n" +
                "        },\n" +
                "        // ...\n" +
                "    ],\n" +
                "\n" +
                "    \"students\": [\n" +
                "        {\n" +
                "            \"name\": \"Name1\",\n" +
                "            \"surname\": \"Surname1\",\n" +
                "            \"dateofbirth\": \"01/02/2023\",\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": // ... ,\n" +
                "            \"surname\": // ... ,\n" +
                "            \"dateofbirth\": // ... ,\n" +
                "        },\n" +
                "        // ...\n" +
                "    ]\n" +
                "\n" +
                "    // other fields, if any (courses, enrollments)\n" +
                "</pre>\n" +
                "        </li> \n" +
                "        <li>\n" +
                "            If declaring students and/or professors, you must declare a date format, for example:\n" +
                "            <pre \n" +
                "            style=\"background-color: #f0f0f0;\n" +
                "            font-weight: bolder;\n" +
                "            font-family: 'JetBrains Mono', 'Courier New', Courier, monospace;\">\n" +
                "\"dateformat\": \"dd/MM/yyyy\"\n" +
                "            </pre>\n" +
                "            More format/patterne examples can be found <a href=\"https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html\">here</a>.\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            Any parsing error due to an error in the syntax of the JSON file will cancel the parsing process and display an error. You can fix the syntax error and re-open the file again.\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            Enrollments can be declared either in a new course or in the enrollments field\n" +
                "        </li>\n" +
                "        <li>\n" +
                "            Ensure any <code>professoremail</code> fields in <code>courses</code> refer to existing professors already in the system, or to professors declared in the <code>professors</code> field.\n" +
                "            <pre \n" +
                "            style=\"background-color: #f0f0f0;\n" +
                "            font-weight: bolder;\n" +
                "            font-family: 'JetBrains Mono', 'Courier New', Courier, monospace;\">\n" +
                "\"professors\": [\n" +
                "{\n" +
                "    \"name\": \"John\"\n" +
                "    \"surname\": \"Doe\"\n" +
                "    \"dateofbirth\": ...\n" +
                "]\n" +
                "\n" +
                "\n" +
                "\"courses\": [\n" +
                "{\n" +
                "    \"name\": \"Course 1\",\n" +
                "    \"type\": \"Lecture\",\n" +
                "    \"professoremail\": \"john.doe@e-uvt.ro\",\n" +
                "    \"students\":\n" +
                "        [\n" +
                "            // ... (if any)\n" +
                "        ]\n" +
                "\n" +
                "},\n" +
                "  // ...\n" +
                "            </pre>\n" +
                "        </li>\n" +
                "    </ul>\n" +
                "</html>"
        );
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == view.openFileButton) {
            FileDialog fileDialog = new FileDialog(view, "Select a file", FileDialog.LOAD);
            fileDialog.setFilenameFilter((dir, name) -> name.endsWith(".json"));
            fileDialog.setLocationRelativeTo(null);
            fileDialog.setVisible(true);

            if (fileDialog.getFile() != null) {
                if (fileDialog.getFile().endsWith(".json")) {
                    view.selectedFileTextPanel.setText(fileDialog.getFile());
                    // Enable submit button
                    view.submitButton.setEnabled(true);
                    // Change the state of the panel to valid
                    view.selectedFileTextPanel.setState(JFileStatusPanel.STATE_VALID_FILE_SELECTED);
                    jsonFile = new File(fileDialog.getDirectory(), fileDialog.getFile());
                } else {
                    // Disable submit button
                    view.submitButton.setEnabled(false);
                    JOptionPane.showMessageDialog(view, "Please select a JSON file!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if (event.getSource() == view.cancelButton) {
            // If parse thread has been created and is running, cancel it
            if ((parseThread != null) && (!parseThread.isDone())) {
                parseThread.cancel(true);
            }
            view.dispose();
            // Clears tree selection and shows the default card
            AdminDashboardController.show(null);
        }

        else if (event.getSource() == view.submitButton) {
            this.jsonParser = new JSONParser();
            this.parseThread = createParsingThread();

            // Just before starting parse, change the state of the panel to processing
            view.selectedFileTextPanel.setState(JFileStatusPanel.STATE_PROCESSING);
            // Disable submit button
            view.submitButton.setEnabled(false);

            // Parse the JSON file in a separate thread
            parseThread.execute();

        }
    }

    private SwingWorker<Void, Void> createParsingThread() {
        return new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    jsonParser.parseJSON(jsonFile);
                } catch (Exception e) {
                    // Any exception that occurs during parsing displays an error message
                    view.selectedFileTextPanel.setState(JFileStatusPanel.STATE_REJECTED);
                    JOptionPane.showMessageDialog(view, e.getMessage(), "Error while parsing JSON",
                            JOptionPane.ERROR_MESSAGE);
                    // Set the flag to indicate parsing failed
                    parsingFailed = true;
                    // Return from the thread
                    return null;
                }
                return null;
            }

            @Override
            protected void done() {
                // This method is called when the thread completes execution
                // i.e. when doInBackground() returns
                // If parsing failed, return, since error message is already displayed
                if (parsingFailed) return;

                // Change the state of the panel to indicate successful parsing
                view.selectedFileTextPanel.setState(JFileStatusPanel.STATE_ACCEPTED);

                // Display a confirmation dialog to import the data
                int option = JOptionPane.showConfirmDialog(view, "JSON file parsed successfully. Do you want to import the data?",
                        "JSON file parsed", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                // If user chooses to import the data
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        Admin.importParsedData(jsonParser);
                        JOptionPane.showMessageDialog(view, "Data imported successfully!", "Data imported",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        // Any exception that occurs during DB import displays an error message
                        JOptionPane.showMessageDialog(view, e.getMessage(), "Error while importing data",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Also include the case when user chooses not to import the data
                view.dispose();
                AdminDashboardController.show(null);
            }
        };
    }
}
