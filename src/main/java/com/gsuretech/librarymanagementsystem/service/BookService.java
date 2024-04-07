package com.gsuretech.librarymanagementsystem.service;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {

    public List<BookDto> getAllBooks();
    public BookDto getBookById(Long bookId) throws BookNotFoundException;
    public BookDto addBook(BookDto bookDto);
    public BookDto updateBook( Long bookId, BookDto bookDto) throws BookNotFoundException;
    public void deleteBook( Long bookId) throws BookNotFoundException;

}
