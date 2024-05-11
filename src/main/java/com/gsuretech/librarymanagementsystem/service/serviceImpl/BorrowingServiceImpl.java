package com.gsuretech.librarymanagementsystem.service.serviceImpl;

import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.entity.BorrowingRecord;
import com.gsuretech.librarymanagementsystem.entity.Patron;
import com.gsuretech.librarymanagementsystem.exceptions.BorrowingException;
import com.gsuretech.librarymanagementsystem.repository.BookRepository;
import com.gsuretech.librarymanagementsystem.repository.BorrowingRecordRepository;
import com.gsuretech.librarymanagementsystem.repository.PatronRepository;
import com.gsuretech.librarymanagementsystem.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Override
    @Transactional
    public void borrowBook(Long bookId, Long patronId) throws BorrowingException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);
        if (!optionalBook.isPresent() || !optionalPatron.isPresent()) {
            throw new BorrowingException("Book or Patron not found");
        }
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(optionalBook.get());
        borrowingRecord.setPatron(optionalPatron.get());
        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    @Transactional
    public void returnBook(Long bookId, Long patronId) throws BorrowingException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);
        if (!optionalBook.isPresent() || !optionalPatron.isPresent()) {
            throw new BorrowingException("Book or Patron not found");
        }
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(optionalBook.get(), optionalPatron.get());
        if (borrowingRecords.isEmpty()) {
            throw new BorrowingException("No borrowing record found");
        }
        BorrowingRecord borrowingRecord = borrowingRecords.get(0);
        borrowingRecord.setReturnDate(LocalDate.now());
        borrowingRecordRepository.save(borrowingRecord);
    }
}
