package com.hospitalizationmanagement.domainmodel;

public class Nurse {
    private int nurseID;
    private String name;
    private String surname;
    public Nurse(int nurseID, String name, String surname){
        this.nurseID = nurseID;
        this.name = name;
        this.surname = surname;
    }

    public void setNurseID(int nurseID){
        this.nurseID = nurseID;
    }
    public int getNurseID(){
        return nurseID;
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
