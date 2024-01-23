package com.hospitalizationmanagement.dao;

import com.hospitalizationmanagement.domainmodel.DailyCheck;
import com.hospitalizationmanagement.domainmodel.Patient;

import java.util.ArrayList;

public interface DailyCheckDao {
    ArrayList<DailyCheck> getAllRecords(Patient p);
    DailyCheck getRecordByID(int recordID);
    void saveRecord(DailyCheck dc);
    void deleteRecord(int recordID);
}
