package br.edu.ifsp.lp1p1client.util;

import br.edu.ifsp.lp1p1client.dto.user.UserResponseDTO;

public class UserUtil {

    public static void formatToString(UserResponseDTO user){
        System.out.println("");
        System.out.println("id: "+user.id());
        System.out.println("name: "+user.name());
        System.out.println("cpf: "+user.cpf());
        System.out.println("address: "+user.address());
        System.out.println("email: "+user.email());
        System.out.println("role: "+user.role());
        System.out.println("");
    }

}
