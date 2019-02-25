package com.flightmode.app.service

import com.flightmode.app.model.Airport
import com.flightmode.app.model.City
import com.flightmode.app.model.FlightSchedule
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

private val errorResponse: Response<JsonObject> by lazy {
    val errorResponse = Response.error<JsonObject>(
        404,
        ResponseBody.create(MediaType.parse("application/json"), MockErrorResponse.DATA)
    )
    errorResponse
}

class AviationEdgeServiceTest {

    private lateinit var retrofit: Retrofit
    private lateinit var delegateAviationEdgeService: BehaviorDelegate<AviationEdgeService>
    private lateinit var mockRetrofit: MockRetrofit
    private lateinit var mockErrorServiceCall: MockErrorServiceCall
    private lateinit var mockSuccessServiceCall: MockSuccessServiceCall

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(AviationEdgeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val behavior = NetworkBehavior.create()

        mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(behavior).build()
        delegateAviationEdgeService = mockRetrofit.create(AviationEdgeService::class.java)
        mockErrorServiceCall = MockErrorServiceCall(delegateAviationEdgeService)
        mockSuccessServiceCall = MockSuccessServiceCall(delegateAviationEdgeService)
    }

    @Test
    fun getInstance_should_return_an_instant_of_the_AviationEdgesService() {
        assertTrue(AviationEdgeService::class.java.isInstance(AviationEdgeService.getInstance()))
    }

    @Test
    fun getNearbyAirports_should_return_an_error_response_when_the_api_returns_an_error_response() {
        val call = mockErrorServiceCall.getNearbyAirports("-5.466667", "122.63333", "100")
        val response = call.execute()

        assertFalse(response.isSuccessful)
    }

    @Test
    fun getNearbyAirports_should_return_a_success_response_when_the_api_returns_a_success_response() {
        val call = mockSuccessServiceCall.getNearbyAirports("-5.466667", "122.63333", "100")
        val response = call.execute()

        assertTrue(response.isSuccessful)
    }

    @Test
    fun getAirportsSchedule_should_return_an_error_response_when_the_api_returns_an_error_response() {
        val call = mockErrorServiceCall.getAirportsSchedule("JFK", "departure")
        val response = call.execute()

        assertFalse(response.isSuccessful)
    }

    @Test
    fun getAirportsSchedule_should_return_a_success_response_when_the_api_returns_a_success_response() {
        val call = mockSuccessServiceCall.getAirportsSchedule("JFK", "departure")
        val response = call.execute()

        assertTrue(response.isSuccessful)
    }

    @Test
    fun getCity_should_return_an_error_response_when_the_api_returns_an_error_response() {
        val call = mockErrorServiceCall.getCity("JFK")
        val response = call.execute()

        assertFalse(response.isSuccessful)
    }

    @Test
    fun getCity_should_return_a_success_response_when_the_api_returns_a_success_response() {
        val call = mockSuccessServiceCall.getCity("JFK")
        val response = call.execute()

        assertTrue(response.isSuccessful)
    }

    class MockErrorServiceCall(private val service: BehaviorDelegate<AviationEdgeService>) : AviationEdgeService {

        override fun getNearbyAirports(lat: String, lng: String, distance: String, incorrectApiKey: String): Call<List<Airport>> {

            return service.returning(Calls.response(errorResponse)).getNearbyAirports(lat, lng, distance)
        }

        override fun getAirportsSchedule(iataCode: String, type: String, incorrectApiKey: String): Call<List<FlightSchedule>> {
            return service.returning(Calls.response(errorResponse)).getAirportsSchedule(iataCode, type)
        }

        override fun getCity(iataCode: String, apiKey: String): Call<List<City>> {
            return service.returning(Calls.response(errorResponse)).getCity(iataCode)
        }

    }

    class MockSuccessServiceCall(private val service: BehaviorDelegate<AviationEdgeService>) : AviationEdgeService {

        override fun getNearbyAirports(lat: String, lng: String, distance: String, key: String): Call<List<Airport>> {
            val successResponse = Response.success(listOf<Airport>())
            return service.returningResponse(Response.success(successResponse))
                .getNearbyAirports(lat, lng, distance, key)
        }

        override fun getAirportsSchedule(iataCode: String, type: String, apiKey: String): Call<List<FlightSchedule>> {
            val successResponse = Response.success(listOf<FlightSchedule>())
            return service.returningResponse(Response.success(successResponse))
                .getAirportsSchedule(iataCode, type, apiKey)
        }

        override fun getCity(iataCode: String, apiKey: String): Call<List<City>> {
            val successResponse = Response.success(listOf<City>())
            return service.returningResponse(Response.success(successResponse))
                .getCity(iataCode, apiKey)
        }

    }

}