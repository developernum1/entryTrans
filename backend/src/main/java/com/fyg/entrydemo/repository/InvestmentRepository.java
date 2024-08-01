package com.fyg.entrydemo.repository;

import com.fyg.entrydemo.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
