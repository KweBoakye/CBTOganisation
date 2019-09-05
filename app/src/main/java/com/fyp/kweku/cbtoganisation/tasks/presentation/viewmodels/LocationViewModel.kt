package com.fyp.kweku.cbtoganisation.tasks.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.LocationsOutput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationViewModel @Inject constructor(): ViewModel(),
    LocationsOutput {

    private val allLocations: MutableLiveData<List<String>> = MutableLiveData()
    private var filteredLocations: MutableLiveData<List<String>> = MutableLiveData()


    override suspend fun showAllLocations(locations: List<String>) = withContext(Dispatchers.Main) {
        allLocations.value = locations
    }

    override fun getAllLocations(): LiveData<List<String>> {
        Timber.i("${allLocations.value}")
        Timber.i("${allLocations.hasActiveObservers()}")
        return this.allLocations
    }
    override fun getFilteredLocations():LiveData<List<String>> = filteredLocations

    override suspend fun setFilteredLocationsLiveData(filteredLocationsList: List<String>)= withContext(Dispatchers.Main){
        filteredLocations.value = filteredLocationsList
        Timber.i("${filteredLocations.value}")
    }



}