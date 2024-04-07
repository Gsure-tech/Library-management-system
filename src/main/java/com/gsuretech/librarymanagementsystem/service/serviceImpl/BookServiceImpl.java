package com.gsuretech.librarymanagementsystem.service.serviceImpl;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.exceptions.BookNotFoundException;
import com.gsuretech.librarymanagementsystem.repository.BookRepository;
import com.gsuretech.librarymanagementsystem.service.BookService;
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
public class BookServiceImpl implements BookService {

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
    public BookDto updateBook(Long bookId, BookDto bookDto) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            log.debug("The Book with ID: {} was not found", bookId);
            throw new BookNotFoundException(String.format("The Book with ID: %s was not found!", bookId));
        }
        Book bookToUpdate = optionalBook.get();
        bookToUpdate.setTitle(bookDto.getTitle());
        bookToUpdate.setAuthor(bookDto.getAuthor());
        bookToUpdate.setPublicationYear(bookDto.getPublicationYear());
        bookToUpdate.setIsbn(bookDto.getIsbn());
        bookRepository.save(bookToUpdate);
        return modelMapper.map(bookToUpdate, BookDto.class);
    }

    @Override
    public void deleteBook(Long bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            log.debug("The Book with ID: {} was not found", bookId);
            throw new BookNotFoundException(String.format("The Book with ID: %s was not found!", bookId));
        }
        bookRepository.delete(optionalBook.get());
    }

    @Override
    public List<BookDto> getAllBooks() {
       List<Book> bookList = bookRepository.findAll();
       List<BookDto> booksToReturn;
       booksToReturn = bookList.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return booksToReturn;
    }

    @Override
    public BookDto getBookById(Long bookId) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            log.debug("The Book with ID: {} was not found", bookId);
            throw new BookNotFoundException(String.format("The Book with ID: %s was not found!", bookId));
        }
        return modelMapper.map(optionalBook, BookDto.class);
    }


}
