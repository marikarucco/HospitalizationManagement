package com.hospitalizationmanagement.dao;

import com.hospitalizationmanagement.domainmodel.HospitalizationRecord;

import java.util.ArrayList;

public interface HospitalizationRecordDao {
    ArrayList<HospitalizationRecord> getAllRecords();
    HospitalizationRecord getRecordByID(int recordID);
    void saveRecord(HospitalizationRecord hr);
    void deleteRecord(int recordID);
}

