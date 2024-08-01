package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.TEntryTransaction;
import com.fyg.entrydemo.service.TEntryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TEntryTransactionController {

    @Autowired
    private TEntryTransactionService service;

    @GetMapping
    public List<TEntryTransaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TEntryTransaction> getTransactionById(@PathVariable Long id) {
        TEntryTransaction transaction = service.getTransactionById(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public TEntryTransaction createTransaction(@RequestBody TEntryTransaction transaction) {
        return service.createTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TEntryTransaction> updateTransaction(@PathVariable Long id, @RequestBody TEntryTransaction transaction) {
        TEntryTransaction updatedTransaction = service.updateTransaction(id, transaction);
        if (updatedTransaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        service.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
