package com.fyg.entrydemo.service.impl;

import com.fyg.entrydemo.model.TEntryTransaction;
import com.fyg.entrydemo.repository.TEntryTransactionRepository;
import com.fyg.entrydemo.service.TEntryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TEntryTransactionServiceImpl implements TEntryTransactionService {

    @Autowired
    private TEntryTransactionRepository repository;

    @Override
    public List<TEntryTransaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public TEntryTransaction getTransactionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TEntryTransaction createTransaction(TEntryTransaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public TEntryTransaction updateTransaction(Long id, TEntryTransaction transaction) {
        if (repository.existsById(id)) {
            // Set the id using the setter method
            transaction.setId(id);
            return repository.save(transaction);
        }
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }
}
