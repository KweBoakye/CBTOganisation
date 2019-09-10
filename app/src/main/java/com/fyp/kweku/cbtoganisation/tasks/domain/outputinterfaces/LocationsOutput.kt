package com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces

import androidx.lifecycle.LiveData

interface LocationsOutput {

    fun getAllLocations(): LiveData<List<String>>
    fun getFilteredLocations():LiveData<List<String>>
    suspend fun showAllLocations(locations: List<String>)
    suspend fun setFilteredLocationsLiveData(filteredLocationsList: List<String>)
}