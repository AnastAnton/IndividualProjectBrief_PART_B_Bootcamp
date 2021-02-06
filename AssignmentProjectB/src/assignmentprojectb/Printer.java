package assignmentprojectb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Printer {

    public static void printStudents(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. name, date of birth, tuition fees");
        resultSet = statement.executeQuery("SELECT * FROM Students");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + " "
                    + resultSet.getString(3) + ", "
                    + resultSet.getDate(4) + ", "
                    + resultSet.getInt(5));      
        }
        System.out.println("-------------");
    }
    
    public static void printTrainers(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. name, subject");
        resultSet = statement.executeQuery("SELECT * FROM Trainers");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + " "
                    + resultSet.getString(3) + ", "
                    + resultSet.getString(4));     
        }
        System.out.println("-------------");
    }

    public static void printAssignments(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. title, description, submission date and time, oral mark, total mark");
        resultSet = statement.executeQuery("SELECT * FROM Assignments");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + ", "
                    + resultSet.getString(3) + ", "
                    + resultSet.getTimestamp(4) + ", "
                    + resultSet.getInt(5) + ", "
                    + resultSet.getInt(6));    
        }
        System.out.println("-------------");
    }

    public static void printCourses(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. title, stream, type, starting date, ending date");
        resultSet = statement.executeQuery("SELECT * FROM Courses");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + ", "
                    + resultSet.getString(3) + ", "
                    + resultSet.getString(4) + ", "
                    + resultSet.getDate(5) + ", "
                    + resultSet.getDate(6));
        }
        System.out.println("-------------");
    }

    public static void printStudentsPerCourses(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. name, title, stream, type");
        resultSet = statement.executeQuery("SELECT StudentsPerCourses.id, first_name, last_name, title, stream, type "
                + " FROM StudentsPerCourses INNER JOIN Students on Students.id = students_id"
                + " INNER JOIN Courses on Courses.id = courses_id ORDER BY StudentsPerCourses.id ASC");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + " "
                    + resultSet.getString(3) + ", "
                    + resultSet.getString(4) + ", "
                    + resultSet.getString(5) + ", "
                    + resultSet.getString(6));
        }
        System.out.println("-------------");
    }

    public static void printTrainersPerCourses(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. name, title, stream, type");
        resultSet = statement.executeQuery("SELECT TrainersPerCourses.id, first_name, last_name, title, stream, type"
                + " FROM TrainersPerCourses INNER JOIN Trainers ON Trainers.id = trainers_id"
                + " INNER JOIN Courses ON Courses.id = courses_id");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + " "
                    + resultSet.getString(3) + ", "
                    + resultSet.getString(4) + ", "
                    + resultSet.getString(5) + ", "
                    + resultSet.getString(6));
        }
        System.out.println("-------------");
    }

    public static void printAssignmentsPerCourses(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. assignment title, description, submission date and time, course title, stream, type");
        resultSet = statement.executeQuery("SELECT AssignmentsPerCourses.id, Assignments.title, description, sub_date_time, Courses.title, stream, type"
                + " FROM AssignmentsPerCourses INNER JOIN Assignments ON Assignments.id = assignments_id"
                + " INNER JOIN Courses ON Courses.id = courses_id");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + ", "
                    + resultSet.getString(3) + ", "
                    + resultSet.getTimestamp(4) + ", "
                    + resultSet.getString(5) + ", "
                    + resultSet.getString(6) + ", "
                    + resultSet.getString(7));
        }
        System.out.println("-------------");
    }

    public static void printAssignmentsPerStudent(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("id. student on course, assignment title, assignment description, assignment submission date and time, oral mark, total mark");
        resultSet = statement.executeQuery("SELECT acs.id, student_on_course, title, description, sub_date_time, oral_mark, total_mark \n" +
                                           "  FROM `AssignmentsPerCoursesPerStudents` AS acs\n" +
                                           "  INNER JOIN StudentsPerCourses AS sc\n" +
                                           "    ON sc.id = acs.students_per_courses_id\n" +
                                           "  INNER JOIN Assignments AS a\n" +
                                           "    ON a.id = acs.assignments_id" +
                                           " ORDER BY acs.id ASC");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + ", "
                    + resultSet.getString(3) + ", "
                    + resultSet.getString(4) + ", "
                    + resultSet.getDate(5) + ", "
                    + resultSet.getInt(6) + ", "
                    + resultSet.getInt(7));
        }
        System.out.println("-------------");
    }

    public static void printStudentsInManyCourses(Statement statement, ResultSet resultSet) throws SQLException {
        System.out.println("student's id. name");
        resultSet = statement.executeQuery("SELECT Students.id, first_name, last_name"
                + " FROM StudentsPerCourses INNER JOIN Students ON Students.id = students_id"
                + " INNER JOIN Courses ON Courses.id = courses_id"
                + " GROUP BY first_name, last_name HAVING COUNT(*) > 1"
                + " ORDER BY StudentsPerCourses.id ASC");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + ". "
                    + resultSet.getString(2) + " "
                    + resultSet.getString(3));
        }
        System.out.println("-------------");
    }
}
