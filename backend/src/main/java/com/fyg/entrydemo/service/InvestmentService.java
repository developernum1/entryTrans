package com.fyg.entrydemo.service;

import com.fyg.entrydemo.model.Investment;

import java.util.List;

public interface InvestmentService {
    List<Investment> getAllInvestments();
    Investment getInvestmentById(Long id);
    Investment createInvestment(Investment investment);
    Investment updateInvestment(Long id, Investment investment);
    void deleteInvestment(Long id);
}
