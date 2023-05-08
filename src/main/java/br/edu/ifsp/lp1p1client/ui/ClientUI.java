package br.edu.ifsp.lp1p1client.ui;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import br.edu.ifsp.lp1p1client.request.user.UserRequest;
import br.edu.ifsp.lp1p1client.util.BookUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class ClientUI {
    private static final Scanner input = new Scanner(System.in);
    public static void show(UserResponseDTO client, String token){
        short option = -1;
        String inputStr;
        do {
            System.out.println("[Client: "+ client.name() +" ]" );
            System.out.println("1. List all books");
            System.out.println("2. List book(s) by title");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("0. Quit");
            inputStr = input.nextLine().replaceAll("\\D+","");
            option = Short.parseShort((!inputStr.equals("")?inputStr:"-1"));

            switch (option) {
                case 1 -> {
                    List<BookResponseDTO> books = UserRequest.findAllBooks(token);
                    for(BookResponseDTO b : books){
                        BookUtil.formatToString(b);
                    }
                }
                case 2 -> {
                    System.out.println("Type the book title");
                    String title = input.nextLine();
                    List<BookResponseDTO> books = UserRequest.findAllBooksByTitle(token, title);
                    for (BookResponseDTO b : books) {
                        BookUtil.formatToString(b);
                    }
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
