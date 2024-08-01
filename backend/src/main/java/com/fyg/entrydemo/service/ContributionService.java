package com.fyg.entrydemo.service;

import com.fyg.entrydemo.model.Contribution;

import java.util.List;

public interface ContributionService {
    List<Contribution> getAllContributions();
    Contribution getContributionById(Long id);
    Contribution createContribution(Contribution entry);
    Contribution updateContribution(Long id, Contribution entry);
    void deleteContribution(Long id);
}
