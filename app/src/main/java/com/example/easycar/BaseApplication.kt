package com.example.easycar

import android.app.Application
import com.example.easycar.data.CarDatabase

class BaseApplication : Application() {
    val database: CarDatabase by lazy { CarDatabase.getDatabase(this) }
}