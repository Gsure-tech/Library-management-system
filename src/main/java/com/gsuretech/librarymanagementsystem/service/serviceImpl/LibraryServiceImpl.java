package com.gsuretech.librarymanagementsystem.service.serviceImpl;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.repository.BookRepository;
import com.gsuretech.librarymanagementsystem.service.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto addBook(BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
        return bookDto;
    }
    @Override
    public List<BookDto> getAllBooks() {
        return null;
    }

    @Override
    public BookDto getBookById(Long id) {
        return null;
    }


}
