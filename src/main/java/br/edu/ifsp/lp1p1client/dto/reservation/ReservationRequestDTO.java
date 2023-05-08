package br.edu.ifsp.lp1p1client.dto.reservation;

public record ReservationRequestDTO(Long employeeId,
                                    short daysAhead,
                                    String returnDate) {
}
