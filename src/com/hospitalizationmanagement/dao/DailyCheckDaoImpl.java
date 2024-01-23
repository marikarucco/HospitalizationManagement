package com.hospitalizationmanagement.dao;

import com.hospitalizationmanagement.domainmodel.DailyCheck;
import com.hospitalizationmanagement.domainmodel.Doctor;
import com.hospitalizationmanagement.domainmodel.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DailyCheckDaoImpl implements DailyCheckDao {
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
        public ArrayList<DailyCheck> getAllRecords(Patient p) {
            ArrayList<DailyCheck> checks = new ArrayList<>();
            connect();
            int patientID = p.getPatientID();
            String sql = "SELECT * from daily_check where patient_id=?";
            try{
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, patientID);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    DailyCheck dc = new DailyCheck();
                    dc.setDoctor(new Doctor(rs.getInt(2)));
                    dc.setPatient(new Patient(rs.getInt(3)));
                    dc.setDate(rs.getObject(4, LocalDate.class));
                    dc.setTemperature(rs.getInt(5));
                    dc.setOxygenSaturation(rs.getInt(6));
                    dc.setDiastolicPressure(rs.getInt(7));
                    dc.setSystolicPressure(rs.getInt(8));
                    dc.setHeartRate(rs.getInt(9));
                    dc.setSymptoms(rs.getString(10));
                    checks.add(dc);
                }
            }catch(SQLException e){
                System.out.println("Failed to get list of daily checks");
            }
            return checks;
        }

        @Override
        public DailyCheck getRecordByID(int recordID) {
            DailyCheck dc = new DailyCheck();
            connect();
            String sql = "SELECT * from daily_check where daily_check_id=?";
            try{
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, recordID);
                ResultSet rs = statement.executeQuery();
                rs.next();
                dc.setDoctor(new Doctor(rs.getInt(2)));
                dc.setPatient(new Patient(rs.getInt(3)));
                dc.setDate(rs.getObject(4, LocalDate.class));
                dc.setTemperature(rs.getInt(5));
                dc.setOxygenSaturation(rs.getInt(6));
                dc.setDiastolicPressure(rs.getInt(7));
                dc.setSystolicPressure(rs.getInt(8));
                dc.setHeartRate(rs.getInt(9));
                dc.setSymptoms(rs.getString(10));
            }catch(SQLException e){
                System.out.println("Failed to get daily check by id");
            }
            return dc;
        }

        @Override
        public void saveRecord(DailyCheck dc) {
            connect();
            String sql = "INSERT INTO daily_check(daily_check_id, doctor_id, patient_id, date, temperature, oxygen_saturation," +
                    " diastolic_pressure, systolic_pressure, heart_rate, symptoms) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try{
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, dc.getDailyCheckID());
                statement.setInt(2, dc.getDoctor().getDoctorID());
                statement.setInt(3, dc.getPatient().getPatientID());
                statement.setObject(4, dc.getDate());
                statement.setInt(5, dc.getTemperature());
                statement.setInt(6, dc.getOxygenSaturation());
                statement.setInt(7, dc.getDiastolicPressure());
                statement.setInt(8, dc.getSystolicPressure());
                statement.setInt(9, dc.getHeartRate());
                statement.setString(10, dc.getSymptoms());
                statement.executeUpdate();
            }catch(SQLException e){
                System.out.println("Failed to add new daily check to db");
            }
        }

        @Override
        public void deleteRecord(int recordID) {
            connect();
            String sql = "DELETE from daily_check where daily_check_id=?";
            try{
                PreparedStatement statement= con.prepareStatement(sql);
                statement.setInt(1, recordID);
                statement.executeUpdate();
            }catch(SQLException e){
                System.out.println("Failed to remove daily check from db");
            }

        }


}
