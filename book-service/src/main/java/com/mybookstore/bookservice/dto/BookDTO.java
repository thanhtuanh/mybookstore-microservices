package com.mybookstore.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    @NotBlank(message = "Titel darf nicht leer sein")
    @Size(min = 2, max = 100, message = "Der Titel muss zwischen 2 und 100 Zeichen lang sein")
    private String title;

    @NotBlank(message = "Autor darf nicht leer sein")
    private String author;

    @NotBlank(message = "ISBN darf nicht leer sein")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "Ungültiges ISBN-Format")
    private String isbn;

    @Size(max = 2000, message = "Die Beschreibung darf maximal 2000 Zeichen lang sein")
    private String description;

    private LocalDate publicationDate;

    @Positive(message = "Der Preis muss größer als 0 sein")
    private Double price;

    @Positive(message = "Die Seitenzahl muss größer als 0 sein")
    private Integer pages;

    private String genre;

    @NotNull(message = "Verfügbarkeitsstatus darf nicht leer sein")
    private Boolean inStock;
}
