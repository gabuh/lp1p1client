package br.edu.ifsp.lp1p1client.dto.user;

import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;

public record RegisterDTO(String name,
                          String cpf,
                          String address,
                          String email,
                          String password,
                          UserRoles role) {
}
