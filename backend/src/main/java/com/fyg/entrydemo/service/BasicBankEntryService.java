package com.fyg.entrydemo.service;

import com.fyg.entrydemo.model.BasicBankEntry;
import java.util.List;

public interface BasicBankEntryService {
    List<BasicBankEntry> getAllBasicBankEntries();
    BasicBankEntry getBasicBankEntryById(Long id);
    BasicBankEntry createBasicBankEntry(BasicBankEntry entry);
    BasicBankEntry updateBasicBankEntry(Long id, BasicBankEntry entry);
    void deleteBasicBankEntry(Long id);
}
