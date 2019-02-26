package com.flightmode.app.repo

import com.flightmode.app.service.AviationEdgeService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks

class AirportRepositoryTest {

    private lateinit var repository: AirportRepository

    @Mock
    private lateinit var mockAviationEdgeService: AviationEdgeService

    @Before
    fun setUp() {
        initMocks(this)

        repository = AirportRepository(mockAviationEdgeService)
    }

    @Test
    fun repository_getNearbyAirports_should_make_a_getNearbyAirports_service_call() {
        val latitude = "-26.1304"
        val longitude = "28.2096"
        val distance = "5000.0"

        repository.getNearbyAirports(latitude, longitude, distance)

        verify(mockAviationEdgeService).getNearbyAirports(lat = latitude, lng = longitude, distance = distance)
    }

    @Test
    fun repository_getAirportsSchedule_should_make_a_getAirportsSchedule_service_call() {
        val iataCode = "JKF"
        val type = "departure"

        repository.getAirportsSchedule(iataCode, type)

        verify(mockAviationEdgeService).getAirportsSchedule(iataCode = iataCode, type = type)
    }

    @Test
    fun repository_getCity_should_make_a_getCity_service_call() {
        val codeIataCity = "codelataCity"

        repository.getCity(codeIataCity)

        verify(mockAviationEdgeService).getCity(iataCode = codeIataCity)
    }

}