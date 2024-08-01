package com.fyg.entrydemo.repository;

import com.fyg.entrydemo.model.BasicBankEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasicBankEntryRepository extends JpaRepository<BasicBankEntry, Long> {
}
