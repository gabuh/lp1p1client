package br.edu.ifsp.lp1p1client.util;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;

public class BookUtil {

    public static void formatToString(BookResponseDTO book){
        System.out.println("");
        System.out.println("id: "+book.id());
        System.out.println("title: "+book.title());
        System.out.println("author: "+book.author());
        System.out.println("publisher: "+book.publisher());
        System.out.println("publication year: "+book.publicationYear());
        System.out.println("number of copies: "+book.numberOfCopies());
        System.out.println("number of copies available: "+book.numberOfCopiesAvailable());
        System.out.println("");
    }

}
