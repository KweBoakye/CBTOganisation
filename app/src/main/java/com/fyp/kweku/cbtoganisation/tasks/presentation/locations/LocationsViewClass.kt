package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.databinding.FragmentLocationsBinding
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationDialogFragment
import com.google.android.flexbox.AlignItems.BASELINE
import com.google.android.flexbox.FlexDirection.ROW
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent.CENTER

class LocationsViewClass(val inflater: LayoutInflater, val parent: ViewGroup?): LocationsViewClassInterface, LocationsRecyclerViewAdapter.LocationListener {
    override fun setListener(locationsViewClassListener: LocationsViewClassInterface.LocationsViewClassListener) {
        this.locationsViewClassListener = locationsViewClassListener
    }

    override fun setFragmentListener(locationsViewClassFragmentListener: LocationsViewClassInterface.LocationsViewClassFragmentListener) {
        this.locationsViewClassFragmentListener = locationsViewClassFragmentListener
    }

    override fun locationSelected(location: String) {
        locationsViewClassFragmentListener.LaunchDialog(location)
    }

    private val locationBinding: FragmentLocationsBinding = DataBindingUtil.inflate(inflater,
       R.layout.fragment_locations, parent, false )
    private val root = locationBinding.root
    private val locationsSearch: SearchView = locationBinding.locationSearch
    private val locationsRecyclerView: RecyclerView = locationBinding.locationsRecyclerView
    private val flexboxLayoutManager: FlexboxLayoutManager = FlexboxLayoutManager(parent!!.context)
    private val locationsRecyclerViewAdapter: LocationsRecyclerViewAdapter = LocationsRecyclerViewAdapter(this)
    lateinit var locationsViewClassFragmentListener: LocationsViewClassInterface.LocationsViewClassFragmentListener
    lateinit var locationsViewClassListener: LocationsViewClassInterface.LocationsViewClassListener

    init{
        initLocationsRecyclerview()
    }

    override fun setAdapterLocations(locations: List<String>)= locationsRecyclerViewAdapter.setLocations(locations)


    fun initLocationsRecyclerview(){
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