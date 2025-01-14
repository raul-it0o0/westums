package com.westums.models;

public class Course {

    public final static String LECTURE = "Lecture";
    public final static String SEMINAR = "Seminar";
    public final static String LABORATORY = "Laboratory";

    private int id;
    private String name;
    private String type;

    public Course(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            return ((Course) obj).getID() == id
                    && ((Course) obj).getName().equals(name)
                    && ((Course) obj).getType().equals(type);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Course: %s, ID = %d Type: %s", name, id, type);
    }
}
