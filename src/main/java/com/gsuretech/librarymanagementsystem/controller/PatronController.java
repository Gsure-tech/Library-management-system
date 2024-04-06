package com.gsuretech.librarymanagementsystem.controller;


import com.gsuretech.librarymanagementsystem.service.PatronService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<PatronDto> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDto> getPatronById(@PathVariable Long id) {
        try {
            PatronDto patron = patronService.getPatronById(id);
            return ResponseEntity.ok(patron);
        } catch (PatronException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<PatronDto> addPatron(@RequestBody PatronDto patronDto) {
        PatronDto newPatron = patronService.addPatron(patronDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDto> updatePatron(@PathVariable Long id, @RequestBody PatronDto patronDto) {
        try {
            PatronDto updatedPatron = patronService.updatePatron(id, patronDto);
            return ResponseEntity.ok(updatedPatron);
        } catch (PatronException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        try {
            patronService.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (PatronException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
