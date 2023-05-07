package br.edu.ifsp.lp1p1client.dto.user;

import br.edu.ifsp.lp1p1client.entity.enums.user.UserRoles;

public record UserResponseDTO(Long id,
                              String name,
                              String cpf,
                              String address,
                              String email,
                              UserRoles role) {
}
