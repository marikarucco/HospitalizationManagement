package com.hospitalizationmanagement.domainmodel;

import java.util.ArrayList;

public class HospitalizationRecord {
    private int recordID;
    private Patient patient;
    private ArrayList<DailyCheck> checks= new ArrayList<>();;
    public HospitalizationRecord(){
    }
    public HospitalizationRecord(int recordID){
        this.recordID = recordID;
    }
    public HospitalizationRecord(int recordID, Patient patient){
        this.recordID = recordID;
        this.patient = patient;

    }
    public void setRecordID(int recordID){
        this.recordID = recordID;
    }
    public int getRecordID(){
        return recordID;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public Patient getPatient(){
        return patient;
    }
    public void setChecks(ArrayList<DailyCheck> checks){
        this.checks = checks;
    }
    public ArrayList<DailyCheck> getChecks(){
        return checks;
    }
    public void addCheck(DailyCheck dc){
        checks.add(dc);
    }
}
