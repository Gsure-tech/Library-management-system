package com.gsuretech.librarymanagementsystem.controller;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final LibraryService libraryService;

    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book) {
        BookDto newBook = libraryService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return libraryService.getAllBooks();
    }

}
