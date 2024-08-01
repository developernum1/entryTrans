package com.fyg.entrydemo.service.impl;

import com.fyg.entrydemo.model.BasicBankEntry;
import com.fyg.entrydemo.repository.BasicBankEntryRepository;
import com.fyg.entrydemo.service.BasicBankEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicBankEntryServiceImpl implements BasicBankEntryService {

    @Autowired
    private BasicBankEntryRepository repository;

    @Override
    public List<BasicBankEntry> getAllBasicBankEntries() {
        return repository.findAll();
    }

    @Override
    public BasicBankEntry getBasicBankEntryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BasicBankEntry createBasicBankEntry(BasicBankEntry entry) {
        return repository.save(entry);
    }

    @Override
    public BasicBankEntry updateBasicBankEntry(Long id, BasicBankEntry entry) {
        if (repository.existsById(id)) {
            entry.setId(id);
            return repository.save(entry);
        }
        return null;
    }

    @Override
    public void deleteBasicBankEntry(Long id) {
        repository.deleteById(id);
    }
}
