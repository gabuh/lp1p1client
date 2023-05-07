package br.edu.ifsp.lp1p1client.ui;
<<<<<<< HEAD

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Setter
@Getter
public class EmployeeUI {
    private static final Scanner input = new Scanner(System.in);

    public static void show(){
        short option = -1;
        String inputStr;
        do {
            System.out.println("[Employee: "+ "UserNameHere" + "]");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("0. Quit");
            inputStr = input.nextLine().replaceAll("\\D+","");
            option = Short.parseShort((!inputStr.equals("")?inputStr:"-1"));
            switch (option) {
                case 1 -> {
                    System.out.println("opt1");
                }
                case 2 -> {
                    System.out.println("opt2");
                }
                case 0 -> {
                    System.out.println("Quiting");
                    option = 0;
                }
                default -> System.out.println("Invalid Option, Try again.");
            }

            System.out.println("\033[H\033[2J");

        }while(option != 0);
    }



=======
public class EmployeeUI {
>>>>>>> 5e0d39ea4f6620a223a1173a6a936de2d3b1fcbe
}
