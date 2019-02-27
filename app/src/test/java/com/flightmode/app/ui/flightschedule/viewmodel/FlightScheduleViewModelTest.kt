package com.flightmode.app.ui.flightschedule.viewmodel

import androidx.lifecycle.LiveData
import com.flightmode.app.model.Airport
import com.flightmode.app.model.FlightSchedule
import com.flightmode.app.repo.AirportRepository
import com.flightmode.app.ui.flightschedule.view.FlightScheduleView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import retrofit2.Call

class FlightScheduleViewModelTest {

    private lateinit var viewModel: FlightScheduleViewModel

    @Mock
    private lateinit var mockAirportRepository: AirportRepository
    @Mock
    private lateinit var mockCall: Call<List<FlightSchedule>>
    @Mock
    private lateinit var mockView: FlightScheduleView
    @Mock
    private lateinit var mockAirportLiveData: LiveData<Airport>
    @Mock
    private lateinit var mockAirport: Airport

    @Before
    fun setUp() {
        initMocks(this)

        viewModel = FlightScheduleViewModel(mockAirportRepository, mockAirportLiveData)
        viewModel.init(mockView)
    }

    @Test
    fun displayProgress_on_when_flight_schedules_requested() {
        `when`(mockAirportRepository.getAirportsSchedule(CODE_IATA_AIRPORT, SCHEDULE_TYPE)).thenReturn(mockCall)

        viewModel.requestFlightSchedules(CODE_IATA_AIRPORT, SCHEDULE_TYPE)

        verify(mockView).showProgress()
    }

    @Test
    fun getAirportCodeIata_should_return_the_correct_code() {
        `when`(mockAirportLiveData.value).thenReturn(mockAirport)

        assertEquals(viewModel.getAirportDetails(), mockAirportLiveData)
    }

    @Test
    fun requestFlightSchedules_should_send_a_request_to_the_repository_for_data() {
        `when`(mockAirportRepository.getAirportsSchedule(CODE_IATA_AIRPORT, SCHEDULE_TYPE)).thenReturn(mockCall)

        viewModel.requestFlightSchedules(CODE_IATA_AIRPORT, SCHEDULE_TYPE)

        verify(mockCall).enqueue(mockView)
    }

    companion object {
        private const val CODE_IATA_AIRPORT = "codeIataAirport"
        private const val SCHEDULE_TYPE = "departure"
    }

}