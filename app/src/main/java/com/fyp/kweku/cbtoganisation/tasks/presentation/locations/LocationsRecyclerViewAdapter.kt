package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import android.content.Context
import android.location.LocationListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.LocationRecyclerviewChipBinding
import com.google.android.material.chip.Chip

class LocationsRecyclerViewAdapter(val locationListener: LocationListener):RecyclerView.Adapter<LocationsRecyclerViewAdapter.LocationsViewHolder>() {

     private var locations: List<String> = mutableListOf()



lateinit var locationChipBinding: LocationRecyclerviewChipBinding
    inner class LocationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
   lateinit var locationChip: Chip

        fun bind(position:Int, locations: List<String> ){
            locationChip.text = locations[position]
            locationChip.setOnClickListener {  locationListener.locationSelected(locations[position])}
        }
    }

    fun setLocations(locations: List<String>){
        this.locations = locations
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        locationChipBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.location_recyclerview_chip, parent,false)
        val locationsViewHolder = LocationsViewHolder(locationChipBinding.root)
        locationsViewHolder.locationChip =  locationChipBinding.locationChip


        return locationsViewHolder
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(position, locations)
    }

    interface LocationListener{
        fun locationSelected(location: String)
    }

}