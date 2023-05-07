package br.edu.ifsp.lp1p1client.ui;

import java.util.Scanner;

public class AdminUI {
    private static final Scanner input = new Scanner(System.in);

    public static void show(){
        short option = -1;
        String inputStr;
        do {
            System.out.println("[Admin: " + "UserNameHere" +" ]");
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
}
