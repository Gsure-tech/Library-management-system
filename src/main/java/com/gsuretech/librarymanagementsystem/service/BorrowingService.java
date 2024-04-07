package com.gsuretech.librarymanagementsystem.service;

import com.gsuretech.librarymanagementsystem.exceptions.BorrowingException;

public interface BorrowingService {
    public void borrowBook(Long bookId, Long patronId) throws BorrowingException;
    public void returnBook(Long bookId, Long patronId) throws BorrowingException;
}
