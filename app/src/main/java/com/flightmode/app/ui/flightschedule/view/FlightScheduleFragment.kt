package com.flightmode.app.ui.flightschedule.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.flightmode.app.R
import kotlinx.android.synthetic.main.flight_schedule_fragment.*


class FlightScheduleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupToolBar()
        return inflater.inflate(com.flightmode.app.R.layout.flight_schedule_fragment, container, false)
    }

    private fun setupToolBar() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }
        }
    }
}