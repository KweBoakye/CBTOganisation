package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import kotlinx.coroutines.Job

interface LocationInteractorInterface {

     fun loadAllLocations(): Job
    suspend fun getTasksByLocation(taskLocation: String):List<Task>
    suspend fun sendAllLocationsToOutput(locations: List<String>)
    fun filterLocations(searchString:String): Job


}