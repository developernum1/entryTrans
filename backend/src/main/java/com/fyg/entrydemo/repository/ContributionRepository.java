package com.fyg.entrydemo.repository;

import com.fyg.entrydemo.model.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
}
