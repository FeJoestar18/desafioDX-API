package br.com.duxusdesafio.Application.Helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import br.com.duxusdesafio.Application.Exception.InvalidDateFormatException;

public class DateParserHelper {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM-yyyy"),
            DateTimeFormatter.ISO_LOCAL_DATE
    );

    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Tenta o próximo formato
            }
        }

        throw new InvalidDateFormatException(
                "Formato de data inválido para '" + dateStr + "'. Os formatos aceitos são: dd/MM/yyyy, dd-MM-yyyy, dd/MM-yyyy");
    }
}