package com.fyg.entrydemo.service;

import com.fyg.entrydemo.model.Dividend;

import java.util.List;

public interface DividendService {
    List<Dividend> getAllDividends();
    Dividend getDividendById(Long id);
    Dividend createDividend(Dividend entry);
    Dividend updateDividend(Long id, Dividend entry);
    void deleteDividend(Long id);
}
