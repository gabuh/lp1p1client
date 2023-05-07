package br.edu.ifsp.lp1p1client.entity;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Loan {

    private Long id;
    private Book book;
    private User client;
    private User user;
    private Instant loanDate;
    private Instant returnDate;
    
}
