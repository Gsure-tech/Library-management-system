package com.gsuretech.librarymanagementsystem.service;

import com.gsuretech.librarymanagementsystem.dto.PatronDto;
import com.gsuretech.librarymanagementsystem.exceptions.PatronException;

import java.util.List;

public interface PatronService {
    public PatronDto addPatron(PatronDto patronDto);
    public PatronDto updatePatron(Long id, PatronDto patronDto) throws PatronException;
    public void deletePatron(Long id) throws PatronException;
    public PatronDto getPatronById(Long id) throws PatronException;
    public List<PatronDto> getAllPatrons();
}
