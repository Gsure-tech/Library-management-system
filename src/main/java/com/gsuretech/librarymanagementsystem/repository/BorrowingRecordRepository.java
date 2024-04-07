package com.gsuretech.librarymanagementsystem.repository;

import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.entity.BorrowingRecord;
import com.gsuretech.librarymanagementsystem.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    List<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
}
