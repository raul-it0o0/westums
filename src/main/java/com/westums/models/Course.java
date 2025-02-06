package com.westums.models;

public class Course {

    public final static String LECTURE = "Lecture";
    public final static String SEMINAR = "Seminar";
    public final static String LABORATORY = "Laboratory";

    private int id;
    private String name;
    private String type;
    private String professorEmail;

    public Course(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.professorEmail = builder.professorEmail;
    }

    public Course(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static Builder builder(String name, String type, String professorEmail) {
        return new Builder(name, type, professorEmail);
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

    public String getProfessorEmail() {
        return professorEmail;
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

    public static class Builder {
        private int id;
        private String name;
        private String type;
        private String professorEmail;

        public Builder(String name, String type, String professorEmail) {
            this.id = 0;
            this.name = name;
            this.type = type;
            this.professorEmail = professorEmail;
        }

        public Course build() {
            return new Course(this);
        }


    }
}
