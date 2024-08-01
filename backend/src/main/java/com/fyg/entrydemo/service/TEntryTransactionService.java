package com.fyg.entrydemo.service;

import com.fyg.entrydemo.model.TEntryTransaction;
import java.util.List;

public interface TEntryTransactionService {
    List<TEntryTransaction> getAllTransactions();
    TEntryTransaction getTransactionById(Long id);
    TEntryTransaction createTransaction(TEntryTransaction transaction);
    TEntryTransaction updateTransaction(Long id, TEntryTransaction transaction);
    void deleteTransaction(Long id);
}
