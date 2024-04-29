package com.mastercoding.g15_lee_seol_comp304sec001_lab3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule.Schedule
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule.ScheduleDao

@Database(entities = [Schedule::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/airline_schedule.db")
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }
}