package com.flightmode.app.ui.flightschedule.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.flightmode.app.model.Airport
import com.flightmode.app.repo.AirportRepository
import com.flightmode.app.ui.flightschedule.view.FlightScheduleView


class FlightScheduleViewModel
@VisibleForTesting constructor(
    private val repository: AirportRepository,
    private val airportLiveData: LiveData<Airport>
) : ViewModel() {

    private lateinit var view: FlightScheduleView

    constructor(airportLiveData: LiveData<Airport>, scheduleType: String) : this(AirportRepository(), airportLiveData)

    fun init(view: FlightScheduleView) {
        this.view = view
    }

    fun getAirportDetails() = airportLiveData

    fun requestFlightSchedules(codeIataAirport: String, scheduleType: String) {
        view.showProgress()

        repository.getAirportsSchedule(codeIataAirport, scheduleType).enqueue(view)
    }

}