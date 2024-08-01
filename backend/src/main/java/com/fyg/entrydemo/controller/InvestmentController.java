package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.Investment;
import
        com.fyg.entrydemo.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService service;

    @GetMapping
    public List<Investment> getAllInvestments() {
        return service.getAllInvestments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        Investment investment = service.getInvestmentById(id);
        if (investment != null) {
            return ResponseEntity.ok(investment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Investment createInvestment(@RequestBody Investment investment) {
        return service.createInvestment(investment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody Investment investment) {
        Investment updated = service.updateInvestment(id, investment);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        service.deleteInvestment(id);
        return ResponseEntity.noContent().build();
    }
}
