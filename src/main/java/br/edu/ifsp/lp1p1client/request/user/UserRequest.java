package br.edu.ifsp.lp1p1client.request.user;

import br.edu.ifsp.lp1p1client.dto.user.LoginDTO;
import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;
import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;
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

    public static UserRoles filter(String token, String email){
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
                return u.role();
            }
        }

        return null;
    }

}
