package com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "airline_schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @NonNull @ColumnInfo(name = "airline_name") var airlineName: String,
    @NonNull @ColumnInfo(name = "arrival_time") var arrivalTime: Int,
    @NonNull @ColumnInfo(name = "terminal_number") var terminalNumber: String,
    var status: String?
)
