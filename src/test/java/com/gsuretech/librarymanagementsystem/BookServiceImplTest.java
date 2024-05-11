package com.gsuretech.librarymanagementsystem;

import com.gsuretech.librarymanagementsystem.dto.BookDto;
import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.exceptions.BookNotFoundException;
import com.gsuretech.librarymanagementsystem.repository.BookRepository;
import com.gsuretech.librarymanagementsystem.service.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void addBook_Success() {
        BookDto bookDto = new BookDto();
        Book book = new Book();
        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto result = bookService.addBook(bookDto);

        assertNotNull(result);
        verify(bookRepository).save(book);
    }

    @Test
    void updateBook_Success() throws BookNotFoundException {
        Long bookId = 1L;
        BookDto bookDto = new BookDto();
        bookDto.setTitle("New Title");
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Old Title");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto updatedBook = bookService.updateBook(bookId, bookDto);

        assertEquals("New Title", updatedBook.getTitle());
        verify(bookRepository).save(book);
    }

    @Test
    void updateBook_NotFound() {
        Long bookId = 1L;
        BookDto bookDto = new BookDto();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(bookId, bookDto));
    }

    @Test
    void deleteBook_Success() throws BookNotFoundException {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);

        assertDoesNotThrow(() -> bookService.deleteBook(bookId));
        verify(bookRepository).delete(book);
    }

    @Test
    void deleteBook_NotFound() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId));
    }

    @Test
    void getAllBooks_Success() {
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> books = Arrays.asList(book1, book2);
        BookDto bookDto1 = new BookDto();
        BookDto bookDto2 = new BookDto();

        when(bookRepository.findAll()).thenReturn(books);
        when(modelMapper.map(book1, BookDto.class)).thenReturn(bookDto1);
        when(modelMapper.map(book2, BookDto.class)).thenReturn(bookDto2);

        List<BookDto> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository).findAll();
    }

    @Test
    void getBookById_Success() throws BookNotFoundException {
        Long bookId = 1L;
        Book book = new Book();
        BookDto bookDto = new BookDto();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto result = bookService.getBookById(bookId);

        assertNotNull(result);
        verify(bookRepository).findById(bookId);
    }

    @Test
    void getBookById_NotFound() {
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(bookId));
    }
}
