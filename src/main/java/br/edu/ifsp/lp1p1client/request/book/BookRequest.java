package br.edu.ifsp.lp1p1client.request.book;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class BookRequest {
    public static List<BookResponseDTO> findAllBooks(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return List.of(Objects.requireNonNull(new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BookResponseDTO[].class).getBody()));
    }

    public static List<BookResponseDTO> findAllBooksByTitle(String token, String title){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return List.of(Objects.requireNonNull(new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books/find?title={title}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BookResponseDTO[].class,
                title).getBody()));
    }

    public static ResponseEntity<Void> deleteBookById(String token, Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books/{id}",
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                Void.class,
                id);
    }

    public static BookResponseDTO returnBook(String token, Long bookId, Long clientId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/books/{id}/return?clientId={clientId}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                BookResponseDTO.class,
                bookId,
                clientId).getBody();
    }

}
