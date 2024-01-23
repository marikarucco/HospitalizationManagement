package com.hospitalizationmanagement.domainmodel;

import java.time.LocalDate;


public class DailyCheck {
    private int dailyCheckID;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private int temperature;
    private int oxygenSaturation;
    private int diastolicPressure;
    private int systolicPressure;
    private int heartRate;
    private String symptoms;
    public DailyCheck(){

    }
    public DailyCheck(int dailyCheckID, Patient patient, Doctor doctor, LocalDate date, int temperature, int oxygenSaturation ,int diastolicPressure, int systolicPressure, int heartRate, String symptoms){
        this.dailyCheckID = dailyCheckID;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.temperature = temperature;
        this.oxygenSaturation = oxygenSaturation;
        this.diastolicPressure = diastolicPressure;
        this.systolicPressure = systolicPressure;
        this.heartRate = heartRate;
        this.symptoms = symptoms;

    }
    public void setDailyCheckID(int dailyCheckID){
        this.dailyCheckID = dailyCheckID;
    }
    public int getDailyCheckID(){
        return dailyCheckID;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public Patient getPatient(){
        return patient;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setTemperature(int temperature){
        if(temperature > 0) {
            this.temperature = temperature;
        } else{
            throw new IllegalArgumentException("Temperature can't be negative or equal to zero");
        }
    }
    public int getTemperature(){
        return temperature;
    }
    public void setOxygenSaturation(int oxygenSaturation){
        if(oxygenSaturation > 0) {
            this.oxygenSaturation = oxygenSaturation;
        } else{
            throw new IllegalArgumentException("Oxygen saturation can't be negative or equal to zero");
        }
    }
    public int getOxygenSaturation(){
        return oxygenSaturation;
    }
    public void setDiastolicPressure(int diastolicPressure){
        if(diastolicPressure > 0) {
            this.diastolicPressure = diastolicPressure;
        } else{
            throw new IllegalArgumentException("Diastolic pressure can't be negative or equal to zero");
        }
    }
    public int getDiastolicPressure(){
        return diastolicPressure;
    }
    public void setSystolicPressure(int systolicPressure){
        if(systolicPressure > 0){
            this.systolicPressure = systolicPressure;
        } else {
            throw new IllegalArgumentException("Systolic pressure can't be negative or equal to zero");
        }
    }
    public int getSystolicPressure(){
        return systolicPressure;
    }
    public void setHeartRate(int heartRate){
        if(heartRate > 0) {
            this.heartRate = heartRate;
        } else{
            throw new IllegalArgumentException("Heart rate can't be negative or equal to zero");
        }
    }
    public int getHeartRate(){
        return heartRate;
    }
    public void setSymptoms(String symptoms){
        this.symptoms = symptoms;
    }
    public String getSymptoms(){
        return symptoms;
    }
}
