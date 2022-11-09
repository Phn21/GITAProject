package com.example.day11last.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Info1Dao {

    @Query("SELECT * From Info1")
    List<Info1> getAll();

    @Query("SELECT * From Info1 Where BloodType =:bloodType")
    List<Info1> getInfoByBloodType(String bloodType);

    @Insert
    void insert(Info1 info1);

    @Delete
    void delete(Info1 info1);

    @Update
    void update(Info1 info1);

}

