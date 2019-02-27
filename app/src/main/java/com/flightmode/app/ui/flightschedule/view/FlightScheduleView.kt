package com.flightmode.app.ui.flightschedule.view

import com.flightmode.app.model.FlightSchedule
import retrofit2.Callback

interface FlightScheduleView : Callback<List<FlightSchedule>> {

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun hideError()

    fun showFlightSchedules(flightScheduleList: List<FlightSchedule>)

}