package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.Dividend;
import com.fyg.entrydemo.service.DividendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dividend")
public class DividendController {

    @Autowired
    private DividendService service;

    @GetMapping
    public List<Dividend> getAllDividends() {
        return service.getAllDividends();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dividend> getDividendById(@PathVariable Long id) {
        Dividend entry = service.getDividendById(id);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Dividend createDividend(@RequestBody Dividend entry) {
        return service.createDividend(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dividend> updateDividend(@PathVariable Long id, @RequestBody Dividend entry) {
        Dividend updatedEntry = service.updateDividend(id, entry);
        if (updatedEntry != null) {
            return ResponseEntity.ok(updatedEntry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDividend(@PathVariable Long id) {
        service.deleteDividend(id);
        return ResponseEntity.noContent().build();
    }
}
