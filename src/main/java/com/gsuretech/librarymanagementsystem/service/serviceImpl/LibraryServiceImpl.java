package com.gsuretech.librarymanagementsystem.service.serviceImpl;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.exceptions.BookException;
import com.gsuretech.librarymanagementsystem.repository.BookRepository;
import com.gsuretech.librarymanagementsystem.service.LibraryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Logger log = LogManager.getLogger(getClass());

    @Override
    public BookDto addBook(BookDto bookDto) {

        Book book = modelMapper.map(bookDto, Book.class);
        bookRepository.save(book);
        return bookDto;
    }
    @Override
    public List<BookDto> getAllBooks() {
       List<Book> bookList = bookRepository.findAll();
       List<BookDto> booksToReturn;
       booksToReturn = bookList.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return booksToReturn;
    }

    @Override
    public BookDto getBookById(Long id) throws BookException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            log.debug("The Book with ID: {} was not found", id);
            throw new BookException(String.format("The Book with ID: %s was not found!", id));
        }
        return modelMapper.map(optionalBook, BookDto.class);
    }


}
