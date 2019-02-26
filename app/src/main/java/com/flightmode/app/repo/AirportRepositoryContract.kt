package com.flightmode.app.repo

import com.flightmode.app.model.Airport
import com.flightmode.app.model.City
import com.flightmode.app.model.FlightSchedule
import retrofit2.Call

interface AirportRepositoryContract {

    fun getNearbyAirports(lat: String, lng: String, distance: String) : Call<List<Airport>>

    fun getAirportsSchedule(iataCode: String, type: String): Call<List<FlightSchedule>>

    fun getCity(codeIataCity: String): Call<List<City>>

}