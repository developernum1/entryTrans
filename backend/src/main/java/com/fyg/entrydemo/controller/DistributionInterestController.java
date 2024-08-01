package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.DistributionInterest;
import com.fyg.entrydemo.service.DistributionInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributionInterest")
public class DistributionInterestController {

    @Autowired
    private DistributionInterestService service;

    @GetMapping
    public List<DistributionInterest> getAllDistributionInterests() {
        return service.getAllDistributionInterests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistributionInterest> getDistributionInterestById(@PathVariable Long id) {
        DistributionInterest entry = service.getDistributionInterestById(id);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public DistributionInterest createDistributionInterest(@RequestBody DistributionInterest entry) {
        return service.createDistributionInterest(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistributionInterest> updateDistributionInterest(@PathVariable Long id, @RequestBody DistributionInterest entry) {
        DistributionInterest updatedEntry = service.updateDistributionInterest(id, entry);
        if (updatedEntry != null) {
            return ResponseEntity.ok(updatedEntry);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistributionInterest(@PathVariable Long id) {
        service.deleteDistributionInterest(id);
        return ResponseEntity.noContent().build();
    }
}
