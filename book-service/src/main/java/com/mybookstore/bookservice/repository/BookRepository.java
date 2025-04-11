package com.mybookstore.bookservice.repository;

import com.mybookstore.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByGenreIgnoreCase(String genre);

    @Query("SELECT b FROM Book b WHERE b.price <= :maxPrice")
    List<Book> findBooksCheaperThan(@Param("maxPrice") Double maxPrice);

    List<Book> findByInStockTrue();
}
