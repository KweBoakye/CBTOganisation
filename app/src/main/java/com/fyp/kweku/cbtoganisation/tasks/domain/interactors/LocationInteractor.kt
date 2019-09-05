package com.fyp.kweku.cbtoganisation.tasks.domain.interactors

import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.LocationsOutput
import com.fyp.kweku.cbtoganisation.tasks.domain.model.Task
import com.fyp.kweku.cbtoganisation.tasks.domain.repository.TaskRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Singleton
class LocationInteractor @Inject constructor(private val taskRepositoryInterface: TaskRepositoryInterface,
                         private val locationsOutput: LocationsOutput
): LocationInteractorInterface {

     private var allLocations: MutableList<String> = mutableListOf()
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    override  fun loadAllLocations() =scope.launch(Dispatchers.IO) {
        allLocations = taskRepositoryInterface.getAllLocations().toMutableList()
        locationsOutput.showAllLocations(allLocations)
        Timber.i("$allLocations")
    }

    override suspend fun getTasksByLocation(taskLocation: String): List<Task> {
        return taskRepositoryInterface.getTasksByLocation(taskLocation)
    }

    override suspend fun sendAllLocationsToOutput(locations: List<String>){
        locationsOutput.showAllLocations(locations)
    }


   override fun filterLocations(searchString:String)= scope.launch(Dispatchers.IO){
        val a = (allLocations.filter {
                location -> location.contains(searchString, true) })
       Timber.i("$a")
       locationsOutput.setFilteredLocationsLiveData(a)
    }




}