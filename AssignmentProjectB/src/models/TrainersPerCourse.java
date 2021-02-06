package models;

public class TrainersPerCourse {

    private int trainerId;
    private int courseId;

    public TrainersPerCourse(int trainerId, int courseId) {
        this.trainerId = trainerId;
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @Override
    public String toString() {
        return "TrainersPerCourse{" + "trainerId=" + trainerId + ", courseId=" + courseId + '}';
    }
    

}
