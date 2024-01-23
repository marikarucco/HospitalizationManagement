package com.hospitalizationmanagement.domainmodel;

public class Bed {
    private int bedID;
    private boolean available = true;

    public Bed(int bedID){
        this.bedID = bedID;
    }

    public void setBedID(int bedID){
        this.bedID = bedID;
    }

    public int getBedID(){
        return bedID;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }

    public boolean isAvailable(){
        return available;
    }

}
