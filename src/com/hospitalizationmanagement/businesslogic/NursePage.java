package com.hospitalizationmanagement.businesslogic;
import com.hospitalizationmanagement.domainmodel.Bed;
import com.hospitalizationmanagement.domainmodel.Patient;
import com.hospitalizationmanagement.dao.PatientDao;
import com.hospitalizationmanagement.dao.PatientDaoImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NursePage {
    private ArrayList<Bed> beds = new ArrayList<>();
    private PatientDao pd;
    public NursePage(int numBeds){
        pd = new PatientDaoImpl();
        for(int i=1; i<numBeds+1; i++){
            beds.add(new Bed(i));
        }
    }

    public Bed getAvailableBed(){
        Bed b = null;
        for(Bed item : beds){
            if(item.isAvailable()) {
                b = item;
                break;
            }
        }
        return b;
    }
    public void assignBed(Patient p){
        Bed b = getAvailableBed();
        p.setBed(b);
        b.setAvailable(false);
    }
    public void freeBed(Bed b){
        b.setAvailable(true);
    }
    public void savePatient(int patientID, String name, String surname, LocalDate birthDate, String gender) throws SQLException {
        Patient p = new Patient(patientID, name, surname, birthDate, gender, null);
        assignBed(p);
        pd.savePatient(p);
    }
    public void deletePatient(Patient p) throws SQLException{
        pd.deletePatient(p.getPatientID());
    }
    public ArrayList<Bed> getBeds(){
        return beds;
    }

}
