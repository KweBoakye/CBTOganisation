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
import com.fyp.kweku.cbtoganisation.common.CBTOrganisationApplication
import com.fyp.kweku.cbtoganisation.tasks.domain.outputinterfaces.LocationsOutput
import com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation.TasksByLocationDialogFragment
import javax.inject.Inject


class LocationsFragment : Fragment(), LocationsViewClassInterface.LocationsViewClassFragmentListener {


    @Inject lateinit var locationsController: LocationsController
    @Inject  lateinit var locationsOutput: LocationsOutput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CBTOrganisationApplication.getComponent().inject(this)
        //locationsController = component.locationsController
        //component = DaggerAppComponent.builder().build()



        //locationsOutput =
        //locationsController = get()
        locationsController.loadLocations()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val locationsViewClassInterface: LocationsViewClassInterface = LocationsViewClass(inflater, container,this)

       locationsController.bindView(locationsViewClassInterface)

        val root: View = locationsViewClassInterface.getRoot()
        setAllLocationsAsLiveDataObserver(locationsObserver())
        setFilteredLocationsLiveDataObserver(locationsObserver())

        return root
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
                locationsController.locationsQuery(it)
            }
        }}
    }

    override fun launchDialog(location: String) {
        launchDialogFragmentWithArguments(location)
    }


    private fun launchDialogFragmentWithArguments(location: String){
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
        return locationsOutput.getAllLocations()
    }

    private fun getFilteredLocationsAsLiveData(): LiveData<List<String>>{
        return locationsOutput.getFilteredLocations()
    }



}
