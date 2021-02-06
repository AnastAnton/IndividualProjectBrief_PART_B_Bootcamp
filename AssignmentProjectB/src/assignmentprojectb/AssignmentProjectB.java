package assignmentprojectb;

import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignmentProjectB {

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement pstatement = null;
    private static ResultSet resultSet = null;

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        cmdutils.Command cmd = new cmdutils.Command();
        connectToDbServer(sc);
        do {
            chooseAction(sc);
        } while (cmd.getStringField(sc, "Insert y to choose action again, else any other key to exit program").equals("y"));
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentProjectB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connection closed! Bye :)");
    }

    private static void connectToDbServer(Scanner sc) {
        cmdutils.Command cmd = new cmdutils.Command();
        System.out.println("Welcome!");
        String username = cmd.getStringField(sc, "Insert username to connect to database");
        String password = cmd.getStringField(sc, "Insert password");
        String database = cmd.getStringField(sc, "Insert database");
        String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&serverTimezone=Europe/Athens";

        try {
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            System.out.println("-------------");
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentProjectB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection is not established");
        }
    }

    public static void chooseAction(Scanner sc) throws ParseException {
        cmdutils.Command cmd = new cmdutils.Command();
        System.out.println("Choose an action option");
        System.out.println("Insert 1 to show lists");
        System.out.println("insert 2 to create lists");
        System.out.println("Insert any other to close the program");
        System.out.println("-------------");

        try {
            switch (sc.next()) {
                case ("1"):
                    do {
                        chooseListFromMenu(sc);
                    } while (cmd.getStringField(sc, "Insert y to choose another list, else any other key").equals("y"));
                    break;
                case ("2"):
                    do {
                        chooseCreateMenu(sc);
                    } while (cmd.getStringField(sc, "Insert y to create another list, else any other key").equals("y"));
                    break;
                default:
                    System.out.println("Exiting the program..");
                    System.exit(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentProjectB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void chooseListFromMenu(Scanner sc) throws SQLException {
        System.out.println("Choose a printing option");
        System.out.println("Insert 1 to print all students");
        System.out.println("Insert 2 to print all trainers");
        System.out.println("Insert 3 to print all assignments");
        System.out.println("Insert 4 to print all courses");
        System.out.println("Insert 5 to print all students per course");
        System.out.println("Insert 6 to print all trainers per course");
        System.out.println("Insert 7 to print all assignments per course");
        System.out.println("Insert 8 to print all assignments per student");
        System.out.println("Insert 9 to print all students who are enrolled to more than one courses");
        System.out.println("Insert any other to close the program");
        System.out.println("-------------");
        statement = connect.createStatement();

        switch (sc.next()) {
            case ("1"):
                Printer.printStudents(statement, resultSet);
                break;
            case ("2"):
                Printer.printTrainers(statement, resultSet);
                break;
            case ("3"):
                Printer.printAssignments(statement, resultSet);
                break;
            case ("4"):
                Printer.printCourses(statement, resultSet);
                break;
            case ("5"):
                Printer.printStudentsPerCourses(statement, resultSet);
                break;
            case ("6"):
                Printer.printTrainersPerCourses(statement, resultSet);
                break;
            case ("7"):
                Printer.printAssignmentsPerCourses(statement, resultSet);
                break;
            case ("8"):
                Printer.printAssignmentsPerStudent(statement, resultSet);
                break;
            case ("9"):
                Printer.printStudentsInManyCourses(statement, resultSet);
                break;
            default:
                System.out.println("Exiting the program..");
                System.exit(0);
        }
    }

    public static void chooseCreateMenu(Scanner sc) throws ParseException, SQLException {

        System.out.println("What would you like to create?");
        System.out.println("Insert 1 to create a student");
        System.out.println("Insert 2 to create a trainer");
        System.out.println("Insert 3 to create an assignment");
        System.out.println("Insert 4 to create a course");
        System.out.println("Insert 5 to create students per course");
        System.out.println("Insert 6 to create trainers course");
        System.out.println("Insert 7 to create assignments per student per course");
        System.out.println("Insert any other to close the program");
        System.out.println("-------------");
        statement = connect.createStatement();

        switch (sc.next()) {
            case "1":
                Creator.createStudent(sc, pstatement, connect, statement, resultSet);
                break;
            case "2":
                Creator.createTrainer(sc, pstatement, connect, statement, resultSet);
                break;
            case "3":
                Creator.createAssignment(sc, pstatement, connect, statement, resultSet);
                break;
            case "4":
                Creator.createCourse(sc, pstatement, connect, statement, resultSet);
                break;
            case "5":
                Creator.createStudentsPerCourse(sc, pstatement, connect, resultSet, statement);
                break;
            case "6":
                Creator.createTrainersPerCourse(sc, pstatement, connect, resultSet, statement);
                break;
            case "7":
                Creator.createAssignmentPerStudentPerCourse(sc, pstatement, connect, resultSet, statement);
                break;
            default:
                System.out.println("Exiting the program");
                System.exit(0);
        }
    }
}
