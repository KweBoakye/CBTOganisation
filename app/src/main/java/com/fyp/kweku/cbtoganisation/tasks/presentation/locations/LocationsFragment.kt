package com.fyp.kweku.cbtoganisation.tasks.presentation.locations


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.koin.android.ext.android.get


class LocationsFragment : Fragment(), LocationsViewClassInterface.LocationsViewClassFragmentListener {



    lateinit var locationsController: LocationsController


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
        val locationsViewClassInterface: LocationsViewClassInterface = LocationsViewClass(inflater, container)
        locationsViewClassInterface.setFragmentListener(this)
        locationsViewClassInterface.setListener(locationsController)

        val root: View = locationsViewClassInterface.getRoot()

        val allLocationsLiveDataObserver = Observer<List<String>>{ allLocations -> locationsViewClassInterface.setAdapterLocations(
            allLocations
        )}


        getAllLocationsAsLiveData().observe(this,allLocationsLiveDataObserver )
        return root


    }

    override fun LaunchDialog(location: String) {
        launchDialogFragmentWithArguments(location)
    }


    fun launchDialogFragmentWithArguments(location: String){
        val locationBundle: Bundle = Bundle()
        locationBundle.putString("location", location)
        launchDialog(locationBundle)
    }
    fun launchDialog(locationBundle: Bundle){
        val dialog = TasksByLocationDialogFragment()
        dialog.arguments = locationBundle
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        dialog.show(fragmentTransaction, TasksByLocationDialogFragment.TAG)
    }


    fun getAllLocationsAsLiveData(): LiveData<List<String>>{
        @Suppress("UNCHECKED_CAST")
        return locationsController.getTasksByLocationInteractorInterface.getAllLocationsAsAny() as LiveData<List<String>>
    }



}
