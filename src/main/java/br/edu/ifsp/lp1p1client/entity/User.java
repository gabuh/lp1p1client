package br.edu.ifsp.lp1p1client.entity;

import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class User {

    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String password;
    private UserRoles role;

}
