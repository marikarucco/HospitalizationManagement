package com.hospitalizationmanagement.domainmodel;

public class Doctor {
    private int doctorID;
    private String name;
    private String surname;
    public Doctor( int doctorID){
        this.doctorID = doctorID;
    }
    public Doctor( int doctorID, String name, String surname){
        this.doctorID = doctorID;
        this.name = name;
        this.surname = surname;
    }
    public void setDoctorID(int doctorID){
        this.doctorID = doctorID;
    }
    public int getDoctorID(){
        return doctorID;
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

}
