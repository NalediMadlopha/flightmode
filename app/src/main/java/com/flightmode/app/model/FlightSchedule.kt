package com.flightmode.app.model

data class FlightSchedule(
    val airlineName: String,
    val status: String,
    val departureTime: String,
    val flightNumber: String,
    val destination: String
)
