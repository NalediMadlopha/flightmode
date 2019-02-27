package com.flightmode.app.repo

import androidx.annotation.VisibleForTesting
import com.flightmode.app.model.Airport
import com.flightmode.app.model.City
import com.flightmode.app.model.FlightSchedule
import com.flightmode.app.service.AviationEdgeService
import retrofit2.Call

open class AirportRepository @VisibleForTesting constructor(private val service: AviationEdgeService) : AirportRepositoryContract {

    constructor() : this(AviationEdgeService.getInstance())

    override fun getNearbyAirports(lat: String, lng: String, distance: String): Call<List<Airport>> {
        return service.getNearbyAirports(lat = lat, lng = lng, distance = distance)
    }

    override fun getAirportsSchedule(iataCode: String, type: String): Call<List<FlightSchedule>> {
        return service.getAirportsSchedule(iataCode = iataCode, type = type)
    }

    override fun getCity(codeIataCity: String): Call<List<City>> {
        return service.getCity(iataCode = codeIataCity)
    }

}