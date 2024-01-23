package com.hospitalizationmanagement.dao;
import com.hospitalizationmanagement.domainmodel.Patient;

import java.util.ArrayList;

public interface PatientDao {
    ArrayList<Patient> getAllPatients();
    Patient getPatientByID(int patientID);
    void savePatient(Patient p);
    void deletePatient(int patientID);
}


