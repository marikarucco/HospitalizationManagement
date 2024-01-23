package com.hospitalizationmanagement.dao;

import com.hospitalizationmanagement.domainmodel.*;

import java.sql.*;
import java.util.ArrayList;

public class HospitalizationRecordDaoImpl implements HospitalizationRecordDao {
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
    public ArrayList<HospitalizationRecord> getAllRecords() {
        ArrayList<HospitalizationRecord> records = new ArrayList<>();
        connect();
        String sql = "SELECT * from hospitalization_record";
        try{
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                HospitalizationRecord hr = new HospitalizationRecord();
                hr.setRecordID(rs.getInt(1));
                hr.setPatient(new Patient(rs.getInt(2)));
                records.add(hr);
            }
        }catch(SQLException e){
            System.out.println("Failed to get list of hospitalization records");
        }
        return records;
    }

    @Override
    public HospitalizationRecord getRecordByID(int recordID) {
        HospitalizationRecord hr = new HospitalizationRecord();
        connect();
        String sql = "SELECT * from hospitalization_record where record_id=?";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, recordID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            hr.setRecordID(recordID);
            hr.setPatient(new Patient(rs.getInt(2)));
        }catch(SQLException e){
            System.out.println("Failed to get patient by id");
        }
        return hr;
    }

    @Override
    public void saveRecord(HospitalizationRecord hr) {
        connect();
        String sql = "INSERT INTO hospitalization_record(record_id, patient_id) VALUES (?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, hr.getRecordID());
            statement.setInt(2, hr.getPatient().getPatientID());
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failed to add new hospitalization record to db");
        }
    }

    @Override
    public void deleteRecord(int recordID) {
        connect();
        String sql = "DELETE from hospitalization_record where record_id=?";
        try{
            PreparedStatement statement= con.prepareStatement(sql);
            statement.setInt(1, recordID);
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Failed to remove hospitalization record from db");
        }

    }


}

