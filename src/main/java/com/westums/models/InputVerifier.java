package com.westums.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputVerifier {

    private final static String ENTITY_NAME_REGEX = "^[^A-Z]|\\d|[|!@#$%^&*()`~_+=}{\\[\\]:;\"'?/>.<," +
            "]|-\\s*[^A-Z]|[^a-z]$";
    private final static String COURSE_NAME_REGEX = "^[^A-Z]|[|!@#$%^*`~_+=}{\\[\\];?>.<," +
            "]|-\\s*[^A-Z]";

    public static boolean isValidName(String input) {

        if (input.isEmpty()) return false;

        Pattern pattern = Pattern.compile(ENTITY_NAME_REGEX);
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.find();
        matcher.reset();

        // If matches, then not valid hence the '!'
        return !matches;
    }

    public static boolean isValidCourseName(String input) {

        if (input.isEmpty()) return false;

        Pattern pattern = Pattern.compile(COURSE_NAME_REGEX);
        Matcher matcher = pattern.matcher(input);
        boolean matches = matcher.find();
        matcher.reset();

        // If matches, then not valid hence the '!'
        return !matches;
    }
}
