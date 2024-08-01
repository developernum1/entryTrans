package com.fyg.entrydemo.controller;

import com.fyg.entrydemo.model.TEntryType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class EntryTypeController {

    @GetMapping("/entry-types")
    public ResponseEntity<List<String>> getEntryTypes() {
        List<String> entryTypes = Arrays.stream(TEntryType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(entryTypes);
    }
}
