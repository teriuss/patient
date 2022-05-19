package com.example.doctors.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.doctors.models.Patient;

import java.util.List;

@Dao
public interface PatientDAO {

    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Insert
    void insertPatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

}
