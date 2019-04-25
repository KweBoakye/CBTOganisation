package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import android.view.View

interface LocationsViewClassInterface {

    interface LocationsViewClassListener{

    }

    interface LocationsViewClassFragmentListener{
        fun LaunchDialog(location: String)
    }
    fun setAdapterLocations(locations: List<String>)
    fun getRoot(): View
    fun setListener(locationsViewClassListener: LocationsViewClassListener)
    fun setFragmentListener(locationsViewClassFragmentListener: LocationsViewClassFragmentListener)
}