package br.edu.ifsp.lp1p1client.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Book {

    private Long id;
    private String author;
    private String publisher;
    private String publicationYear;
    private Integer numberOfCopies;
    private Integer numberOfCopiesAvailable;

}
