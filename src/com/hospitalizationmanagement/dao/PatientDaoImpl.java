package com.hospitalizationmanagement.dao;

import com.hospitalizationmanagement.domainmodel.HospitalizationRecord;
import com.hospitalizationmanagement.domainmodel.Patient;
import com.hospitalizationmanagement.domainmodel.Bed;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class PatientDaoImpl implements PatientDao {
    Connection con = null;

    public void connect(){
        String url = "jdbc:postgresql://localhost:5432/HospitalizationManagement";
        String user = "postgres";
        String password = "marikarucco";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch(SQLException e){
            System.out.println("Connection failed");
        }
    }

    @Override
    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        connect();
        String sql = "SELECT * from patient";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Patient patient = new Patient();
                patient.setPatientID(rs.getInt(1));
                patient.setName(rs.getString(2));
                patient.setSurname(rs.getString(3));
                patient.setBirthDate(rs.getObject(4, LocalDate.class));
                patient.setGender(rs.getString(5));
                patient.setBed(new Bed(rs.getInt(6)));
                patients.add(patient);
            }
        }catch(SQLException e){
            System.out.println("Failed to get list of patients");
        }
        return patients;
    }

    @Override
    public Patient getPatientByID(int patientID) {
        Patient patient = new Patient();
        connect();
        String sql = "SELECT * from patient where patient_id=?";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, patientID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            patient.setPatientID(patientID);
            patient.setName(rs.getString(2));
            patient.setSurname(rs.getString(3));
            patient.setBirthDate(rs.getObject(4, LocalDate.class));
            patient.setGender(rs.getString(5));
            patient.setBed(new Bed(rs.getInt(6)));
        }catch(SQLException e){
           System.out.println("Failed to get patient by id");
        }
        return patient;
    }

    @Override
    public void savePatient(Patient p) {
        connect();
        String sql = "INSERT INTO patient(patient_id, name, surname, birth_date, gender, bed_id) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, p.getPatientID());
            statement.setString(2, p.getName());
            statement.setString(3, p.getSurname());
            statement.setObject(4, p.getBirthDate());
            statement.setString(5, p.getGender());
            statement.setInt(6, p.getBed().getBedID());
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failed to add new patient to db");
        }
    }

    @Override
    public void deletePatient(int patientID) {
        connect();
        String sql = "DELETE from patient where patient_id=?";
        try{
           PreparedStatement statement= con.prepareStatement(sql);
           statement.setInt(1, patientID);
           statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failed to remove patient from db");
        }

    }
}
