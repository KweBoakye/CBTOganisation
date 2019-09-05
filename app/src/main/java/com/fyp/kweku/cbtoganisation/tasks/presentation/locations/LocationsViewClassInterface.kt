package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import android.view.View
import androidx.appcompat.widget.SearchView

interface LocationsViewClassInterface {

    interface LocationsViewClassListener{

    }

    interface LocationsViewClassFragmentListener{
        fun launchDialog(location: String)
        fun getDebouncingQueryTextListener(): SearchView.OnQueryTextListener
    }
    fun setAdapterLocations(locations: List<String>)
    fun getRoot(): View
    fun setListener(locationsViewClassListener: LocationsViewClassListener)
   // fun setFragmentListener(locationsViewClassFragmentListener: LocationsViewClassFragmentListener)
}