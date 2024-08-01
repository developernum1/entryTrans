package com.fyg.entrydemo.repository;

import com.fyg.entrydemo.model.TEntryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TEntryTransactionRepository extends JpaRepository<TEntryTransaction, Long> {
}

