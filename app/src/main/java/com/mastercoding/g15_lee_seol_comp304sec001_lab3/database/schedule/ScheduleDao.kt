package com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM airline_schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>

    @Query("SELECT * FROM airline_schedule WHERE airline_name=:airlineName ORDER BY arrival_time ASC")
    fun getByAirlineName(airlineName: String): Flow<List<Schedule>>
}