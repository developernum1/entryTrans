package com.fyg.entrydemo.service.impl;

import com.fyg.entrydemo.model.Dividend;
import com.fyg.entrydemo.repository.DividendRepository;
import com.fyg.entrydemo.service.DividendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DividendServiceImpl implements DividendService {

    @Autowired
    private DividendRepository repository;

    @Override
    public List<Dividend> getAllDividends() {
        return repository.findAll();
    }

    @Override
    public Dividend getDividendById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Dividend createDividend(Dividend entry) {
        return repository.save(entry);
    }

    @Override
    public Dividend updateDividend(Long id, Dividend entry) {
        if (repository.existsById(id)) {
            entry.setId(id);
            return repository.save(entry);
        }
        return null;
    }

    @Override
    public void deleteDividend(Long id) {
        repository.deleteById(id);
    }
}
