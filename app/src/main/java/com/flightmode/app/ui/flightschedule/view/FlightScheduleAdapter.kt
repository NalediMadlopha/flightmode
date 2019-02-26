package com.flightmode.app.ui.flightschedule.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flightmode.app.R
import com.flightmode.app.model.FlightSchedule
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.flight_schedule_list_item.*

class FlightScheduleAdapter(private val flightScheduleList: List<FlightSchedule>)
    : RecyclerView.Adapter<FlightScheduleAdapter.FlightScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flight_schedule_list_item, parent, false)
        return FlightScheduleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return flightScheduleList.size
    }

    override fun onBindViewHolder(holder: FlightScheduleViewHolder, position: Int) {
        holder.bind(flightScheduleList[position])
    }

    class FlightScheduleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(flightSchedule: FlightSchedule) {
            flightScheduleAirlineNameTextView.text = flightSchedule.airline.name
            flightScheduleStatusTextView.text = flightSchedule.status
//            flightScheduleDepartureLabelValueWidget.setBottomText(flightSchedule.departure.scheduledTime)
            flightScheduleFlightNumberLabelValueWidget.setBottomText(flightSchedule.flight.number)
            flightScheduleDestinationLabelValueWidget.setBottomText(flightSchedule.arrival.iataCode)
        }

    }

}