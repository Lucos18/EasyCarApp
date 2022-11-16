package com.example.easycar.ui.viewmodel

import androidx.lifecycle.*
import com.example.easycar.data.CarDao
import com.example.easycar.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.nio.file.Files.delete

class CarViewModel(
    private val CarDao: CarDao
) : ViewModel(){
    val allCars: LiveData<List<Car>> = CarDao.getCars().asLiveData()

    fun getCarById(id: Long): LiveData<Car> {
        return CarDao.getCarById(id).asLiveData()
    }

    fun addCar(
        Brand: String,
        Model: String,
        YearStartProduction: Int,
        YearEndProduction: Int,
        Seats: Int,
        FuelType: String
    ) {
        val car = Car(
            brand = Brand,
            model = Model,
            yearStartProduction = YearStartProduction,
            yearEndProduction = YearEndProduction,
            seats = Seats,
            fuelType = FuelType
        )

        // TODO: launch a coroutine and call the DAO method to add a Forageable to the database within it
        viewModelScope.launch {
            CarDao.insert(car)
        }
    }
    //TODO add Function to update new car if needed

    fun deleteCar(car: Car) {
        viewModelScope.launch(Dispatchers.IO) {
            CarDao.delete(car)
        }
    }
}
class CarViewModelFactory(private val CarDao: CarDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CarViewModel(CarDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}