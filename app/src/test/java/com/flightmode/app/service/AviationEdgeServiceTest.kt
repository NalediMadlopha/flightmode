package com.flightmode.app.service

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

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
        val call = mockErrorServiceCall.getNearbyAirports("Incorrect key", "-5.466667", "122.63333", "100")
        val response = call.execute()

        assertFalse(response.isSuccessful)
    }

    @Test
    fun getNearbyAirports_should_return_a_success_response_when_the_api_returns_a_success_response() {
        val call = mockSuccessServiceCall.getNearbyAirports("8477db3-385de", "-5.466667", "122.63333", "100")
        val response = call.execute()

        assertTrue(response.isSuccessful)
    }

    @Test
    fun getAirportsSchedule_should_return_an_error_response_when_the_api_returns_an_error_response() {
        val call = mockErrorServiceCall.getAirportsSchedule("Incorrect key", "JFK", "departure")
        val response = call.execute()

        assertFalse(response.isSuccessful)
    }

    @Test
    fun getAirportsSchedule_should_return_a_success_response_when_the_api_returns_a_success_response() {
        val call = mockSuccessServiceCall.getAirportsSchedule("8477db3-385de", "JFK", "departure")
        val response = call.execute()

        assertTrue(response.isSuccessful)
    }

    class MockErrorServiceCall(private val service: BehaviorDelegate<AviationEdgeService>) : AviationEdgeService {

        override fun getNearbyAirports(incorrectApiKey: String, lat: String, lng: String, distance: String): Call<JsonObject> {
            val errorResponse = Response.error<JsonObject>(
                404,
                ResponseBody.create(MediaType.parse("application/json"), MockErrorResponse.DATA)
            )

            return service.returning(Calls.response(errorResponse)).getNearbyAirports(incorrectApiKey, lat, lng, distance)
        }

        override fun getAirportsSchedule(incorrectApiKey: String, iataCode: String, type: String): Call<JsonObject> {
            val errorResponse = Response.error<JsonObject>(
                404,
                ResponseBody.create(MediaType.parse("application/json"), MockErrorResponse.DATA)
            )

            return service.returning(Calls.response(errorResponse)).getAirportsSchedule(incorrectApiKey, iataCode, type)
        }

    }

    class MockSuccessServiceCall(private val service: BehaviorDelegate<AviationEdgeService>) : AviationEdgeService {

        override fun getNearbyAirports(key: String, lat: String, lng: String, distance: String): Call<JsonObject> {
            val mockSuccessResponseJsonObject = JsonParser().parse(MockNearbyAirportsSuccessResponse.DATA).asJsonArray
            val successResponse = Response.success(mockSuccessResponseJsonObject)
            return service.returningResponse(Response.success(successResponse))
                .getNearbyAirports(key, lat, lng, distance)
        }

        override fun getAirportsSchedule(apiKey: String, iataCode: String, type: String): Call<JsonObject> {
            val mockSuccessResponseJsonObject = JsonParser().parse(MockAirportScheduleSuccessResponse.DATA).asJsonArray
            val successResponse = Response.success(mockSuccessResponseJsonObject)
            return service.returningResponse(Response.success(successResponse))
                .getAirportsSchedule(apiKey, iataCode, type)
        }

    }

}