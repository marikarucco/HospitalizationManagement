package com.hospitalizationmanagement.businesslogic;
import com.hospitalizationmanagement.domainmodel.HospitalizationRecord;
import com.hospitalizationmanagement.domainmodel.Patient;
import com.hospitalizationmanagement.domainmodel.DailyCheck;
import com.hospitalizationmanagement.domainmodel.Doctor;
import com.hospitalizationmanagement.dao.HospitalizationRecordDao;
import com.hospitalizationmanagement.dao.HospitalizationRecordDaoImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;

public class ResidentPage extends Observable {
    private Doctor resident;
    private HospitalizationRecordDao hrd;
    public ResidentPage(int doctorID, String name, String surname){
        resident = new Doctor(doctorID, name, surname);
        hrd = new HospitalizationRecordDaoImpl();
    }
    public void assignHospitalizationRecord(int recordID, Patient p){
        HospitalizationRecord hr = new HospitalizationRecord(recordID);
        hr.setPatient(p);
        p.setHospitalizationRecord(hr);
    }
    public void saveHospitalizationRecord(HospitalizationRecord hr) throws SQLException {
        hrd.saveRecord(hr);
    }
    public void createDailyCheck(int dailyCheckID, Patient patient, LocalDate date, int temperature,
                                 int oxygenSaturation, int diastolicPressure, int systolicPressure,
                                 int heartRate, String symptoms) throws IllegalArgumentException{
        DailyCheck dc = new DailyCheck(dailyCheckID, patient, resident, date, temperature,
                oxygenSaturation, diastolicPressure, systolicPressure, heartRate, symptoms);
        setChanged();
        notifyObservers(dc);
    }
}
