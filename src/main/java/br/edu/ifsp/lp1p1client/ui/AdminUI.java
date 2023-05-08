package br.edu.ifsp.lp1p1client.ui;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.dto.loan.LoanRequestDTO;
import br.edu.ifsp.lp1p1client.dto.reservation.ReservationRequestDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import br.edu.ifsp.lp1p1client.request.loan.LoanRequest;
import br.edu.ifsp.lp1p1client.request.user.UserRequest;
import br.edu.ifsp.lp1p1client.util.BookUtil;
import br.edu.ifsp.lp1p1client.util.DateUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Scanner;

public class AdminUI {
    private static final Scanner input = new Scanner(System.in);

    public static void show(UserResponseDTO admin, String token){
        short option = -1;
        String inputStr;
        do {
            System.out.println("[Admin: " + admin.name() +"]");
            System.out.println("1. List all books");
            System.out.println("2. List book(s) by title");
            System.out.println("3. Delete book by id");
            System.out.println("4. Create a loan");
            System.out.println("5. Create a reservation");
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
                    for(BookResponseDTO b : books){
                        BookUtil.formatToString(b);
                    }
                }
                case 3 -> {
                    System.out.println("Type the book id");
                    Long id = input.nextLong();
                    ResponseEntity<Void> response = UserRequest.deleteBookById(token, id);
                    System.out.println();
                    System.out.println("Book #"+id+" deleted");
                    System.out.println();
                    input.nextLine();
                }
                case 4 -> {
                    System.out.println("Type the id of the book to make a loan");
                    Long bookId = input.nextLong();
                    input.nextLine();
                    System.out.println("Type the id of the client");
                    Long clientId = input.nextLong();
                    input.nextLine();
                    System.out.println("Type the return date");
                    String returnDate = input.nextLine();
                    LoanRequestDTO loan = new LoanRequestDTO(clientId, DateUtil.inputDateToInstant(returnDate).toString());
                    ResponseEntity<Void> response = LoanRequest.createLoan(token, bookId, loan);
                    System.out.println();
                    System.out.println("Loan created");
                    System.out.println();
                }
                case 5 -> {
                    System.out.println("Type the id of the book to make a reservation");
                    Long bookId = input.nextLong();
                    System.out.println("Type the id of the employee responsible for this reservation");
                    Long employeeId = input.nextLong();
                    input.nextLine();
                    System.out.println("Type how many days ahead the book will be caught");
                    short daysAhead = input.nextShort();
                    input.nextLine();
                    System.out.println("Type the return date");
                    String returnDate = input.nextLine();
                    ReservationRequestDTO reservation = new ReservationRequestDTO(employeeId, daysAhead, DateUtil.inputDateToInstant(returnDate).toString());
                    ResponseEntity<Void> response = LoanRequest.createReservation(token, bookId, reservation);
                    System.out.println();
                    System.out.println("Reservation created");
                    System.out.println();
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
