package com.mastercoding.g15_lee_seol_comp304sec001_lab3

import android.app.Application
import com.mastercoding.g15_lee_seol_comp304sec001_lab3.database.AppDatabase

class AirlineScheduleApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}