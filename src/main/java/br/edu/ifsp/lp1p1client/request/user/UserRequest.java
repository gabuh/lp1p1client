package br.edu.ifsp.lp1p1client.request.user;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.dto.user.LoginDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class UserRequest {

    public static String login(String email, String password){
        LoginDTO login = new LoginDTO(email, password);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/login",
                HttpMethod.POST,
                new HttpEntity<>(login),
                String.class).getBody();
    }

    public static UserResponseDTO filter(String token, String email){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        List<UserResponseDTO> users = List.of(Objects.requireNonNull(new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserResponseDTO[].class).getBody()));

        for (UserResponseDTO u : users){
            if(Objects.equals(email, u.email())){
                return u;
            }
        }

        return null;
    }

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


}
