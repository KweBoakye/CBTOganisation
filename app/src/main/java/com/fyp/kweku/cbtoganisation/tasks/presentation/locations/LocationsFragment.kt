package com.fyp.kweku.cbtoganisation.tasks.presentation.locations


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationDialogFragment
import org.koin.android.ext.android.get


class LocationsFragment : Fragment(), LocationsViewClassInterface.LocationsViewClassFragmentListener {



    lateinit var locationsController: LocationsController
    private var searchActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationsController = get()
        locationsController.loadLocations()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val locationsViewClassInterface: LocationsViewClassInterface = LocationsViewClass(inflater, container,this)
        //locationsViewClassInterface.setFragmentListener(this)
       locationsController.bindView(locationsViewClassInterface)

        val root: View = locationsViewClassInterface.getRoot()

        val allLocationsLiveDataObserver = Observer<List<String>>{ allLocations -> locationsViewClassInterface.setAdapterLocations(
            allLocations
        )}

        setAllLocationsAsLiveDataObserver(locationsObserver())
        setFilteredLocationsLiveDataObserver(locationsObserver())


       // getAllLocationsAsLiveData().observe(this,allLocationsLiveDataObserver )
        //getFilteredLocationsAsLiveData().observe(this, allLocationsLiveDataObserver)
        return root
    }

    private fun setLiveDataToObserve():LiveData<List<String>>{
        return if (searchActive){
            getFilteredLocationsAsLiveData()
        }
        else getAllLocationsAsLiveData()
    }

    private fun locationsObserver():Observer<List<String>>{
        return  Observer{ locations -> locationsController.setAdapterLocations(locations) }
    }

    private fun setFilteredLocationsLiveDataObserver(observer: Observer<List<String>>){
        getFilteredLocationsAsLiveData().observe(this, observer)
    }

    private fun removeFilteredLocationsLiveDataObserver(observer: Observer<List<String>>){
        getFilteredLocationsAsLiveData().removeObserver(observer)
    }

    private fun setAllLocationsAsLiveDataObserver(observer: Observer<List<String>>){
        getAllLocationsAsLiveData().observe(this,observer)
    }
    private fun removeAllLocationsAsLiveDataObserver(observer: Observer<List<String>>){
        getAllLocationsAsLiveData().removeObserver(observer)
    }


    override fun getDebouncingQueryTextListener(): SearchView.OnQueryTextListener{
        return DebouncingQueryTextListener(this.lifecycle){newText -> newText?.let {
            if (it.isEmpty()){
             setAllLocationsAsLiveDataObserver(locationsObserver())
                removeFilteredLocationsLiveDataObserver(locationsObserver())
            } else{
                setFilteredLocationsLiveDataObserver(locationsObserver())
                removeAllLocationsAsLiveDataObserver(locationsObserver())
                locationsController.locationsquery(it)
            }
        }}
    }

    override fun launchDialog(location: String) {
        launchDialogFragmentWithArguments(location)
    }


    fun launchDialogFragmentWithArguments(location: String){
        val locationBundle = Bundle()
        locationBundle.putString("location", location)
        launchDialog(locationBundle)
    }
    fun launchDialog(locationBundle: Bundle){
        val dialog = TasksByLocationDialogFragment()
        dialog.arguments = locationBundle
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        dialog.show(fragmentTransaction, TasksByLocationDialogFragment.TAG)
    }


    private fun getAllLocationsAsLiveData(): LiveData<List<String>>{
        @Suppress("UNCHECKED_CAST")
        return locationsController.getTasksByLocationInteractorInterface.getAllLocationsAsAny() as LiveData<List<String>>
    }

    private fun getFilteredLocationsAsLiveData(): LiveData<List<String>>{
        @Suppress("UNCHECKED_CAST")
        return locationsController.getFilteredLocations() as LiveData<List<String>>
    }



}
