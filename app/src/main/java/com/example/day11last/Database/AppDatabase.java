package com.example.day11last.Database;

import androidx.room.Database;

import androidx.room.RoomDatabase;

@Database(entities = {Info1.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Info1Dao info1Dao();
}
