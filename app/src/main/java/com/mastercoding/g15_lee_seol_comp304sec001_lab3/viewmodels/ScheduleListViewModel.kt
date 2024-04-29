package com.mastercoding.g15_lee_seol_comp304sec001_lab3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.schedule.ScheduleDao

class AirlineScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {
    fun fullSchedule() = scheduleDao.getAll()
    fun scheduleForAirlineName(airlineName: String) = scheduleDao.getByAirlineName(airlineName)
}

class AirlineScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirlineScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}