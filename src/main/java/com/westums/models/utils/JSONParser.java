package com.westums.models.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westums.models.Admin;
import com.westums.models.Course;
import com.westums.models.Professor;
import com.westums.models.Student;
import com.westums.models.uimodels.NewCoursesTableModel;
import com.westums.models.uimodels.NewEnrollmentsTableModel;
import com.westums.models.uimodels.NewProfessorsTableModel;
import com.westums.models.uimodels.NewStudentsTableModel;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS;

public class JSONParser {

    private NewStudentsTableModel newStudentsTableModel;
    private NewProfessorsTableModel newProfessorsTableModel;
    private NewCoursesTableModel newCoursesTableModel;
    private NewEnrollmentsTableModel newEnrollmentsTableModel;

    public JSONParser() {
        this.newStudentsTableModel = new NewStudentsTableModel();
        this.newProfessorsTableModel = new NewProfessorsTableModel();
        this.newCoursesTableModel = new NewCoursesTableModel();
        this.newEnrollmentsTableModel = new NewEnrollmentsTableModel();
    }

    public void parseJSON(File jsonFile) throws IOException, ParseException, SQLException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(ALLOW_COMMENTS, true);

        // NOTE: Throws IOException if file is not found
        JsonNode root = mapper.readTree(jsonFile);

        // Attempt to get the date format
        // used to parse the date of birth from
        // String to Date
        // NOTE: Throws IllegalArgumentException if pattern is invalid
        SimpleDateFormat sdf = new SimpleDateFormat(root.get("dateformat").asText());

