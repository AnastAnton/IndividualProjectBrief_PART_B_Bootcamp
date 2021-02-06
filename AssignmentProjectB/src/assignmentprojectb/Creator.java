package assignmentprojectb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Creator {

    cmdutils.Command cmd = new cmdutils.Command();

    public static void createCourse(Scanner sc, PreparedStatement pstatement, Connection connect, Statement statement, ResultSet resultSet) throws ParseException {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            models.Course course = new models.Course(cmd.getStringField(sc, "Insert Course's title"),
                    cmd.getStringField(sc, "Insert Course's stream"),
                    cmd.getStringField(sc, "Insert Course's type"),
                    convertStringToDate(cmd.getStringField(sc, "Insert Course's starting date as dd/MM/yyyy")),
                    convertStringToDate(cmd.getStringField(sc, "Insert Course's ending date as dd/MM/yyyy")));

            if (checkCourseExistence(statement, resultSet, course) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO Courses VALUES (default, ?, ?, ?, ?, ?)");
                pstatement.setString(1, course.getTitle());
                pstatement.setString(2, course.getStream());
                pstatement.setString(3, course.getType());
                pstatement.setDate(4, course.getStartDate());    // date()
                pstatement.setDate(5, course.getEndDate());     // date()
                pstatement.executeUpdate();
                System.out.println("New course created!");
            } else {
                System.out.println("This course already exists with id= " + checkCourseExistence(statement, resultSet, course));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkCourseExistence(Statement statement, ResultSet resultSet, models.Course course) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Courses");
            while (resultSet.next()) {
                if (course.getTitle() == resultSet.getString(2)
                        && course.getStream() == resultSet.getString(3)
                        && course.getType() == resultSet.getString(4)
                        && course.getStartDate() == resultSet.getDate(5)
                        && course.getEndDate() == resultSet.getDate(6)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

    public static void createStudent(Scanner sc, PreparedStatement pstatement, Connection connect, Statement statement, ResultSet resultSet) throws ParseException {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            models.Student student = new models.Student(cmd.getStringField(sc, "Insert Students's first name"),
                    cmd.getStringField(sc, "Insert Students's last name"),
                    convertStringToDate(cmd.getStringField(sc, "Insert Students's date of Birth as dd/MM/yyyy")),
                    cmd.getDoubleField(sc, "Insert Students's tuition fees"));

            if (checkStudentExistence(statement, resultSet, student) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO Students VALUES (default, ?, ?, ?, ?)");
                pstatement.setString(1, student.getFirstName());
                pstatement.setString(2, student.getLastName());
                pstatement.setDate(3, student.getDateOfBirth());     // date()
                pstatement.setDouble(4, student.getTuitionFees());
                pstatement.executeUpdate();
                System.out.println("New student created!");
            } else {
                System.out.println("This student already exists with id= " + checkStudentExistence(statement, resultSet, student));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkStudentExistence(Statement statement, ResultSet resultSet, models.Student student) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Students");
            while (resultSet.next()) {
                if (student.getFirstName() == resultSet.getString(2)
                        && student.getLastName() == resultSet.getString(3)
                        && student.getDateOfBirth() == resultSet.getDate(4)
                        && student.getTuitionFees() == resultSet.getDouble(5)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

    public static void createTrainer(Scanner sc, PreparedStatement pstatement, Connection connect, Statement statement, ResultSet resultSet) throws ParseException {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            models.Trainer trainer = new models.Trainer(cmd.getStringField(sc, "Insert Trainer's first name"),
                    cmd.getStringField(sc, "Insert Trainer's last name"),
                    cmd.getStringField(sc, "Insert Trainer's subject"));

            if (checkTrainerExistence(statement, resultSet, trainer) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO Trainers VALUES (default, ?, ?, ?)");
                pstatement.setString(1, trainer.getFirstName());
                pstatement.setString(2, trainer.getLastName());
                pstatement.setString(3, trainer.getSubject());
                pstatement.executeUpdate();
                System.out.println("New trainer created!");
            } else {
                System.out.println("This student already exists with id= " + checkTrainerExistence(statement, resultSet, trainer));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkTrainerExistence(Statement statement, ResultSet resultSet, models.Trainer trainer) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Trainers");
            while (resultSet.next()) {
                if (trainer.getFirstName() == resultSet.getString(2)
                        && trainer.getLastName() == resultSet.getString(3)
                        && trainer.getSubject() == resultSet.getString(4)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

    public static void createAssignment(Scanner sc, PreparedStatement pstatement, Connection connect, Statement statement, ResultSet resultSet) throws ParseException {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            models.Assignment assignment = new models.Assignment(cmd.getStringField(sc, "Insert Assignment's title"),
                    cmd.getStringField(sc, "Insert Assignment's description"),
                    convertStringToDate(cmd.getStringField(sc, "Insert Assignment's submission date and time")),
                    cmd.getIntField(sc, "Insert Assignment's oral mark"),
                    cmd.getIntField(sc, "Insert Assignment's total mark"));

            if (checkAssignmentExistence(statement, resultSet, assignment) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO Assignments VALUES (default, ?, ?, ?, ?, ?)");
                pstatement.setString(1, assignment.getTitle());
                pstatement.setString(2, assignment.getDescription());
                pstatement.setDate(3, assignment.getSubDateTime());     // date() 
                pstatement.setInt(4, assignment.getOralMark());
                pstatement.setInt(5, assignment.getTotalMark());
                pstatement.executeUpdate();
                System.out.println("New assignment created!");
            } else {
                System.out.println("This assignment already exists with id= " + checkAssignmentExistence(statement, resultSet, assignment));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkAssignmentExistence(Statement statement, ResultSet resultSet, models.Assignment assignment) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Assignments");
            while (resultSet.next()) {
                if (assignment.getTitle() == resultSet.getString(2)
                        && assignment.getDescription() == resultSet.getString(3)
                        && assignment.getSubDateTime() == resultSet.getDate(4)
                        && assignment.getOralMark() == resultSet.getInt(5)
                        && assignment.getTotalMark() == resultSet.getInt(6)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

    public static void createStudentsPerCourse(Scanner sc, PreparedStatement pstatement, Connection connect, ResultSet resultSet, Statement statement) {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            Printer.printStudents(statement, resultSet);
            System.out.println("---------------");
            Printer.printCourses(statement, resultSet);
            models.StudentsPerCourse spc = new models.StudentsPerCourse(cmd.getIntField(sc, "Choose student's id to enroll on a course"),
                    cmd.getIntField(sc, "Choose course's id to enroll the student"));
//            resultSet = statement.executeQuery("SELECT first_name, last_name FROM Students WHERE Students.id = " + spc.getStudentId());
//            String name = resultSet.getString(1);
//            String lname = resultSet.getString(2);
//            resultSet = statement.executeQuery("SELECT title, stream, type FROM Courses WHERE Courses.id = " + spc.getCourseId());
//            String title = resultSet.getString(1);
//            String stream = resultSet.getString(2);
//            String type = resultSet.getString(3);

            if (checkStudentsPerCourseExistence(statement, resultSet, spc) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO StudentsPerCourses VALUES (default, ?, ?, ?)");
                pstatement.setInt(1, spc.getStudentId());
                pstatement.setInt(2, spc.getCourseId());
                pstatement.setString(3, "student's id: " + spc.getStudentId() + " course's id: " + spc.getCourseId());
                pstatement.executeUpdate();
                System.out.println("New enrollment created!");
            } else {
                System.out.println("This enrollment already exists with id= " + checkStudentsPerCourseExistence(statement, resultSet, spc));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkStudentsPerCourseExistence(Statement statement, ResultSet resultSet, models.StudentsPerCourse spc) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM StudentsPerCourses");
            while (resultSet.next()) {
                if (spc.getStudentId() == resultSet.getInt(2)
                        && spc.getCourseId() == resultSet.getInt(3)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

    public static void createTrainersPerCourse(Scanner sc, PreparedStatement pstatement, Connection connect, ResultSet resultSet, Statement statement) {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            Printer.printTrainers(statement, resultSet);
            System.out.println("---------------");
            Printer.printCourses(statement, resultSet);
            models.TrainersPerCourse tpc = new models.TrainersPerCourse(cmd.getIntField(sc, "Choose trainer's id to enroll on a course"),
                    cmd.getIntField(sc, "Choose course's id to enroll the trainer"));

            if (checkTrainersPerCourseExistence(statement, resultSet, tpc) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO TrainersPerCourses VALUES (default, ?, ?)");
                pstatement.setInt(1, tpc.getTrainerId());
                pstatement.setInt(2, tpc.getCourseId());
                pstatement.executeUpdate();
                System.out.println("New enrollment created!");
            } else {
                System.out.println("This enrollment already exists with id= " + checkTrainersPerCourseExistence(statement, resultSet, tpc));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkTrainersPerCourseExistence(Statement statement, ResultSet resultSet, models.TrainersPerCourse tpc) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM TrainersPerCourses");
            while (resultSet.next()) {
                if (tpc.getTrainerId() == resultSet.getInt(2)
                        && tpc.getCourseId() == resultSet.getInt(3)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

    public static void createAssignmentPerStudentPerCourse(Scanner sc, PreparedStatement pstatement, Connection connect, ResultSet resultSet, Statement statement) {
        try {
            cmdutils.Command cmd = new cmdutils.Command();
            Printer.printStudentsPerCourses(statement, resultSet);
            System.out.println("---------------");
            Printer.printAssignments(statement, resultSet);
            models.AssignmentsPerStudent aps = new models.AssignmentsPerStudent(cmd.getIntField(sc, "Choose students per course id "),
                    cmd.getIntField(sc, "Choose assignment's id"));

            if (checkAssignmentsPerStudent(statement, resultSet, aps) == 0) {
                pstatement = connect.prepareStatement("INSERT INTO AssignmentsPerCoursesPerStudents VALUES (default, ?, ?)");
                pstatement.setInt(1, aps.getStudentsPerCourse());
                pstatement.setInt(2, aps.getAssignments());
                pstatement.executeUpdate();
                System.out.println("New assignments per student created!");
            } else {
                System.out.println("This fields already exists with id= " + checkAssignmentsPerStudent(statement, resultSet, aps));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int checkAssignmentsPerStudent(Statement statement, ResultSet resultSet, models.AssignmentsPerStudent aps) {
        int check = 0;
        try {
            resultSet = statement.executeQuery("SELECT * FROM AssignmentsPerCoursesPerStudents");
            while (resultSet.next()) {
                if (aps.getStudentsPerCourse() == resultSet.getInt(2)
                        && aps.getAssignments() == resultSet.getInt(3)) {

                    check = resultSet.getInt(1);
                } else {
                    check = 0;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (check);
    }

//    public static Timestamp convertStringToTImestamp(String strDate) {
//        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
//        LocalDateTime ldt = LocalDateTime.parse(strDate, FORMATTER);
//        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
//        Timestamp timeStamp = new java.sql.Timestamp(zdt.toInstant().toEpochMilli());
//        return (timeStamp);
//    }
    public static Date convertStringToDate(String strDate) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ldt = LocalDate.parse(strDate, FORMATTER);
        Date date = new java.sql.Date(ldt.getYear() - 1900, ldt.getMonthValue() - 1, ldt.getDayOfMonth());
        return (date);
    }
}
