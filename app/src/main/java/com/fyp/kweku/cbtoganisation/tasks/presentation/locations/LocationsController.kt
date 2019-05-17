package com.fyp.kweku.cbtoganisation.tasks.presentation.locations

import com.fyp.kweku.cbtoganisation.tasks.domain.interactors.GetTasksByLocationInteractorInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LocationsController(val getTasksByLocationInteractorInterface: GetTasksByLocationInteractorInterface): LocationsViewClassInterface.LocationsViewClassListener {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    lateinit var locationsViewClassInterface: LocationsViewClassInterface

    fun bindView(locationsViewClassInterface: LocationsViewClassInterface){
        this.locationsViewClassInterface = locationsViewClassInterface
        locationsViewClassInterface.setListener(this)
    }


    fun loadLocations() = scope.launch(Dispatchers.IO){getTasksByLocationInteractorInterface.loadAllLocations()}
    fun locationsquery(searchString: String) = scope.launch(Dispatchers.IO)
    {getTasksByLocationInteractorInterface.passLocationsSearchString(searchString)}

    fun getFilteredLocations() = getTasksByLocationInteractorInterface.getFilteredLocationsAsAny()
    fun setAdapterLocations(locations: List<String>) = locationsViewClassInterface.setAdapterLocations(locations)
}