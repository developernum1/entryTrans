package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.Contribution;
import com.fyg.entrydemo.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contribution")
public class ContributionController {

    @Autowired
    private ContributionService service;

    @GetMapping
    public List<Contribution> getAllContributions() {
        return service.getAllContributions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contribution> getContributionById(@PathVariable Long id) {
        Contribution entry = service.getContributionById(id);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Contribution createContribution(@RequestBody Contribution entry) {
        return service.createContribution(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contribution> updateContribution(@PathVariable Long id, @RequestBody Contribution entry) {
        Contribution updatedEntry = service.updateContribution(id, entry);
        if (updatedEntry != null) {
            return ResponseEntity.ok(updatedEntry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContribution(@PathVariable Long id) {
        service.deleteContribution(id);
        return ResponseEntity.noContent().build();
    }
}
