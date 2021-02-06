package models;

import java.text.ParseException;

public class Trainer {

    private String firstName;
    private String lastName;
    private String subject;

    public Trainer(String firstName, String lastName, String subject) throws ParseException{
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Trainer[" + "firstName=" + firstName + ", lastName=" + lastName + ", subject=" + subject + ']';
    }
    
    

}
