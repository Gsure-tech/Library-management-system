package com.gsuretech.librarymanagementsystem.service;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.exceptions.BookException;

import java.util.List;

public interface LibraryService {

    public List<BookDto> getAllBooks();
    public BookDto getBookById(Long id) throws BookException;
    public BookDto addBook(BookDto bookDto);
}
