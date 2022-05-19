package com.example.doctors.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "f_name")
    private String f_name;

    @ColumnInfo(name = "l_name")
    private String l_name;

    @ColumnInfo(name = "patronymic")
    private String patronymic;

    @ColumnInfo(name = "phone")
    private String phone;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
