package cmdutils;

import java.util.Scanner;

public class Command {

    public String getStringField(Scanner sc, String message){
        System.out.println(message);
        return (sc.next());
    }
    public double getDoubleField(Scanner sc, String message){
        System.out.println(message);
        return (sc.nextDouble());
    }
    public int getIntField(Scanner sc, String message){
        System.out.println(message);
        return (sc.nextInt());
    }

    
}
