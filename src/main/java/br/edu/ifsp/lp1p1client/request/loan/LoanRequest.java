package br.edu.ifsp.lp1p1client.request.loan;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.dto.loan.LoanRequestDTO;
import br.edu.ifsp.lp1p1client.dto.reservation.ReservationRequestDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class LoanRequest {


    public static ResponseEntity<Void> createLoan(String token, Long bookId, LoanRequestDTO loan){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books/{id}/loan",
                HttpMethod.POST,
                new HttpEntity<>(loan, headers),
                Void.class,
                bookId);
    }

    public static ResponseEntity<Void> createReservation(String token, Long bookId, ReservationRequestDTO reservation){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books/{id}/reservation",
                HttpMethod.POST,
                new HttpEntity<>(reservation, headers),
                Void.class,
                bookId);
    }

    public static BookResponseDTO cancelReservation(String token, Long bookId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books/{id}/cancel/reservation",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BookResponseDTO.class,
                bookId).getBody();
    }

}
