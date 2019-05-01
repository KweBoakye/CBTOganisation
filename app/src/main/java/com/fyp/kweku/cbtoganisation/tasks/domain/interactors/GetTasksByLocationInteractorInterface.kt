package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task

interface GetTasksByLocationInteractorInterface {

    suspend fun loadAllLocations()
    suspend fun getTasksByLocation(taskLocation: String):List<Task>
    suspend fun sendAllLocationsToOutput(locations: List<String>)
    suspend fun sendTasksByLocationToOutput(location: String)
    fun getAllLocationsAsAny(): Any
    fun getTasksByLocationLiveDataAsAny():Any


}