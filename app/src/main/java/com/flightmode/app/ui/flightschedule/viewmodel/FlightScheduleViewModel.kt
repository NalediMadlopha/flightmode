package com.flightmode.app.ui.flightschedule.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flightmode.app.model.Airport
import com.flightmode.app.model.FlightSchedule
import com.flightmode.app.repo.AirportRepository
import retrofit2.Callback


class FlightScheduleViewModel
@VisibleForTesting constructor(
    private val repository: AirportRepository,
    private val airport: Airport
) : ViewModel() {

    private lateinit var callback: Callback<List<FlightSchedule>>

    constructor(airport: Airport) : this(AirportRepository(), airport)

    fun init(callback: Callback<List<FlightSchedule>>) {
        this.callback = callback
    }

    fun requestFlightSchedules() {
        repository.getAirportsSchedule(airport.codeIataAirport, "departure").enqueue(callback)
    }

    fun getAirportDetails(): LiveData<Airport> {
        val airportLiveData = MutableLiveData<Airport>()
        airportLiveData.value = airport

        return airportLiveData
    }

}