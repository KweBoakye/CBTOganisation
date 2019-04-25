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


    fun loadLocations() = scope.launch(Dispatchers.IO){getTasksByLocationInteractorInterface.loadAllLocations()}
}