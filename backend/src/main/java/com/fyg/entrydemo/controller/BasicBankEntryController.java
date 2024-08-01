package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.BasicBankEntry;
import com.fyg.entrydemo.service.BasicBankEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basicBankEntries")
public class BasicBankEntryController {

    @Autowired
    private BasicBankEntryService service;

    @GetMapping
    public List<BasicBankEntry> getAllBasicBankEntries() {
        return service.getAllBasicBankEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasicBankEntry> getBasicBankEntryById(@PathVariable Long id) {
        BasicBankEntry entry = service.getBasicBankEntryById(id);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entry);
    }

    @PostMapping
    public BasicBankEntry createBasicBankEntry(@RequestBody BasicBankEntry entry) {
        return service.createBasicBankEntry(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BasicBankEntry> updateBasicBankEntry(@PathVariable Long id, @RequestBody BasicBankEntry entry) {
        BasicBankEntry updatedEntry = service.updateBasicBankEntry(id, entry);
        if (updatedEntry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasicBankEntry(@PathVariable Long id) {
        service.deleteBasicBankEntry(id);
        return ResponseEntity.noContent().build();
    }
}
