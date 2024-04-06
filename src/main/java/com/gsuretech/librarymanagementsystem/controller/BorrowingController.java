package com.gsuretech.librarymanagementsystem.controller;

import com.gsuretech.librarymanagementsystem.exceptions.BorrowingException;
import com.gsuretech.librarymanagementsystem.service.BorrowingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            borrowingService.borrowBook(bookId, patronId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BorrowingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            borrowingService.returnBook(bookId, patronId);
            return ResponseEntity.ok().build();
        } catch (BorrowingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}