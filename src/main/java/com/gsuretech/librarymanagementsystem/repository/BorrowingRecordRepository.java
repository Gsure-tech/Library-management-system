package com.gsuretech.librarymanagementsystem.repository;

import com.gsuretech.librarymanagementsystem.entity.Book;
import com.gsuretech.librarymanagementsystem.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
}
