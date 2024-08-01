package com.fyg.entrydemo.service.impl;

import com.fyg.entrydemo.model.Contribution;
import com.fyg.entrydemo.repository.ContributionRepository;
import com.fyg.entrydemo.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    private ContributionRepository repository;

    @Override
    public List<Contribution> getAllContributions() {
        return repository.findAll();
    }

    @Override
    public Contribution getContributionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Contribution createContribution(Contribution contribution) {
        return repository.save(contribution);
    }

    @Override
    public Contribution updateContribution(Long id, Contribution contribution) {
        if (repository.existsById(id)) {
            contribution.setId(id);
            return repository.save(contribution);
        }
        return null;
    }

    @Override
    public void deleteContribution(Long id) {
        repository.deleteById(id);
    }
}
