package com.hospitalizationmanagement.domainmodel;


import java.time.LocalDate;

public class Patient {
    private int patientID;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gender;
    private Bed bed;
    private HospitalizationRecord hospitalizationRecord;

    public Patient(){

    }
    public Patient (int patientID){
        this.patientID = patientID;
    }
    public Patient(int patientID, String name, String surname, LocalDate birthDate, String gender, Bed bed){
        this.patientID = patientID;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bed = bed;
    }
    public void setPatientID(int patientID){
        this.patientID = patientID;
    }
    public int getPatientID(){
        return patientID;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }
    public void setBirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return gender;
    }
    public void setBed(Bed bed){
        this.bed = bed;
    }
    public Bed getBed(){
        return bed;
    }
    public void setHospitalizationRecord(HospitalizationRecord hospitalizationRecord){
        this.hospitalizationRecord = hospitalizationRecord;
    }
    public HospitalizationRecord getHospitalizationRecord(){
        return hospitalizationRecord;
    }


}
