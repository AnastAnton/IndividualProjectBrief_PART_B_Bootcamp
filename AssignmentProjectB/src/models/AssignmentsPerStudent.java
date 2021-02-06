package models;

public class AssignmentsPerStudent {
    
    private int studentsPerCourse;
    private int assignments;

    public AssignmentsPerStudent(int studentsPerCourse, int assignments) {
        this.studentsPerCourse = studentsPerCourse;
        this.assignments = assignments;
    }

    public int getAssignments() {
        return assignments;
    }

    public void setAssignments(int assignments) {
        this.assignments = assignments;
    }

    public int getStudentsPerCourse() {
        return studentsPerCourse;
    }

    public void setStudentsPerCourse(int studentsPerCourse) {
        this.studentsPerCourse = studentsPerCourse;
    }

    @Override
    public String toString() {
        return "AssignmentsPerStudent{" + "studentsPerCourse=" + studentsPerCourse + ", assignments=" + assignments + '}';
    }


}
