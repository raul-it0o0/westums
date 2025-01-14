
# WestUMS

WestUMS, as the acronym implies, is a University Management System, written in Java. It features a GUI created with Java Swing.
Users of the software include university system administrators (e.g. secretaries), professors and students at a university.


## Features/Requirements

- An administrator can add students, professors and courses into the database. Additionally, they can view what they have added into the database (i.e. see all registered students, registered professors, registered courses)
- The administrator can also register an enrollment, i.e. link a student to a course.
- Professors can take attendance for their taught lectures/seminars/laboratories
- Students can check their attendance at courses, and receive warnings if their attendance exceeds the respective course's absence limit.
- Professors can assign grades for tests and exams.
- Students can view their grades at tests and exams they've taken in courses they attend.
- Professors can view statistics regarding their taught course's attendance, as well as grades in exams and tests.



## Lessons Learned

- This is my first GUI project, and I realized that UI implementations (in programming) as a whole are very similar. For example:
    - Just like HTML, `JFrame` functions as an HTML file (a single page).
    - The `JPanel` functions as a `<div>` component in HTML.
- I also got introduced to the MVC (Model, View, Controller) software architecture, which this application is a *slight* example of.


## Possible Improvements

- Pre-importing data into the database, so that the user doesn't have to manually add students, professors and courses.
- Use an embedded database (e.g. H2, SQLite) that don't require the user to manually create the database and to start the server.
- Use a cloud database to allow users on different machines to have access to the same data.
- Implement database pooling, a method that can keep database connections open at all times, to avoid creating a database connection everytime the database needs to be queried or manipulated.

