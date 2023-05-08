package br.edu.ifsp.lp1p1client.util;

import br.edu.ifsp.lp1p1client.dto.loan.LoanResponseDTO;

public class LoanUtil {

    public static void formatToString(LoanResponseDTO loan){
        System.out.println("");
        System.out.println("id: "+loan.id());
        System.out.println("book: "+loan.book().title());
        System.out.println("client id: "+loan.clientId());
        System.out.println("employee id: "+loan.userId());
        System.out.println("loan date: "+loan.loanDate());
        System.out.println("return date: "+loan.returnDate());
        System.out.println("");
    }
    
}
