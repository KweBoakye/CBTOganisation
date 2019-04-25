package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.TaskOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface

class GetTasksByLocationInteractor(val taskRepositoryInterface: TaskRepositoryInterface, val taskOutput: TaskOutput): GetTasksByLocationInteractorInterface {



    override suspend fun loadAllLocations() {
        sendAllLocationsToOutput(taskRepositoryInterface.getAllLocations())
    }

    override suspend fun getTasksByLocation(taskLocation: String): List<Task> {
        return taskRepositoryInterface.getTasksByLocation(taskLocation)
    }

    override suspend fun sendAllLocationsToOutput(locations: List<String>){
     taskOutput.showAllLocations(locations)
    }

    override suspend fun sendTasksByLocationToOutput(location: String) {
        taskOutput.showTasksByLocation(getTasksByLocation(location))
    }

    override fun getAllLocationsAsAny(): Any {
        return taskOutput.getAllLocations()
    }

    override fun getTasksByLocationLiveDataAsAny():Any{
        return taskOutput.getTasksByLocation()
    }


}