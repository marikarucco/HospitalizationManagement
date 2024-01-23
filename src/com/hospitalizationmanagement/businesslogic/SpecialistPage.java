package com.hospitalizationmanagement.businesslogic;
import com.hospitalizationmanagement.domainmodel.DailyCheck;
import com.hospitalizationmanagement.domainmodel.Doctor;
import com.hospitalizationmanagement.dao.DailyCheckDao;
import com.hospitalizationmanagement.dao.DailyCheckDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class SpecialistPage implements Observer {
    private Doctor specialist;
    private DailyCheckDao dcd;
    private ArrayList<DailyCheck> checks = new ArrayList<>();

    public SpecialistPage(int doctorID, String name, String surname, Observable obs){
        specialist = new Doctor(doctorID, name, surname);
        dcd = new DailyCheckDaoImpl();
        obs.addObserver(this);
    }

    @Override
    public void update(Observable residentPage, Object dailyCheck) {
        DailyCheck dc = (DailyCheck) dailyCheck;
        checks.add(dc);
    }
    public void confirmCheck(DailyCheck dc){
        dc.getPatient().getHospitalizationRecord().addCheck(dc);
    }
    public void addCheck(DailyCheck dc) throws SQLException {
        dcd.saveRecord(dc);
    }

    public Doctor getSpecialist(){
        return specialist;
    }
    public ArrayList<DailyCheck> getChecks(){
        return checks;
    }
}
