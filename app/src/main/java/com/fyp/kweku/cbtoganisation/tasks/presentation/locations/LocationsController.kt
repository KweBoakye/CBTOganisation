package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.LocationInteractorInterface
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocationsController @Inject constructor (private val locationInteractorInterface: LocationInteractorInterface): LocationsViewClassInterface.LocationsViewClassListener {

    lateinit var locationsViewClassInterface: LocationsViewClassInterface

    fun bindView(locationsViewClassInterface: LocationsViewClassInterface){
        this.locationsViewClassInterface = locationsViewClassInterface
        locationsViewClassInterface.setListener(this)
    }

    fun loadLocations() = locationInteractorInterface.loadAllLocations()
    fun locationsQuery(searchString: String) = locationInteractorInterface.filterLocations(searchString)
    fun setAdapterLocations(locations: List<String>) = locationsViewClassInterface.setAdapterLocations(locations)
}