package br.edu.ifsp.lp1p1client.ui;

import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;
import br.edu.ifsp.lp1p1client.request.user.UserRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class GuestUI {

    private static short option = -1;
    private static Scanner input = new Scanner(System.in);
    private static String email;
    private static String password;

    public static void show(){
        do {
            System.out.println("[Guest]");
            System.out.println("1. Login");
            System.out.println("2. Register Customer");
            System.out.println("0. Quit");
            option = Short.parseShort(input.nextLine().replaceAll("\\D+",""));
            switch (option) {
                case 1 -> {
                    System.out.println("Type your email:");
                    email = input.nextLine();
                    System.out.println("Type your password:");
                    password = input.nextLine();
                    String token = UserRequest.login(email, password);
                    UserRoles role = UserRequest.filter(token, email);

                    switch (role) {
                        case ADMIN -> {
                            AdminUI.show();
                        }
                    }
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

