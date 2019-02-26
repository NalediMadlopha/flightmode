package com.flightmode.app.ui.flightschedule.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.flightmode.app.R
import com.flightmode.app.model.Airport
import com.flightmode.app.ui.flightschedule.viewmodel.FlightScheduleViewModel
import com.flightmode.app.ui.map.view.MapActivity
import com.flightmode.app.util.ViewModelFactory


class FlightScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flight_schedule_activity)

        val airport = intent.getParcelableExtra<Airport>(MapActivity.SELECTED_AIRPORT)

        ViewModelProviders.of(
            this,
            ViewModelFactory { FlightScheduleViewModel(airport) }
        ).get(FlightScheduleViewModel::class.java)
    }

}