package com.flightmode.app.ui.flightschedule.view


import android.os.AsyncTask
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
import kotlinx.android.synthetic.main.flight_schedule_fragment.view.*
import retrofit2.Call
import retrofit2.Response


class FlightScheduleFragment : Fragment(), FlightScheduleView {

    private lateinit var rootView: View
    private lateinit var viewModel: FlightScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(com.flightmode.app.R.layout.flight_schedule_fragment, container, false)
        setupToolBar()

        rootView.tryAgainTextView.setOnClickListener { fetchFlightSchedule() }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this.activity!!).get(FlightScheduleViewModel::class.java)
        viewModel.init(this)

        fetchFlightSchedule()
    }

    private fun fetchFlightSchedule() {
        hideError()
        showProgress()

        viewModel.getAirportDetails().observe(this, Observer<Airport> {
            flightScheduleAirportNameTextView.text = it.nameAirport
            flightScheduleAirportLocation.text = it.nameCountry

            AsyncTask.execute {
                viewModel.requestFlightSchedules(it.codeIataAirport, "departure")
            }
        })
    }

    override fun showProgress() {
        flightScheduleDetailsProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        flightScheduleDetailsProgressBar.visibility = View.GONE
    }

    override fun showError() {
        flightScheduleErrorContainer.visibility = View.VISIBLE
    }

    override fun hideError() {
        flightScheduleErrorContainer.visibility = View.GONE
    }

    override fun showFlightSchedules(flightScheduleList: List<FlightSchedule>) {
        flightScheduleRecylcerView.adapter = FlightScheduleAdapter(flightScheduleList)

        flightScheduleDetailsContainer.visibility = View.VISIBLE
    }

    override fun onResponse(call: Call<List<FlightSchedule>>, response: Response<List<FlightSchedule>>) {
        hideProgress()
        hideError()
        showFlightSchedules(response.body()!!)
    }

    override fun onFailure(call: Call<List<FlightSchedule>>, t: Throwable) {
        hideProgress()
        showError()
    }

    private fun setupToolBar() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }
}