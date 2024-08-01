package com.fyg.entrydemo.service;

import com.fyg.entrydemo.model.DistributionInterest;

import java.util.List;

public interface DistributionInterestService {
    List<DistributionInterest> getAllDistributionInterests();
    DistributionInterest getDistributionInterestById(Long id);
    DistributionInterest createDistributionInterest(DistributionInterest entry);
    DistributionInterest updateDistributionInterest(Long id, DistributionInterest entry);
    void deleteDistributionInterest(Long id);
}
