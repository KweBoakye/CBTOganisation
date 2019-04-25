package com.fyp.kweku.cbtoganisation.tasks.presentation.locations.tasksbylocation


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.fyp.kweku.cbtoganisation.R
import com.fyp.kweku.cbtoganisation.tasks.presentation.presentationmodel.TaskPresentationModel
import org.koin.android.ext.android.get


class TasksByLocationDialogFragment : DialogFragment() , TasksByLocationViewClassInterface.TasksByLocationViewClassFragmentListener{

    companion object{
        val TAG: String = "TasksByLocationDialogFragment"
    }

    private lateinit var tasksByLocationController: TasksByLocationController
    var location: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasksByLocationController = get()
        val locationBundle: Bundle? = arguments


            location = locationBundle?.getString("location")

        location?.let { tasksByLocationController.loadTasksByLocations(it) }
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val tasksByLocationViewClassInterface: TasksByLocationViewClassInterface = TasksByLocationViewClass(inflater, container)
        tasksByLocationViewClassInterface.also {
           it.setFragmentListener(this)
            it.setLocation(location)
            it.setupToolbar()
            it.initRecyclerView(this.context!!)
        }

        val tasksByLocationObserver = Observer<List<TaskPresentationModel>>{tasks -> tasksByLocationViewClassInterface.sendDataToAdapter(tasks)}

        tasksByLocationLivedata().observe(this, tasksByLocationObserver)

        return tasksByLocationViewClassInterface.getRoot()
    }


    override fun dismissDialogFragment(){
        this.dismiss()
    }


    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width: Int = ViewGroup.LayoutParams.MATCH_PARENT
            val height: Int = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    fun tasksByLocationLivedata():MutableLiveData<List<TaskPresentationModel>>{
        @Suppress("UNCHECKED_CAST")
       return tasksByLocationController.getTasksByLocationInteractorInterface.getTasksByLocationLiveDataAsAny() as MutableLiveData<List<TaskPresentationModel>>
    }
}
