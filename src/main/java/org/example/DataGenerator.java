package org.example;

import org.ajbrown.namemachine.NameGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Logger;

public class DataGenerator {

    public static void addRandomStudent() {
        NameGenerator nameGenerator = new NameGenerator();

        String surname = nameGenerator.generateName().getLastName();
        String name = nameGenerator.generateName().getFirstName();

        // Generate date of birth
        Date dob = new GregorianCalendar(
                getIntFromRange(1990, 2005),
                getIntFromRange(0, 12),
                getIntFromRange(1, 13)).getTime();
        java.sql.Date dobSQL = new java.sql.Date(dob.getTime());

            String specialization = "IR";
        int year = getIntFromRange(1, 4);

        String query = null;
        PreparedStatement preparedStatement = null;

        try {

            DatabaseConnection conn = new DatabaseConnection();

            query = "SELECT COUNT(*) " +
                    "FROM students " +
                    "WHERE studentID LIKE ?";
            preparedStatement = conn.connection.prepareStatement(query);
            preparedStatement.setString(1, specialization + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            int IDIndex;
            final int studentIDLength = 7;

            if (resultSet.next())
                IDIndex = resultSet.getInt(1) + 1;
            else throw new SQLException("Counting query invalid.");

            // Construct studentID string
            /*
                Format:
                [SPECIALIZATION][INDEX PADDED TO THE LEFT WITH 0]
                e.g. IE00056
             */
            StringBuilder stringBuilder = new StringBuilder(specialization);
            stringBuilder.insert(specialization.length(), IDIndex);

            while (stringBuilder.length() < studentIDLength)
                stringBuilder.insert(specialization.length(), 0);

            String studentID = stringBuilder.toString();

            // Insert generated student into DB
            query = "INSERT INTO westums.students " +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.connection.prepareStatement(query);
            preparedStatement.setString(1, studentID);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, name);
            preparedStatement.setDate(4, dobSQL);
            preparedStatement.setString(5, specialization);
            preparedStatement.setInt(6, year);
            preparedStatement.executeUpdate();
            Logger.getAnonymousLogger().info("ADDED STUDENT (studentID " + studentID + ")");

//            conn.statement.executeUpdate(
//                    "INSERT INTO westums.students " +
//                            "VALUES(" + studentID + ',' + surname + ',' + name + ',' + dob + ',' + specialization + ")"
//            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addRandomProfessor() {
        NameGenerator nameGenerator = new NameGenerator();

        String surname = nameGenerator.generateName().getLastName();
        String name = nameGenerator.generateName().getFirstName();

        // Generate date of birth
        Date dob = new GregorianCalendar(
                getIntFromRange(1960, 2002),
                getIntFromRange(0, 12),
                getIntFromRange(1, 13)).getTime();
        java.sql.Date dobSQL = new java.sql.Date(dob.getTime());

        String query = null;
        PreparedStatement preparedStatement = null;

        try {

            DatabaseConnection conn = new DatabaseConnection();

            // Insert generated student into DB
            query = "INSERT INTO professors(surname, name, DOB) " +
                    "VALUES(?, ?, ?)";
            preparedStatement = conn.connection.prepareStatement(query);
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, dobSQL);
            preparedStatement.executeUpdate();
            Logger.getAnonymousLogger().info("ADDED TEACHER\n");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIntFromRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }
}
