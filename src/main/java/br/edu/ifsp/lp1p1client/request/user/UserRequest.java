package br.edu.ifsp.lp1p1client.request.user;

import br.edu.ifsp.lp1p1client.dto.user.LoginDTO;
import br.edu.ifsp.lp1p1client.dto.user.RegisterDTO;
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

    public static List<UserResponseDTO> findAll(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return List.of(Objects.requireNonNull(new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserResponseDTO[].class).getBody()));

    }

    public static UserResponseDTO findByCpf(String token, String cpf){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users/find?cpf={cpf}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserResponseDTO.class,
                cpf).getBody();

    }

    public static List<UserResponseDTO> findByName(String token, String name){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return List.of(Objects.requireNonNull(new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users/find?name={name}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                UserResponseDTO[].class,
                name).getBody()));

    }

    public static ResponseEntity<Void> register(String token, RegisterDTO register){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users",
                HttpMethod.POST,
                new HttpEntity<>(register, headers),
                Void.class);
    }

    public static ResponseEntity<Void> registerClient(RegisterDTO register){

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users/client",
                HttpMethod.POST,
                new HttpEntity<>(register),
                Void.class);
    }

    public static ResponseEntity<Void> deleteUserById(String token, Long userId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);

        return new RestTemplate().exchange(
                "http://localhost:8080/api/v1/users/{id}",
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                Void.class,
                userId);
    }

}
