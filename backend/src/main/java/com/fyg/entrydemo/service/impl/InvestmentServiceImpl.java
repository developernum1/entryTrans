package com.fyg.entrydemo.service.impl;

import com.fyg.entrydemo.model.Investment;
import com.fyg.entrydemo.repository.InvestmentRepository;
import com.fyg.entrydemo.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentRepository repository;

    @Override
    public List<Investment> getAllInvestments() {
        return repository.findAll();
    }

    @Override
    public Investment getInvestmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Investment createInvestment(Investment investment) {
        return repository.save(investment);
    }

    @Override
    public Investment updateInvestment(Long id, Investment investment) {
        if (repository.existsById(id)) {
            investment.setId(id);
            return repository.save(investment);
        }
        return null;
    }

    @Override
    public void deleteInvestment(Long id) {
        repository.deleteById(id);
    }
}
