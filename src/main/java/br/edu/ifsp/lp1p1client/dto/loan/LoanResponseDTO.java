package br.edu.ifsp.lp1p1client.dto.loan;

import br.edu.ifsp.lp1p1client.dto.book.BookResponseDTO;
import br.edu.ifsp.lp1p1client.entity.Book;

import java.time.Instant;

public record LoanResponseDTO(Long id,
                              BookResponseDTO book,
                              Long clientId,
                              Long userId,
                              Instant loanDate,
                              Instant returnDate) {
}
