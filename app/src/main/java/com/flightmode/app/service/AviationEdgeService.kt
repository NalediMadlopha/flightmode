package com.flightmode.app.service

import com.flightmode.app.BuildConfig
import com.flightmode.app.model.Airport
import com.flightmode.app.model.City
import com.flightmode.app.model.FlightSchedule
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AviationEdgeService {

    @GET(PATH_NEARBY)
    fun getNearbyAirports(
        @Query(QUERY_API_KEY) apiKey: String = BuildConfig.Flightmode_AviationEdge_ApiKey,
        @Query(QUERY_LAT) lat: String,
        @Query(QUERY_LNG) lng: String,
        @Query(QUERY_DISTANCE) distance: String
    ) : Call<List<Airport>>

    @GET(PATH_TIMETABLE)
    fun getAirportsSchedule(
        @Query(QUERY_API_KEY) apiKey: String = BuildConfig.Flightmode_AviationEdge_ApiKey,
        @Query(QUERY_IATA_CODE) iataCode: String,
        @Query(QUERY_TYPE) type: String
    ) : Call<List<FlightSchedule>>

    @GET(PATH_CITY_DATABASE)
    fun getCity(
        @Query(QUERY_API_KEY) apiKey: String = BuildConfig.Flightmode_AviationEdge_ApiKey,
        @Query(QUERY_CODE_IATA_CITY) iataCode: String
    ) : Call<List<City>>

    companion object {
        private const val PATH_NEARBY = "/v2/public/nearby"
        private const val PATH_TIMETABLE = "/v2/public/timetable"
        private const val PATH_CITY_DATABASE = "/v2/public/cityDatabase"
        private const val QUERY_API_KEY = "key"
        private const val QUERY_LAT = "lat"
        private const val QUERY_LNG = "lng"
        private const val QUERY_DISTANCE = "distance"
        private const val QUERY_IATA_CODE = "iataCode"
        private const val QUERY_CODE_IATA_CITY = "codeiataCode"
        private const val QUERY_TYPE = "type"

        const val BASE_URL = "http://aviation-edge.com/"

        fun getInstance() : AviationEdgeService {
            return Retrofit.Builder()
                .baseUrl(AviationEdgeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AviationEdgeService::class.java)
        }
    }

}