package br.edu.ifsp.lp1p1client.ui;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.dto.loan.LoanResponseDTO;
import br.edu.ifsp.lp1p1client.dto.reservation.ReservationRequestDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import br.edu.ifsp.lp1p1client.request.book.BookRequest;
import br.edu.ifsp.lp1p1client.request.loan.LoanRequest;
import br.edu.ifsp.lp1p1client.request.user.UserRequest;
import br.edu.ifsp.lp1p1client.util.BookUtil;
import br.edu.ifsp.lp1p1client.util.DateUtil;
import br.edu.ifsp.lp1p1client.util.LoanUtil;
import br.edu.ifsp.lp1p1client.util.UserUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

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
            System.out.println("3. Create a reservation");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. List your loans and reservations");
            System.out.println("6. List all clients");
            System.out.println("0. Logout");
            inputStr = input.nextLine().replaceAll("\\D+","");
            option = Short.parseShort((!inputStr.equals("")?inputStr:"-1"));

            switch (option) {
                case 1 -> {
                    List<BookResponseDTO> books = BookRequest.findAllBooks(token);
                    for(BookResponseDTO b : books){
                        BookUtil.formatToString(b);
                    }
                }
                case 2 -> {
                    System.out.println("Type the book title");
                    String title = input.nextLine();
                    List<BookResponseDTO> books = BookRequest.findAllBooksByTitle(token, title);
                    for (BookResponseDTO b : books) {
                        BookUtil.formatToString(b);
                    }
                }
                case 3 -> {
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
                case 4 ->{
                    System.out.println("Type the id of the book to cancel your reservations");
                    Long bookId = input.nextLong();
                    BookResponseDTO book = LoanRequest.cancelReservation(token, bookId);
                    BookUtil.formatToString(book);
                    input.nextLine();
                }
                case 5 -> {
                    List<LoanResponseDTO> loans = LoanRequest.findAllByClientId(token, client.id());
                    for(LoanResponseDTO l : loans){
                        LoanUtil.formatToString(l);
                    }
                }
                case 6 -> {
                    List<UserResponseDTO> users = UserRequest.findAllClients(token);
                    for (UserResponseDTO u: users) {
                        UserUtil.formatToString(u);
                    }
                }
                case 0 -> {
                    System.out.println("Quiting");
                    option = 0;
                }
                default -> System.out.println("Invalid Option, Try again.");
            }

            System.out.println("Enter to continue");
            input.nextLine();
            System.out.println("\033[H\033[2J");

        }while(option != 0);
    }

}
