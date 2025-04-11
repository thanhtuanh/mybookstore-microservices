package com.mybookstore.bookservice.controller;

import com.mybookstore.bookservice.exception.BookNotFoundException;
import com.mybookstore.bookservice.service.BookService;
import com.mybookstore.bookservice.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book management APIs")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Alle Bücher anzeigen", description = "Gibt eine Liste aller Bücher zurück")
    @ApiResponse(responseCode = "200", description = "Bücher erfolgreich abgerufen")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buch nach ID suchen", description = "Gibt ein Buch anhand seiner ID zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buch erfolgreich gefunden"),
            @ApiResponse(responseCode = "404", description = "Buch nicht gefunden", content = @Content)
    })
    public ResponseEntity<BookDTO> getBookById(
            @Parameter(description = "ID des Buches", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Buch nach ISBN suchen", description = "Gibt ein Buch anhand seiner ISBN zurück")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buch erfolgreich gefunden"),
            @ApiResponse(responseCode = "404", description = "Buch nicht gefunden", content = @Content)
    })
    public ResponseEntity<BookDTO> getBookByIsbn(
            @Parameter(description = "ISBN des Buches", required = true) @PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping("/author")
    @Operation(summary = "Bücher nach Autor suchen", description = "Gibt eine Liste von Büchern eines bestimmten Autors zurück")
    @ApiResponse(responseCode = "200", description = "Bücher erfolgreich gefunden")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(
            @Parameter(description = "Name des Autors", required = true) @RequestParam String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @GetMapping("/title")
    @Operation(summary = "Bücher nach Titel suchen", description = "Gibt eine Liste von Büchern mit passendem Titel zurück")
    @ApiResponse(responseCode = "200", description = "Bücher erfolgreich gefunden")
    public ResponseEntity<List<BookDTO>> getBooksByTitle(
            @Parameter(description = "Titel des Buches", required = true) @RequestParam String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));
    }

    @GetMapping("/genre")
    @Operation(summary = "Bücher nach Genre suchen", description = "Gibt eine Liste von Büchern eines bestimmten Genres zurück")
    @ApiResponse(responseCode = "200", description = "Bücher erfolgreich gefunden")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(
            @Parameter(description = "Genre der Bücher", required = true) @RequestParam String genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }

    @GetMapping("/price")
    @Operation(summary = "Bücher unter einem bestimmten Preis suchen", description = "Gibt eine Liste von Büchern unter einem bestimmten Preis zurück")
    @ApiResponse(responseCode = "200", description = "Bücher erfolgreich gefunden")
    public ResponseEntity<List<BookDTO>> getBooksCheaperThan(
            @Parameter(description = "Maximaler Preis", required = true) @RequestParam Double maxPrice) {
        return ResponseEntity.ok(bookService.getBooksCheaperThan(maxPrice));
    }

    @GetMapping("/available")
    @Operation(summary = "Verfügbare Bücher anzeigen", description = "Gibt eine Liste aller verfügbaren Bücher zurück")
    @ApiResponse(responseCode = "200", description = "Verfügbare Bücher erfolgreich abgerufen")
    public ResponseEntity<List<BookDTO>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }

    @PostMapping
    @Operation(summary = "Neues Buch erstellen", description = "Erstellt ein neues Buch mit den angegebenen Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Buch erfolgreich erstellt"),
            @ApiResponse(responseCode = "400", description = "Ungültige Daten", content = @Content),
            @ApiResponse(responseCode = "409", description = "Buch mit dieser ISBN existiert bereits", content = @Content)
    })
    public ResponseEntity<BookDTO> createBook(
            @Parameter(description = "Details des zu erstellenden Buches", required = true, schema = @Schema(implementation = BookDTO.class)) @Valid @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Buch aktualisieren", description = "Aktualisiert ein bestehendes Buch mit den angegebenen Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buch erfolgreich aktualisiert"),
            @ApiResponse(responseCode = "400", description = "Ungültige Daten", content = @Content),
            @ApiResponse(responseCode = "404", description = "Buch nicht gefunden", content = @Content),
            @ApiResponse(responseCode = "409", description = "Buch mit dieser ISBN existiert bereits", content = @Content)
    })
    public ResponseEntity<BookDTO> updateBook(
            @Parameter(description = "ID des zu aktualisierenden Buches", required = true) @PathVariable Long id,
            @Parameter(description = "Aktualisierte Details des Buches", required = true, schema = @Schema(implementation = BookDTO.class)) @Valid @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Buch löschen", description = "Löscht ein Buch anhand seiner ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Buch erfolgreich gelöscht"),
            @ApiResponse(responseCode = "404", description = "Buch nicht gefunden", content = @Content)
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID des zu löschenden Buches", required = true) @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
