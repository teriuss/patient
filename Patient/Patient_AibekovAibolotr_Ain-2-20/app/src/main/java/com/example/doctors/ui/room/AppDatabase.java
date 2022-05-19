package com.example.doctors.ui.room;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.doctors.dao.PatientDAO;
import com.example.doctors.models.Patient;

@Database(entities = {Patient.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PatientDAO patientDAO();

}
