package br.edu.ifsp.lp1p1client.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class GuestUI {

    private static short option = -1;
    private static Scanner input = new Scanner(System.in);

    public static void show(){
        do {
            System.out.println("[Guest]");
            System.out.println("1. Login");
            System.out.println("2. Register Customer");
            System.out.println("0. Quit");
            option = Short.parseShort(input.nextLine().replaceAll("\\D+",""));
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

