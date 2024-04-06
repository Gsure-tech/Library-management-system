package com.gsuretech.librarymanagementsystem.service;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import java.util.List;

public interface LibraryService {

    public List<BookDto> getAllBooks();
    public BookDto getBookById(Long id);
    public BookDto addBook(BookDto bookDto);
}