        // Process professors
        JsonNode currentNode = root.get("professors");
        if ((currentNode != null) && (currentNode.isArray())) {
            for (JsonNode professor : currentNode) {
                // Validate name and surname fields
                if ((professor.get("name") == null) || (professor.get("name").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Name field in professors null or empty in JSON file");
                }
                if ((professor.get("surname") == null) || (professor.get("surname").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Surname field in professors null or empty in JSON file");
                }

                String name = professor.get("name").asText().trim();
                String surname = professor.get("surname").asText().trim();

                if (!InputVerifier.isValidName(name))
                    throw new IllegalArgumentException("Invalid professor name \"" + name + "\" in JSON file");
                if (!InputVerifier.isValidName(surname))
                    throw new IllegalArgumentException("Invalid professor surname \"" + surname + "\" in JSON file");

                // Check if dateofbirth field is present
                if ((professor.get("dateofbirth") == null) || (professor.get("dateofbirth").asText().trim().isEmpty()))
                    throw new IllegalArgumentException("Date of birth field in professors null or empty in JSON file");

                // Validate date of birth field (parse it)
                Date dateOfBirth;
                try {
                    dateOfBirth = sdf.parse(professor.get("dateofbirth").asText().trim());
                } catch (ParseException e) {
                    throw new ParseException(
                            String.format(
                                    "Failed to parse professor date of birth \"%s\" in JSON file " +
                                            "(parsed pattern \"%s\")",
                                    professor.get("dateofbirth").asText(),
                                    sdf.toPattern()),
                            e.getErrorOffset());
                }

                // Add new professor to the list
                newProfessorsTableModel.add(Professor.builder(name, surname, dateOfBirth).build());
            }
        }

        // Process students
        currentNode = root.get("students");
        if ((currentNode != null) && (currentNode.isArray())) {
            for (JsonNode student : currentNode) {
                // Check if name and surname fields are present
                if ((student.get("name") == null) || (student.get("name").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Name field in students null or empty in JSON file");
                }
                if ((student.get("surname") == null) || (student.get("surname").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Surname field in students null or empty in JSON file");
                }

                // Validate name and surname fields
                String name = student.get("name").asText().trim();
                String surname = student.get("surname").asText().trim();

                if (!InputVerifier.isValidName(name))
                    throw new IllegalArgumentException("Invalid student name \"" + name + "\" in JSON file");
                if (!InputVerifier.isValidName(surname))
                    throw new IllegalArgumentException("Invalid student surname \"" + surname + "\" in JSON" +
                            " file");

                // Check if dateofbirth field is present
                if ((student.get("dateofbirth") == null) || (student.get("dateofbirth").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Date of birth field in students null or empty in JSON file");
                }

                // Validate date of birth field (parse it)
                Date dateOfBirth;
                try {
                    dateOfBirth = sdf.parse(student.get("dateofbirth").asText().trim());
                } catch (ParseException e) {
                    throw new ParseException(
                            String.format(
                                    "Failed to parse student date of birth \"%s\" in JSON file " +
                                            "(parsed pattern \"%s\")",
                                    student.get("dateofbirth").asText(),
                                    sdf.toPattern()),
                            e.getErrorOffset());
                }

                // Add new student to the list
                newStudentsTableModel.add(Student.builder(name, surname, dateOfBirth).build());

            }
        }

        // Process courses
        currentNode = root.get("courses");
        if ((currentNode != null) && (currentNode.isArray())) {
            for (JsonNode course : currentNode) {
                // Check if name field is present
                if ((course.get("name") == null) || (course.get("name").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Name field in courses null or empty in JSON file");
                }
                // Validate name field
                String name = course.get("name").asText().trim();

                if (!InputVerifier.isValidCourseName(course.get("name").asText().trim())) {
                    throw new IllegalArgumentException("Invalid course name \"" + course.get("name").asText() + "\" in JSON file");
                }

                // Check if type field is present
                if ((course.get("type") == null) || (course.get("type").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Type field in courses null or empty in JSON file");
                }

                // Validate type field
                String type = course.get("type").asText().trim();

                if (!InputVerifier.isValidCourseType(type)) {
                    throw new IllegalArgumentException("Invalid course type \"" + type + "\" in JSON file");
                }

                // Check if course of same name and type already exists in database
                // or has been parsed already
                if (Admin.existsCourse(name, type) || existsParsedCourse(name, type)) {
                    throw new IllegalArgumentException("Course with name \"" + name + "\" and type \"" + type + "\" already exists in the database or already parsed");
                }

                // Check if professor field is present
                if ((course.get("professoremail") == null) || (course.get("professoremail").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Professor field in courses null or empty in JSON file");
                }

                String professorEmail = course.get("professoremail").asText().trim();
                // Validate professor email
                if (!Admin.existsProfessorWithEmail(professorEmail) && !existsParsedProfessorWithEmail(professorEmail)) {
                    throw new IllegalArgumentException("Professor with email \"" + professorEmail + "\" does not exist in the database or has not been parsed so far");
                }

                // Check for optional field students
                if ((course.get("students") != null) && (course.get("students").isArray())) {
                    for (JsonNode emailEntry : course.get("students")) {
                        // Validate student email
                        String email = emailEntry.asText().trim();

                        if (Admin.getStudentIDWithEmail(email) == 0
                                && !existsParsedStudentWithEmail(email)) {
                            throw new IllegalArgumentException("Student with email \"" + email + "\" does not exist in the database or has not been parsed so far");
                        }

                        newEnrollmentsTableModel.add(name, type, email);

                    }
                }

                // Add new course to the list
                newCoursesTableModel.add(Course.builder(name, type, professorEmail).build());
            }
        }

        // Process enrollments
        currentNode = root.get("enrollments");
        if ((currentNode != null) && (currentNode.isArray())) {
            for (JsonNode enrollment : currentNode) {
                // Check if coursename field is present
                if ((enrollment.get("coursename") == null) || (enrollment.get("coursename").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Course name field in enrollments null or empty in JSON file");
                }

                // Check if coursetype field is present
                if ((enrollment.get("coursetype") == null) || (enrollment.get("coursetype").asText().trim().isEmpty())) {
                    throw new IllegalArgumentException("Course type field in enrollments null or empty in JSON file");
                }

                // Validate type field
                String type = enrollment.get("coursetype").asText().trim();
                if (!InputVerifier.isValidCourseType(type)) {
                    throw new IllegalArgumentException("Invalid course type \"" + type + "\" in enrollments in JSON file");
                }

                String name = enrollment.get("coursename").asText().trim();

                // Ensure a course with the given name and type exists (or has been parsed so far)
                if (!Admin.existsCourse(name, type) && !existsParsedCourse(name, type)) {
                    throw new IllegalArgumentException("Course with name \"" + name + "\" and type \"" + type + "\" must exist in the database or have been parsed" +
                            " to add enrollments");
                }

                // Iterate over student emails
                if ((enrollment.get("students") == null) || (!enrollment.get("students").isArray())) {
                    throw new IllegalArgumentException("Students field in enrollments null or empty in JSON file");
                }

                for (JsonNode emailEntry : enrollment.get("students")) {

                    String email = emailEntry.asText().trim();

                    // Validate student email
                    if (Admin.getStudentIDWithEmail(email) == 0
                            && !existsParsedStudentWithEmail(email)) {
                        throw new IllegalArgumentException("Student with email \"" + email + "\" does not exist in the database or has not been parsed so far");
                    }

                    // Check if enrollment entry already exists
                    if (existsEnrollment(name, type, email)) {
                        // Simply skip the entry, don't throw an exception
                        continue;
                    }

                    newEnrollmentsTableModel.add(name, type, email);
                }

            }
        }

        // Remove duplicate enrollments
        removeDuplicateEnrollments();

    }

    public NewStudentsTableModel getStudentsTable() {
        return newStudentsTableModel;
    }

    public NewProfessorsTableModel getProfessorsTable() {
        return newProfessorsTableModel;
    }

    public NewCoursesTableModel getCoursesTable() {
        return newCoursesTableModel;
    }

    public NewEnrollmentsTableModel getEnrollmentsTable() {
        return newEnrollmentsTableModel;
    }

    private boolean existsParsedStudentWithEmail(String email) {
        for (Student student : newStudentsTableModel.getData()) {
            if (student.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean existsParsedProfessorWithEmail(String email) {
        for (Professor professor : newProfessorsTableModel.getData()) {
            if (professor.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean existsParsedCourse(String name, String type) {
        for (Course course : newCoursesTableModel.getData()) {
            if (course.getName().equals(name) && course.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean existsEnrollment(String courseName, String courseType, String studentEmail) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        String query = "SELECT * " +
                "FROM Enrollments " +
                "WHERE CourseID = (SELECT CourseID FROM Courses WHERE CourseName = ? AND CourseType = ?) " +
                "AND StudentID = (SELECT StudentID FROM Students WHERE Email = ?)";
        PreparedStatement stmt = db.connection.prepareStatement(query);

        stmt.setString(1, courseName);
        stmt.setString(2, courseType);
        stmt.setString(3, studentEmail);

        return stmt.executeQuery().next();
    }


    private void removeDuplicateEnrollments() {
        for (int i = 0; i < newEnrollmentsTableModel.getRowCount(); i++) {
            for (int j = i + 1; j < newEnrollmentsTableModel.getRowCount(); j++) {
                if (newEnrollmentsTableModel.getData().get(i).equals(newEnrollmentsTableModel.getData().get(j))) {
                    newEnrollmentsTableModel.getData().remove(j);
                    j--;
                }
            }
        }
    }

}
