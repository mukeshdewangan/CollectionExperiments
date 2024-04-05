package FokatInitialization;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter a number- ");
        int a= sc.nextInt();
        if( a%2==0) {
            System.out.println(StaticClass.getFirstName() + " " + StaticClass.getLastName());
        }
        else {
            System.out.println(AnotherStaticClass.getEducation() + " " + AnotherStaticClass.getAge());
        }
    }
}
