package com.fyg.entrydemo.service.impl;

import com.fyg.entrydemo.model.DistributionInterest;
import com.fyg.entrydemo.repository.DistributionInterestRepository;
import com.fyg.entrydemo.service.DistributionInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionInterestServiceImpl implements DistributionInterestService {

    @Autowired
    private DistributionInterestRepository repository;

    @Override
    public List<DistributionInterest> getAllDistributionInterests() {
        return repository.findAll();
    }

    @Override
    public DistributionInterest getDistributionInterestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public DistributionInterest createDistributionInterest(DistributionInterest entry) {
        return repository.save(entry);
    }

    @Override
    public DistributionInterest updateDistributionInterest(Long id, DistributionInterest entry) {
        if (repository.existsById(id)) {
            entry.setId(id);
            return repository.save(entry);
        }
        return null;
    }

    @Override
    public void deleteDistributionInterest(Long id) {
        repository.deleteById(id);
    }
}
