package com.flightmode.app.ui.flightschedule.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.flightmode.app.R
import com.flightmode.app.model.Airport
import com.flightmode.app.model.FlightSchedule
import com.flightmode.app.ui.flightschedule.viewmodel.FlightScheduleViewModel
import kotlinx.android.synthetic.main.flight_schedule_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FlightScheduleFragment : Fragment(), Callback<List<FlightSchedule>> {

    private lateinit var viewModel: FlightScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.flightmode.app.R.layout.flight_schedule_fragment, container, false)
        setupToolBar()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(FlightScheduleViewModel::class.java)
        viewModel.init(this)

        viewModel.getAirportDetails().observe(this,  Observer<Airport> {
            flightScheduleAirportNameTextView.text = it.nameAirport
            flightScheduleAirportLocation.text = it.nameCountry
        })

        viewModel.requestFlightSchedules()
    }

    override fun onResponse(call: Call<List<FlightSchedule>>, response: Response<List<FlightSchedule>>) {
        flightScheduleRecylcerView.adapter = FlightScheduleAdapter(response.body()!!)
    }

    override fun onFailure(call: Call<List<FlightSchedule>>, t: Throwable) {
        /* no-op */
    }


    private fun setupToolBar() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }
}