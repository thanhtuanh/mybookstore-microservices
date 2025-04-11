package com.mybookstore.bookservice.service;

import com.mybookstore.bookservice.dto.BookDTO;
import com.mybookstore.bookservice.model.Book;
import com.mybookstore.bookservice.exception.BookNotFoundException;
import com.mybookstore.bookservice.exception.DuplicateIsbnException;
import com.mybookstore.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new BookNotFoundException("Buch mit ID " + id + " nicht gefunden"));
    }

    public BookDTO getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(this::convertToDTO)
                .orElseThrow(() -> new BookNotFoundException("Buch mit ISBN " + isbn + " nicht gefunden"));
    }

    public List<BookDTO> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getBooksByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getBooksCheaperThan(Double maxPrice) {
        return bookRepository.findBooksCheaperThan(maxPrice).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> getAvailableBooks() {
        return bookRepository.findByInStockTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findByIsbn(bookDTO.getIsbn());
        if (existingBook.isPresent()) {
            throw new DuplicateIsbnException("Buch mit ISBN " + bookDTO.getIsbn() + " existiert bereits");
        }

        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        log.info("Buch erstellt: {} mit ID: {}", savedBook.getTitle(), savedBook.getId());
        return convertToDTO(savedBook);
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Buch mit ID " + id + " nicht gefunden"));

        // Überprüfen, ob die neue ISBN bereits existiert (falls geändert)
        if (!existingBook.getIsbn().equals(bookDTO.getIsbn())) {
            bookRepository.findByIsbn(bookDTO.getIsbn()).ifPresent(book -> {
                throw new DuplicateIsbnException("Buch mit ISBN " + bookDTO.getIsbn() + " existiert bereits");
            });
        }

        // ID beibehalten
        bookDTO.setId(id);
        BeanUtils.copyProperties(bookDTO, existingBook, "id");

        Book updatedBook = bookRepository.save(existingBook);
        log.info("Buch aktualisiert: {} mit ID: {}", updatedBook.getTitle(), updatedBook.getId());
        return convertToDTO(updatedBook);
    }

    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Buch mit ID " + id + " nicht gefunden");
        }
        bookRepository.deleteById(id);
        log.info("Buch mit ID: {} gelöscht", id);
    }

    // Hilfsmethoden für Konvertierung
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        return bookDTO;
    }

    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return book;
    }
}
