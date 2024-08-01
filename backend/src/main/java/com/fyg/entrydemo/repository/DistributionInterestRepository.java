package com.fyg.entrydemo.repository;

import com.fyg.entrydemo.model.DistributionInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionInterestRepository extends JpaRepository<DistributionInterest, Long> {
}
