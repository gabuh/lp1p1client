package br.edu.ifsp.lp1p1client.ui;

import br.edu.ifsp.lp1p1client.dto.user.RegisterDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;
import br.edu.ifsp.lp1p1client.request.user.UserRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Scanner;

@Getter
@Setter
public class GuestUI {

    private static Scanner input = new Scanner(System.in);
    private static String email;
    private static String password;

    public static void show(){
        short option = -1;
        do {
            String inputStr;
            System.out.println("[Guest]");
            System.out.println("1. Login");
            System.out.println("2. Register Customer");
            System.out.println("0. Quit");
            inputStr = input.nextLine().replaceAll("\\D+","");
            option = Short.parseShort((!inputStr.equals("")?inputStr:"-1"));
            switch (option) {
                case 1 -> {
                    System.out.println("Type your email:");
                    email = input.nextLine();
                    System.out.println("Type your password:");
                    password = input.nextLine();
                    String token = UserRequest.login(email, password);
                    UserResponseDTO user = UserRequest.filter(token, email);
                        switch (user.role()) {
                            case ADMIN -> {
                                AdminUI.show(user, token);
                            }
                            case CLIENT -> {
                                ClientUI.show(user, token);
                            }
                            case EMPLOYEE -> {
                                EmployeeUI.show(user, token);
                            }
                        }
                }
                case 2 -> {
                    System.out.println("Type the name of the new user");
                    String name = input.nextLine();
                    System.out.println("Type the cpf of the new user (XXX.XXX.XXX-XX)");
                    String cpf = input.nextLine();
                    System.out.println("Type the address of the new user");
                    String address = input.nextLine();
                    System.out.println("Type the email of the new user");
                    String email = input.nextLine();
                    System.out.println("Type the password of the new user");
                    String password = input.nextLine();
                    RegisterDTO register = new RegisterDTO(name, cpf, address, email, password, UserRoles.CLIENT);
                    ResponseEntity<Void> response = UserRequest.registerClient(register);
                    System.out.println();
                    System.out.println("Client created");
                    System.out.println();
                }
                case 0 -> {
                    System.out.println("Quiting");
                    option = 0;
                }
                default -> System.out.println("Invalid Option, Try again.");
            }

        }while(option != 0);
        System.exit(0);
    }

}

