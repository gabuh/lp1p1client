package br.edu.ifsp.lp1p1client.ui;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.dto.loan.LoanRequestDTO;
import br.edu.ifsp.lp1p1client.dto.loan.LoanResponseDTO;
import br.edu.ifsp.lp1p1client.dto.reservation.ReservationRequestDTO;
import br.edu.ifsp.lp1p1client.dto.user.RegisterDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;
import br.edu.ifsp.lp1p1client.request.book.BookRequest;
import br.edu.ifsp.lp1p1client.request.loan.LoanRequest;
import br.edu.ifsp.lp1p1client.request.user.UserRequest;
import br.edu.ifsp.lp1p1client.util.BookUtil;
import br.edu.ifsp.lp1p1client.util.DateUtil;
import br.edu.ifsp.lp1p1client.util.LoanUtil;
import br.edu.ifsp.lp1p1client.util.UserUtil;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
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
            System.out.println("6. Return a book");
            System.out.println("7. Cancel a reservation");
            System.out.println("8. List all loans and reservations");
            System.out.println("9. List loans and reservations by client id");
            System.out.println("10. Register new user");
            System.out.println("11. Delete user by id");
            System.out.println("12. List all users");
            System.out.println("13. List user by id");
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
                    for(BookResponseDTO b : books){
                        BookUtil.formatToString(b);
                    }
                }
                case 3 -> {
                    System.out.println("Type the book id");
                    Long id = input.nextLong();
                    ResponseEntity<Void> response = BookRequest.deleteBookById(token, id);
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
                case 6 -> {
                    System.out.println("Type the id of the book");
                    Long bookId = input.nextLong();
                    input.nextLine();
                    System.out.println("Type the id of the client that returned the book");
                    Long clientId = input.nextLong();
                    input.nextLine();
                    BookResponseDTO book = BookRequest.returnBook(token, bookId, clientId);
                    BookUtil.formatToString(book);
                }
                case 7 -> {
                    System.out.println("Type the id of the book to cancel your reservations");
                    Long bookId = input.nextLong();
                    BookResponseDTO book = LoanRequest.cancelReservation(token, bookId);
                    BookUtil.formatToString(book);
                    input.nextLine();
                }
                case 8 -> {
                    List<LoanResponseDTO> loans = LoanRequest.findAll(token);
                    for(LoanResponseDTO l : loans){
                        LoanUtil.formatToString(l);
                    }
                }
                case 9 -> {
                    System.out.println("Type the id of the client");
                    Long clientId = input.nextLong();
                    List<LoanResponseDTO> loans = LoanRequest.findAllByClientId(token, clientId);
                    for(LoanResponseDTO l : loans){
                        LoanUtil.formatToString(l);
                    }
                    input.nextLine();
                }
                case 10 -> {
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
                    System.out.println("Type the role of the new user (ADMIN | EMPLOYEE | CLIENT)");
                    String role = input.nextLine();
                    RegisterDTO register = new RegisterDTO(name, cpf, address, email, password, UserRoles.valueOf(role));
                    ResponseEntity<Void> response = UserRequest.register(token, register);
                    System.out.println();
                    System.out.println("User created");
                    System.out.println();
                }
                case 11 -> {
                    System.out.println("Type the id of the user to be deleted");
                    Long userId = input.nextLong();
                    ResponseEntity<Void> response = UserRequest.deleteUserById(token, userId);
                    System.out.println();
                    System.out.println("User deleted");
                    System.out.println();
                    input.nextLine();
                }
                case 12 -> {
                    List<UserResponseDTO> users = UserRequest.findAll(token);
                    for(UserResponseDTO u : users){
                        UserUtil.formatToString(u);
                    }
                }
                case 13 -> {
                    System.out.println("Type the cpf of the user (XXX.XXX.XXX-XX)");
                    String cpf = input.nextLine();
                    UserResponseDTO user = UserRequest.findByCpf(token, cpf);
                    UserUtil.formatToString(user);
                }
                case 0 -> {
                    System.out.println("Quiting");
                    option = 0;
                }
                default -> System.out.println("Invalid Option, Try again.");
            }

        }while(option != 0);
    }

}
