package br.edu.ifsp.lp1p1client.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static Instant inputDateToInstant(String dateInput){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateInput, formatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(23, 59, 0));
         return localDateTime.toInstant(ZoneOffset.UTC);
    }

}
