package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentLocationsBinding
import com.google.android.flexbox.AlignItems.BASELINE
import com.google.android.flexbox.FlexDirection.ROW
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent.CENTER

class LocationsViewClass(val inflater: LayoutInflater, val parent: ViewGroup?, private val locationsViewClassFragmentListener: LocationsViewClassInterface.LocationsViewClassFragmentListener ): LocationsViewClassInterface, LocationsRecyclerViewAdapter.LocationListener {
    override fun setListener(locationsViewClassListener: LocationsViewClassInterface.LocationsViewClassListener) {
        this.locationsViewClassListener = locationsViewClassListener
    }


    override fun locationSelected(location: String) {
        locationsViewClassFragmentListener.launchDialog(location)
    }

    private val locationBinding: FragmentLocationsBinding = DataBindingUtil.inflate(inflater,
       R.layout.fragment_locations, parent, false )
    private val root = locationBinding.root
    private val locationsSearch: SearchView = locationBinding.locationSearch
    private val locationsRecyclerView: RecyclerView = locationBinding.locationsRecyclerView
    private val flexboxLayoutManager: FlexboxLayoutManager = FlexboxLayoutManager(parent!!.context)
    private val locationsRecyclerViewAdapter: LocationsRecyclerViewAdapter = LocationsRecyclerViewAdapter(this)
    private lateinit var locationsViewClassListener: LocationsViewClassInterface.LocationsViewClassListener

    private fun initSearchView(){
        locationsSearch.setOnQueryTextListener(
            locationsViewClassFragmentListener.getDebouncingQueryTextListener())
    }

    init{
        initLocationsRecyclerview()
        initSearchView()
    }

    override fun setAdapterLocations(locations: List<String>)= locationsRecyclerViewAdapter.setLocations(locations)

    private fun initLocationsRecyclerview(){
        with(flexboxLayoutManager) {
            flexDirection = ROW
            flexWrap = WRAP
            justifyContent = CENTER
            alignItems = BASELINE
        }

        locationsRecyclerView.layoutManager = flexboxLayoutManager
        locationsRecyclerView.adapter = locationsRecyclerViewAdapter
    }

    override fun getRoot(): View {
        return this.root
    }




}