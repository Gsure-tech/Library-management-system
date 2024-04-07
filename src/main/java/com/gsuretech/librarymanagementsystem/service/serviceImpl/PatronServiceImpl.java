package com.gsuretech.librarymanagementsystem.service.serviceImpl;

import com.gsuretech.librarymanagementsystem.dto.PatronDto;
import com.gsuretech.librarymanagementsystem.entity.Patron;
import com.gsuretech.librarymanagementsystem.exceptions.PatronException;
import com.gsuretech.librarymanagementsystem.repository.PatronRepository;
import com.gsuretech.librarymanagementsystem.service.PatronService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronServiceImpl implements PatronService {
    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatronDto> getAllPatrons() {
        List<Patron> patronList = patronRepository.findAll();
        return patronList.stream()
                .map(patron -> modelMapper.map(patron, PatronDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatronDto getPatronById(Long id) throws PatronException {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (!optionalPatron.isPresent()) {
            throw new PatronException(String.format("Patron with ID %s not found", id));
        }
        return modelMapper.map(optionalPatron.get(), PatronDto.class);
    }

    @Override
    public PatronDto addPatron(PatronDto patronDto) {
        Patron patron = modelMapper.map(patronDto, Patron.class);
        patronRepository.save(patron);
        return patronDto;
    }

    @Override
    public PatronDto updatePatron(Long id, PatronDto patronDto) throws PatronException {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (!optionalPatron.isPresent()) {
            throw new PatronException(String.format("Patron with ID %s not found", id));
        }
        Patron patronToUpdate = optionalPatron.get();
        patronToUpdate.setName(patronDto.getName());
        patronToUpdate.setContactInformation(patronDto.getContactInformation());
        // Update other fields as needed
        patronRepository.save(patronToUpdate);
        return patronDto;
    }

    @Override
    public void deletePatron(Long id) throws PatronException {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (!optionalPatron.isPresent()) {
            throw new PatronException(String.format("Patron with ID %s not found", id));
        }
        patronRepository.delete(optionalPatron.get());
    }
}
