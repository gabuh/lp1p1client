package br.edu.ifsp.lp1p1client.request.loan;

import br.edu.ifsp.lp1p1client.dto.loan.LoanRequestDTO;
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

}
